package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.*;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nat on 03.11.2015.
 */
public class ResultDaoTest {

    private static final int ROWS_NUMBER = 8;
    private ResultDao dao;
    private AnswerDao answerDao;
    private StudentDao studentDao;
    private QuizStartDao quizStartDao;
    private QuestionDao questionDao;

    @Before
    public void initDatabase() throws DaoException {
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
        result.setAnswer(this.answerDao.get(1));
        result.setStudent(this.studentDao.get(1));
        result.setQuizStart(this.quizStartDao.get(3));
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
    public void testGetAllAnswersByStudentAndQuestionAndQuizStart() throws DaoException {
        List<Result> actual = dao.getAllAnswersByStudentAndQuestionAndQuizStart(this.studentDao.get(1),
                this.questionDao.get(4), this.quizStartDao.get(2));
        List<Result> expected = Collections.singletonList(dao.get(7));
        assertEquals(expected, actual);
    }
}
