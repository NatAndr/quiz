package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.GeneratedQuestionsDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.GeneratedQuestionsService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 24.11.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneratedQuestionsServiceTest {

    @InjectMocks
    private GeneratedQuestionsService generatedQuestionsService;
    @Mock
    private GeneratedQuestionsDao dao;

    @Test
    public void testGet() throws Exception {
        QuizSet quizSet = new QuizSet("QuizSet");
        QuizStart quizStart = new QuizStart(quizSet);
        GeneratedQuestions expected = new GeneratedQuestions(quizStart);
        when(this.dao.get(1)).thenReturn(expected);

        GeneratedQuestions actual = this.generatedQuestionsService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testInsert() throws ServiceException, DaoException {
        QuizSet quizSet = new QuizSet("QuizSet");
        QuizStart quizStart = new QuizStart(quizSet);
        GeneratedQuestions generatedQuestions = new GeneratedQuestions(quizStart);
        this.generatedQuestionsService.insert(generatedQuestions);
        verify(this.dao).insert(generatedQuestions);
    }
}
