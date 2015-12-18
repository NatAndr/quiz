package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuestionType;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 03.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { //"classpath:quiz-context.xml",
        "classpath:quiz-context-dao-overrides.xml"})
public class QuestionDaoTest {

    private static final int ROWS_NUMBER = 13;
    private static final String VALUE_FOR_ID_1 = "Which four options describe the correct default values" +
            " for array elements of the types indicated?";
    private static final String INSERTED_VALUE = "Сколько полос у арбуза?";
    private static final String UPDATED_NEW_VALUE = "Зачем?";
    //@Autowired
    private QuestionDao dao;
    //@Autowired
    private QuizSetDao quizSetDao;

//    @Before
//    public void initDatabase() throws DaoException {
//        new DatabaseInitializer().initDatabase();
//    }

    @Test
    public void testGetByID() {
        Question question = this.dao.get(1);
        assertEquals(VALUE_FOR_ID_1, question.getQuestion());
    }

    @Test
    public void testGetAll() {
        List<Question> questionList = this.dao.getAll();
        assertEquals(ROWS_NUMBER, questionList.size());
    }

    @Test
    public void testInsert() throws DaoException {
        Question question = new Question();
        question.setQuestion(INSERTED_VALUE);
        question.setQuestionType(QuestionType.MULTIPLE);
        QuizSet quizHeader = this.quizSetDao.get(1);
//        question.setQuiz(quizHeader);
        this.dao.insert(question);
        List<Question> questionList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, questionList.size());
    }

    @Test
    public void testUpdate() throws DaoException {
        Question question = this.dao.get(1);
        question.setQuestion(UPDATED_NEW_VALUE);
        this.dao.update(question);
        Question question2 = this.dao.get(1);
        assertEquals(UPDATED_NEW_VALUE, question2.getQuestion());
    }

    @Test
    public void testDelete() throws DaoException {
        Question question = this.dao.get(1);
        this.dao.delete(question);
        Question question2 = this.dao.get(1);
        assertNull(question2);
    }

//    @Test
//    public void testGetQuestionsByQuizHeader() {
//        List<Question> actual = dao.getQuestionsByQuizSet(QuizSetDao.getInstance().get(2));
//        List<Question> expected = Arrays.asList(dao.get(11), dao.get(13));
//        assertEquals(expected, actual);
//    }
}
