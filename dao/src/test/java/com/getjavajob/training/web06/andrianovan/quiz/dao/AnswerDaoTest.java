package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
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
public class AnswerDaoTest {

    private static final int ROWS_NUMBER = 46;
    private static final String VALUE_FOR_ID_1 = "1. int -> 0";
    private static final String INSERTED_VALUE = "red";
    private static final String UPDATED_NEW_VALUE = "grey";
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;

    @Test
    public void testGetByID() {
        Answer answer = this.answerDao.get(1);
        assertEquals(VALUE_FOR_ID_1, answer.getAnswer());
    }

    @Test
    public void testGetAll() {
        List<Answer> answerList = this.answerDao.getAll();
        assertEquals(ROWS_NUMBER, answerList.size());
    }

    @Test

    public void testInsert() throws DaoException {
        Answer answer = new Answer();
        answer.setAnswer(INSERTED_VALUE);
        answer.setIsCorrect(false);
        this.answerDao.insert(answer);
        List<Answer> answerList = this.answerDao.getAll();
        assertEquals(ROWS_NUMBER + 1, answerList.size());
    }

    @Test
    public void testUpdate() throws DaoException {
        Answer answer = answerDao.get(1);
        answer.setAnswer(UPDATED_NEW_VALUE);
        answer.setIsCorrect(false);
        this.answerDao.update(answer);
        Answer updatedAnswer = this.answerDao.get(1);
        assertEquals(UPDATED_NEW_VALUE, updatedAnswer.getAnswer());
    }

    @Test
    public void testDelete() throws DaoException {
        Answer answer = this.answerDao.get(1);
        this.answerDao.delete(answer);
        Answer newAnswer = this.answerDao.get(1);
        assertNull(newAnswer);
    }
}
