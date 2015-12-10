package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.ResultDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Nat on 12.11.2015.
 */
public class ResultServiceTest {

    private static final int ROWS_NUMBER = 2;
    private ResultService resultService;
    private ResultDao resultDao;


    @Before
    public void onBefore() {
        this.resultService = new ResultService();
        this.resultDao = mock(ResultDao.class);
        this.resultService.setDao(resultDao);

        AnswerService answerService = new AnswerService();
        AnswerDao answerDao = mock(AnswerDao.class);
        answerService.setDao(answerDao);
    }

    @Test
    public void testGet() {
        Student student = new Student(new StudyGroup("Group"), "Ivan", "Ivanov");
        QuizSet quizHeader = new QuizSet("Quiz");
        Question question = new Question("New question");
        Answer answer = new Answer("New answer");
        QuizStart quizStart = new QuizStart(quizHeader);
        Result expected = new Result(student, answer, quizStart);
        when(this.resultDao.get(1)).thenReturn(expected);

        Result actual = this.resultService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<Result> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new Result());
        }
        when(this.resultDao.getAll()).thenReturn(expected);

        List<Result> actual = this.resultService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException, ServiceException {
        Result quizHeader = new Result();
        this.resultService.insert(quizHeader);
        verify(this.resultDao).insert(quizHeader);
    }

    @Test
    public void testGetAllAnswersByStudentAndQuestionAndQuizStart() throws DaoException {
        Student student = new Student(new StudyGroup("Group"), "Ivan", "Ivanov");
        QuizSet quizHeader = new QuizSet("Quiz");
        Question question = new Question("New question");
        QuizStart quizStart = new QuizStart(quizHeader);
        Answer answer1 = new Answer("answer 1");
        Answer answer2 = new Answer("answer 2");
        Result result1 = new Result(student, answer1, quizStart);
        Result result2 = new Result(student, answer2, quizStart);
        List<Result> expected = Arrays.asList(result1, result2);

        when(this.resultDao.getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart))
                .thenReturn(expected);
        List<Result> actual = this.resultDao.getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart);
        assertEquals(expected, actual);
    }

    @Test
    public void testCountQuizResult() throws DaoException, ServiceException {
        int expected = 0;
        Student student = new StudentService().get(1);
        QuizSet quizHeader = new QuizSetService().get(1);
        QuizStart quizStart = new QuizStartService().get(3);

        when(this.resultDao.getAllAnswersByStudentAndQuestionAndQuizStart(any(Student.class), any(Question.class),
                any(QuizStart.class))).thenReturn(Collections.EMPTY_LIST);
        int actual = this.resultService.calculateQuizResult(student, quizStart);
        assertEquals(expected, actual);
    }

}
