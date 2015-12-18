package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 10.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { //"classpath:quiz-context.xml",
        "classpath:quiz-context-dao-overrides.xml"})
public class QuizStartDaoTest {

    private static final int ROWS_NUMBER = 2;
    //@Autowired
    private QuizStartDao dao;
    //@Autowired
    private QuizSetDao quizSetDao;

//    @Before
//    public void initDatabase() throws DaoException {
//        new DatabaseInitializer().initDatabase();
//    }

    @Test
    public void testGetByID() {
        QuizStart quizStart = this.dao.get(2);
        assertEquals("Java Programming. Language Fundamentals", quizStart.getQuizSet().getQuizName());
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

    @Test
    public void testUpdate() throws DaoException {
        QuizStart quizStart = dao.get(2);
        quizStart.setQuizSet(this.quizSetDao.get(2));
        this.dao.update(quizStart);
        QuizStart updatedQuizStart = this.dao.get(2);
        assertEquals("Vegetables", updatedQuizStart.getQuizSet().getQuizName());
    }

    @Test
    public void testDelete() throws DaoException {
        QuizStart quizStart = this.dao.get(2);
        this.dao.delete(quizStart);
        QuizStart quizStart2 = this.dao.get(2);
        assertNull(quizStart2);
    }

}
