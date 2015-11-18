package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 12.11.2015.
 */
public class QuestionServiceTest {

    private static final int ROWS_NUMBER = 13;
    private QuestionService questionService;
    private QuestionDao dao;

    @Before
    public void onBefore() {
        this.questionService = new QuestionService();
        this.dao = mock(QuestionDao.class);
        this.questionService.setDao(dao);
    }

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
    public void testInsert() throws DaoException {
        Question question = new Question("Question");
        Answer answer1 = new Answer("Answer1");
        Answer answer2 = new Answer("Answer2");
        question.setAnswers(Arrays.asList(answer1, answer2));
        this.questionService.insert(question);
        verify(this.dao).insert(question);
    }

    @Test
    public void testUpdate() throws DaoException {
        Question question = new Question("Question");
        Answer answer1 = new Answer("Answer1");
        Answer answer2 = new Answer("Answer2");
        question.setAnswers(Arrays.asList(answer1, answer2));
        this.questionService.update(question);
        verify(this.dao).update(question);
    }

    @Test
    public void testDelete() {
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
