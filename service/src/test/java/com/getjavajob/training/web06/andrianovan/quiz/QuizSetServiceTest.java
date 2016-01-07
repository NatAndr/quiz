package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
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
public class QuizSetServiceTest {

    private static final int ROWS_NUMBER = 2;
    @InjectMocks
    private QuizSetService quizSetService;
    @Mock
    private QuizSetDao dao;

    @Test
    public void testGet() {
        QuizSet expected = new QuizSet("New quiz");
        when(this.dao.get(1)).thenReturn(expected);

        QuizSet actual = this.quizSetService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<QuizSet> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new QuizSet());
        }
        when(this.dao.getAll()).thenReturn(expected);

        List<QuizSet> actual = this.quizSetService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException, ServiceException {
        QuizSet quizSet = new QuizSet("Quiz");
        Question question1 = new Question("question1");
        Question question2 = new Question("question2");
        Answer answer1 = new Answer("Answer1");
        Answer answer2 = new Answer("Answer2");
        question1.setAnswers(Collections.singletonList(answer1));
        question2.setAnswers(Collections.singletonList(answer2));
        quizSet.setQuestions(Arrays.asList(question1, question2));
        this.quizSetService.insert(quizSet);
        verify(this.dao).insert(quizSet);
    }

    @Test
    public void testUpdate() throws DaoException, ServiceException {
        QuizSet quizSet = new QuizSet("Quiz");
        Question question1 = new Question("question1");
        Question question2 = new Question("question2");
        quizSet.setQuestions(Arrays.asList(question1, question2));
        this.quizSetService.update(quizSet);
        verify(this.dao).update(quizSet);
    }

    @Test
    public void testDelete() throws ServiceException, DaoException {
        QuizSet quizSet = new QuizSet();
        this.quizSetService.delete(quizSet);
        verify(this.dao).delete(quizSet);
    }
}
