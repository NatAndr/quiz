package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.AnswerService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 12.11.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    private static final int ROWS_NUMBER = 13;
    @InjectMocks
    private QuestionService questionService;
    @InjectMocks
    private AnswerService answerService;
    @Mock
    private QuestionDao dao;
    @Mock
    private AnswerDao answerDao;

//    @Before
//    public void onBefore() {
////        this.questionService = new QuestionService();
////        this.answerService = new AnswerService();
//
//
//        this.dao = mock(QuestionDao.class);
//        this.questionService.setDao(dao);
//        this.answerDao = mock(AnswerDao.class);
//        this.answerService.setDao(answerDao);
//
////        this.answerService = mock(AnswerService.class);
////        this.questionService.setAnswerService(answerService);
//    }

    @Test
    public void testGet() {
        Question expected = new Question("New question");
        when(this.dao.get(1)).thenReturn(expected);

        Question actual = this.questionService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<Question> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new Question());
        }
        when(this.dao.getAll()).thenReturn(expected);

        List<Question> actual = this.questionService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException, ServiceException {
        QuizSet quizSet = new QuizSet();
        quizSet.setId(2);
        Question question = new Question("Question");
        quizSet.setQuestions(Collections.singletonList(question));
        question.setId(30);
        Answer answer1 = new Answer("Answer3");
        Answer answer2 = new Answer("Answer4");
        question.setAnswers(Arrays.asList(answer1, answer2));

        this.questionService.insert(question);

        verify(this.dao).insert(question);
//        verify(this.answerDao).insert(answer1);
//        verify(this.answerDao).insert(answer2);
    }

    @Test
    public void testUpdate() throws DaoException, ServiceException {
        Question question = new Question("Question");
        Answer answer1 = new Answer("Answer1");
        Answer answer2 = new Answer("Answer2");
        question.setAnswers(Arrays.asList(answer1, answer2));
        this.questionService.update(question);
        verify(this.dao).update(question);
    }

    @Test
    public void testDelete() throws ServiceException, DaoException {
        Question question = new Question();
        this.questionService.delete(question);
        verify(this.dao).delete(question);
    }

//    @Test
//    public void testGetQuestionsByQuiz() {
//        QuizSet quizHeader = new QuizSet("Test");
//        Question question1 = new Question(quizHeader, "Test question");
//        Question question2 = new Question(quizHeader, "Test question 2");
//
//        List<Question> expected = Arrays.asList(question1, question2);
//        when(this.dao.getQuestionsByQuizSet(quizHeader)).thenReturn(expected);
//        List<Question> actual = this.questionService.getQuestionsByQuiz(quizHeader);
//        assertEquals(expected, actual);
//    }
}
