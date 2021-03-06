package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 03.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:quiz-context-dao.xml", "classpath:quiz-context-dao-overrides.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class QuizSetDaoTest {

    private static final int ROWS_NUMBER = 2;
    private static final String VALUE_FOR_ID_1 = "Java Programming. Language Fundamentals";
    private static final String INSERTED_VALUE = "Ботаника";
    private static final String UPDATED_NEW_VALUE = "Математика";
    @Autowired
    private QuizSetDao dao;
    @Autowired
    private QuestionDao questionDao;

    @Test
    public void testGetByID() {
        QuizSet quizHeader = this.dao.get(1);
        assertEquals(VALUE_FOR_ID_1, quizHeader.getName());
    }

    @Test
    public void testGetAll() {
        List<QuizSet> quizHeaderList = this.dao.getAll();
        assertEquals(ROWS_NUMBER, quizHeaderList.size());
    }

    @Test
    public void testInsert() throws DaoException {
        QuizSet quizHeader = new QuizSet();
        quizHeader.setName(INSERTED_VALUE);
        this.dao.insert(quizHeader);
        List<QuizSet> quizHeaderList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, quizHeaderList.size());
    }

    @Test
    public void testUpdate() throws DaoException {
        QuizSet quizHeader = this.dao.get(1);
        quizHeader.setName(UPDATED_NEW_VALUE);
        this.dao.update(quizHeader);
        QuizSet updatedQuizHeader = this.dao.get(1);
        assertEquals(UPDATED_NEW_VALUE, updatedQuizHeader.getName());
    }

    @Test
    public void testDelete() throws DaoException {
        QuizSet quizSet = this.dao.get(1);
        this.dao.delete(quizSet);
        QuizSet quizSet2 = this.dao.get(1);
        assertNull(quizSet2);
    }
}
