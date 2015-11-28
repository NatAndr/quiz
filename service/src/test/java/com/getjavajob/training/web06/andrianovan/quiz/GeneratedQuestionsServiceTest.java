package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.GeneratedQuestionsDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.GeneratedQuestionsService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Nat on 24.11.2015.
 */
public class GeneratedQuestionsServiceTest {

    private GeneratedQuestionsService generatedQuestionsService;
    private GeneratedQuestionsDao dao;

    @Before
    public void onBefore() {
        this.generatedQuestionsService = new GeneratedQuestionsService();
        this.dao = mock(GeneratedQuestionsDao.class);
        this.generatedQuestionsService.setDao(dao);
    }

    @Test
    public void testGet() throws Exception {
        QuizSet quizSet = new QuizSet("QuizSet");
        QuizStart quizStart = new QuizStart(quizSet);
        GeneratedQuestions expected = new GeneratedQuestions(quizStart);
        when(this.dao.get(1)).thenReturn(expected);

        GeneratedQuestions actual = this.generatedQuestionsService.get(1);
        assertEquals(expected, actual);
    }

//    @Test
//    public void testGetAll() throws Exception {
//        List<GeneratedQuestions> expected = new ArrayList<>(6);
//        for (int i = 0; i < 6; i++) {
//            expected.add(new GeneratedQuestions());
//        }
//        when(this.dao.getAll()).thenReturn(expected);
//        List<GeneratedQuestions> actual = this.generatedQuestionsService.getAll();
//        assertEquals(expected.size(), actual.size());
//    }

    @Test
    public void testInsert() throws ServiceException, DaoException {
        QuizSet quizSet = new QuizSet("QuizSet");
        QuizStart quizStart = new QuizStart(quizSet);
        GeneratedQuestions generatedQuestions = new GeneratedQuestions(quizStart);
        this.generatedQuestionsService.insert(generatedQuestions);
        verify(this.dao).insert(generatedQuestions);
    }
}
