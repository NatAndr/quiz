package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.*;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nat on 03.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:quiz-context-dao.xml", "classpath:quiz-context-dao-overrides.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ResultDaoTest {

    private static final int ROWS_NUMBER = 8;
    @Autowired
    private ResultDao dao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private QuizStartDao quizStartDao;
    @Autowired
    private QuestionDao questionDao;

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

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() {
        Result result = this.dao.get(10);
        this.dao.delete(result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws DaoException {
        Result result = this.dao.get(10);
        this.dao.update(result);
    }

    @Test
    public void testGetAllAnswersByStudentAndQuestionAndQuizStart() throws DaoException {
        List<Result> actual = dao.getAllAnswersByStudentAndQuestionAndQuizStart(this.studentDao.get(1),
                this.questionDao.get(4), this.quizStartDao.get(2));
        List<Result> expected = Collections.singletonList(dao.get(7));
        assertEquals(expected, actual);
    }
}
