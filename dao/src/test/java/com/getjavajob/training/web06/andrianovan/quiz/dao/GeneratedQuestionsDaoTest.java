package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.GeneratedQuestionsDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nat on 24.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { //"classpath:quiz-context.xml",
        "classpath:quiz-context-dao-overrides.xml"})
public class GeneratedQuestionsDaoTest {

    private GeneratedQuestionsDao dao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initDatabase() throws DaoException {
        new DatabaseInitializer().initDatabase();
    }

    @Test
    public void testGetById() {
        GeneratedQuestions generatedQuestions = dao.get(4);
        Question question1 = new Question("Which four options describe the correct default values for array " +
                "elements of the types indicated?");
        Question question2 = new Question("Which is a reserved word in the Java programming language?");
        Question question3 = new Question("Which one of the following will declare an array and initialize it with five numbers?");

        assertTrue(CollectionUtils.isEqualCollection(Arrays.asList( question1, question2, question3),
                generatedQuestions.getQuestions()));
    }

    @Test
    public void shouldTestExceptionMessage() throws Exception {
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage(startsWith("Cannot get all records from quiz_generated_questions"));
        dao.getAll();
    }
}
