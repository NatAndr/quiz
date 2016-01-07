package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizStartService;
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
 * Created by Nat on 12.11.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuizStartServiceTest {

    private static final int ROWS_NUMBER = 2;
    @InjectMocks
    private QuizStartService quizStartService;
    @Mock
    private QuizStartDao dao;

    @Test
    public void testGet() {
        QuizSet quizHeader = new QuizSet("New quiz");
        QuizStart expected = new QuizStart(quizHeader);
        when(this.dao.get(1)).thenReturn(expected);

        QuizStart actual = this.quizStartService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<QuizStart> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new QuizStart());
        }
        when(this.dao.getAll()).thenReturn(expected);

        List<QuizStart> actual = this.quizStartService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException, ServiceException {
        QuizStart quizHeader = new QuizStart();
        this.quizStartService.insert(quizHeader);
        verify(this.dao).insert(quizHeader);
    }
}
