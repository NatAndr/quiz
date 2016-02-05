package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nat on 10.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:quiz-context-dao.xml", "classpath:quiz-context-dao-overrides.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class QuizStartDaoTest {

    private static final int ROWS_NUMBER = 2;
    @Autowired
    private QuizStartDao dao;
    @Autowired
    private QuizSetDao quizSetDao;

    @Test
    public void testGetByID() {
        QuizStart quizStart = this.dao.get(2);
        assertEquals("Java Programming. Language Fundamentals", quizStart.getQuizSet().getName());
    }

    @Test
    public void testGetAll() {
        List<QuizStart> quizStarts = this.dao.getAll();
        assertEquals(ROWS_NUMBER, quizStarts.size());
    }

    @Test
    public void testInsert() throws DaoException {
        QuizStart quizStart = new QuizStart();
        quizStart.setQuizSet(this.quizSetDao.get(1));
        this.dao.insert(quizStart);
        List<QuizStart> quizStartList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, quizStartList.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() {
        QuizStart quizStart = this.dao.get(2);
        this.dao.delete(quizStart);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws DaoException {
        QuizStart quizStart = this.dao.get(2);
        this.dao.update(quizStart);
    }

}
