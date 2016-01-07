package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.GeneratedQuestionsDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nat on 24.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:quiz-context-dao.xml", "classpath:quiz-context-dao-overrides.xml"})
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GeneratedQuestionsDaoTest {

    private static final int ROWS_NUMBER = 2;
    @Autowired
    private GeneratedQuestionsDao dao;
    @Autowired
    private QuizStartDao quizStartDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuizSetDao quizSetDao;

    @Test
    public void testGetById() {
        GeneratedQuestions generatedQuestions = this.dao.get(5);
        Question question1 = new Question("Which four options describe the correct default values for array " +
                "elements of the types indicated?");
        Question question2 = new Question("Which is a reserved word in the Java programming language?");
        Question question3 = new Question("Which one of the following will declare an array and initialize it with five numbers?");

        assertTrue(CollectionUtils.isEqualCollection(Arrays.asList( question1, question2, question3),
                generatedQuestions.getQuestions()));
    }

    @Test
    public void testInsert() throws DaoException {
        QuizSet quizSet = this.quizSetDao.get(1);
        QuizStart quizStart = new QuizStart(quizSet);
        quizStartDao.insert(quizStart);

        GeneratedQuestions generatedQuestions = new GeneratedQuestions(quizStart);

        Question question1 = new Question("Which four options describe the correct default values for array " +
                "elements of the types indicated?");
        Question question2 = new Question("Which is a reserved word in the Java programming language?");
        Question question3 = new Question("Which one of the following will declare an array and initialize it with five numbers?");
        generatedQuestions.setQuestions(Arrays.asList(question1, question2, question3));

        this.dao.insert(generatedQuestions);
        List<GeneratedQuestions> generatedQuestionsList = this.dao.getAll();
        assertEquals(1, generatedQuestionsList.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() {
        GeneratedQuestions generatedQuestions = this.dao.get(2);
        this.dao.delete(generatedQuestions);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws DaoException {
        GeneratedQuestions generatedQuestions = this.dao.get(2);
        this.dao.update(generatedQuestions);
    }
}
