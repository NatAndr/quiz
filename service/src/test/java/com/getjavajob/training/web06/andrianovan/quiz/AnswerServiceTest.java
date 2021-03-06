package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.AnswerService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 11.11.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceTest {

    private static final int ROWS_NUMBER = 46;
    @InjectMocks
    private AnswerService answerService;
    @Mock
    private AnswerDao dao;

    @Test
    public void testGet() {
        QuizSet quizHeader = new QuizSet("Quiz");
        Question question = new Question("New question");
        Answer expected = new Answer("New answer");

        when(this.dao.get(1)).thenReturn(expected);

        Answer actual = this.answerService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<Answer> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new Answer());
        }
        when(this.dao.getAll()).thenReturn(expected);

        List<Answer> actual = this.answerService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException, ServiceException {
        Answer answer = new Answer();
        this.answerService.insert(answer);
        verify(this.dao).insert(answer);
    }
}
