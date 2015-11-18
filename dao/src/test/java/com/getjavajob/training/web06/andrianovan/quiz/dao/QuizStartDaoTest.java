package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 10.11.2015.
 */
public class QuizStartDaoTest {

    private static final int ROWS_NUMBER = 2;
    private QuizStartDao dao = QuizStartDao.getInstance();

    @Before
    public void initDatabase() {
        new DatabaseInitializer().initDatabase();
    }

    @Test
    public void testGetByID() {
        QuizStart quizStart = this.dao.get(2);
        assertEquals("Java Programming. Language Fundamentals", quizStart.getQuizHeader().getQuizName());
    }

    @Test
    public void testGetAll() {
        List<QuizStart> quizStarts = this.dao.getAll();
        assertEquals(ROWS_NUMBER, quizStarts.size());
    }

    @Test
    public void testInsert() throws DaoException {
        QuizStart quizStart = new QuizStart();
        quizStart.setQuizHeader(QuizSetDao.getInstance().get(1));
        this.dao.insert(quizStart);
        List<QuizStart> quizStartList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, quizStartList.size());
    }

    @Test
    public void testUpdate() throws DaoException {
        QuizStart quizStart = dao.get(2);
        quizStart.setQuizHeader(QuizSetDao.getInstance().get(2));
        this.dao.update(quizStart);
        QuizStart updatedQuizStart = this.dao.get(2);
        assertEquals("Vegetables", updatedQuizStart.getQuizHeader().getQuizName());
    }

    @Test
    public void testDelete() {
        QuizStart quizStart = this.dao.get(2);
        this.dao.delete(quizStart);
        QuizStart quizStart2 = this.dao.get(2);
        assertNull(quizStart2);
    }

}
