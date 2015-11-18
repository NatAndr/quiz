package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 12.11.2015.
 */
public class QuizSetServiceTest {

    private static final int ROWS_NUMBER = 2;
    private QuizSetService quizHeaderService;
    private QuizSetDao dao;

    @Before
    public void onBefore() {
        this.quizHeaderService = new QuizSetService();
        this.dao = mock(QuizSetDao.class);
        this.quizHeaderService.setDao(dao);
    }

    @Test
    public void testGet() {
        QuizSet expected = new QuizSet("New quiz");
        when(this.dao.get(1)).thenReturn(expected);

        QuizSet actual = this.quizHeaderService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<QuizSet> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new QuizSet());
        }
        when(this.dao.getAll()).thenReturn(expected);

        List<QuizSet> actual = this.quizHeaderService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException {
        QuizSet quizSet = new QuizSet("Quiz");
        Question question1 = new Question("question1");
        Question question2 = new Question("question2");
        Answer answer1 = new Answer("Answer1");
        Answer answer2 = new Answer("Answer2");
        question1.setAnswers(Collections.singletonList(answer1));
        question2.setAnswers(Collections.singletonList(answer2));
        quizSet.setQuestions(Arrays.asList(question1, question2));
        this.quizHeaderService.insert(quizSet);
        verify(this.dao).insert(quizSet);
    }

    @Test
    public void testUpdate() throws DaoException {
        QuizSet quizSet = new QuizSet("Quiz");
        Question question1 = new Question("question1");
        Question question2 = new Question("question2");
        quizSet.setQuestions(Arrays.asList(question1, question2));
        this.quizHeaderService.update(quizSet);
        verify(this.dao).update(quizSet);
    }

    @Test
    public void testDelete() {
        QuizSet quizSet = new QuizSet();
        this.quizHeaderService.delete(quizSet);
        verify(this.dao).delete(quizSet);
    }
}
