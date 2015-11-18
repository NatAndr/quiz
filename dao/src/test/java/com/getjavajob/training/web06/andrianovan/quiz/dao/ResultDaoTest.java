package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.*;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Result;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 03.11.2015.
 */
public class ResultDaoTest {

    private static final int ROWS_NUMBER = 8;
    private ResultDao dao = ResultDao.getInstance();

    @Before
    public void initDatabase() {
        new DatabaseInitializer().initDatabase();
    }

    @Test
    public void testGetByID() {
        Result result = this.dao.get(4);
        assertEquals(2, result.getAnswer().getId());
    }

    @Test
    public void testGetAll() {
        List<Result> resultList = this.dao.getAll();
        assertEquals(ROWS_NUMBER, resultList.size());
    }

    @Test
    public void testInsert() throws DaoException {
        Result result = new Result();
        result.setAnswer(AnswerDao.getInstance().get(1));
        result.setStudent(StudentDao.getInstance().get(1));
        result.setQuizStart(QuizStartDao.getInstance().get(3));
        this.dao.insert(result);
        List<Result> resultList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, resultList.size());
    }

//    @Test
//    public void testUpdate() throws DaoException {
//        Result result = this.dao.get(5);
//        result.setAnswer(AnswerDao.getInstance().get(2));
//        this.dao.update(result);
//        Result updatedResult = this.dao.get(5);
//        assertEquals("2. String -> \"null\"", updatedResult.getAnswer().getAnswer());
//    }

//    @Test
//    public void testDelete() {
//        Result result = this.dao.get(10);
//        this.dao.delete(result);
//        Result result2 = this.dao.get(10);
//        assertNull(result2);
//    }

    @Test
    public void testGetAllAnswersByStudentAndQuestionAndQuizStart() {
        List<Result> actual = dao.getAllAnswersByStudentAndQuestionAndQuizStart(StudentDao.getInstance().get(1),
                QuestionDao.getInstance().get(4), QuizStartDao.getInstance().get(2));
        List<Result> expected = Collections.singletonList(dao.get(7));
        assertEquals(expected, actual);
    }
}
