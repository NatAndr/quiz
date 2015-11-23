package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 03.11.2015.
 */
public class AnswerDaoTest {

    private static final int ROWS_NUMBER = 46;
    private static final String VALUE_FOR_ID_1 = "1. int -> 0";
    private static final String INSERTED_VALUE = "red";
    private static final String UPDATED_NEW_VALUE = "grey";
    private AnswerDao dao = AnswerDao.getInstance();

    @Before
    public void initDatabase() throws DaoException {
        new DatabaseInitializer().initDatabase();
    }

    @Test
    public void testGetByID() {
        Answer answer = this.dao.get(1);
        assertEquals(VALUE_FOR_ID_1, answer.getAnswer());
    }

    @Test
    public void testGetAll() {
        List<Answer> answerList = this.dao.getAll();
        assertEquals(ROWS_NUMBER, answerList.size());
    }

    @Test
    public void testInsert() {
        Answer answer = new Answer();
        answer.setId(1);
        answer.setAnswer(INSERTED_VALUE);
        answer.setIsCorrect(false);
        try {
            this.dao.insert(answer);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        List<Answer> answerList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, answerList.size());
    }

    @Test
    public void testUpdate() {
        Answer answer = dao.get(1);
        answer.setAnswer(UPDATED_NEW_VALUE);
        try {
            this.dao.update(answer);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Answer updatedAnswer = this.dao.get(1);
        assertEquals(UPDATED_NEW_VALUE, updatedAnswer.getAnswer());
    }

    @Test
    public void testDelete() throws DaoException {
        Answer answer = this.dao.get(1);
        this.dao.delete(answer);
        Answer newAnswer = this.dao.get(1);
        assertNull(newAnswer);
    }

    //    @Test
//    public void testGetAnswersByQuestion() {
//        List<Answer> actual = dao.getAnswersByQuestion(QuestionDao.getInstance().get(1));
//        List<Answer> expected = Arrays.asList(dao.get(1), dao.get(2), dao.get(3), dao.get(23), dao.get(24));
//        assertEquals(expected, actual);
//    }
//
    @Test
    public void testGetCorrectAnswerByQuestion() throws DaoException {
        List<Answer> actual = dao.getCorrectAnswerByQuestion(QuestionDao.getInstance().get(1));
        List<Answer> expected = Arrays.asList(dao.get(1), dao.get(3), dao.get(23));
        assertEquals(expected, actual);
    }
}
