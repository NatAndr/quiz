package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.ResultDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizStartService;
import com.getjavajob.training.web06.andrianovan.quiz.service.ResultService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 12.11.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {

    private static final int ROWS_NUMBER = 2;
    @Mock
    private ResultDao resultDao;
    @Mock
    private QuizSetDao quizSetDao;
    @InjectMocks
    private QuizSetService quizSetService;
//    @Mock
//    private StudentDao studentDao;
    @InjectMocks
    private ResultService resultService;
    @InjectMocks
    private StudentService studentService;
    //    private QuizSetService quizSetService;
    @InjectMocks
    private QuizStartService quizStartService;


//    @Before
//    public void onBefore() {
//        this.resultService = new ResultService();
////        this.resultDao = mock(ResultDao.class);
//        this.resultService.setDao(resultDao);
//
//        studentService = new StudentService();
//        quizStartService = new QuizStartService();
//
////        AnswerService answerService;
////        AnswerDao answerDao = mock(AnswerDao.class);
////        answerService.setDao(answerDao);
//    }

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
        Result result = new Result();
        this.resultService.insert(result);
        verify(this.resultDao).insert(result);
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
//        Student student = this.studentService.get(2);
//        QuizSet quizHeader = this.quizSetService.get(1);
//        QuizStart quizStart = this.quizStartService.get(3);
        Student student = new Student(new StudyGroup("Group"), "Ivan", "Ivanov");
        student.setId(10);
        QuizSet quizSet = new QuizSet("Quiz");
        quizSet.setId(1);
        Question question = new Question("New question", QuestionType.INPUT, 1);
        question.setId(1);
        quizSet.setQuestions(Arrays.asList(question));
        Answer answer = new Answer("New answer");
        QuizStart quizStart = new QuizStart(quizSet);
        quizStart.setId(10);

        when(this.quizSetDao.get(any(int.class))).thenReturn(quizSet);

        when(this.resultDao.getAllAnswersByStudentAndQuestionAndQuizStart(any(Student.class), any(Question.class),
                any(QuizStart.class))).thenReturn(Collections.EMPTY_LIST);
//        when(this.resultService.calculateQuizResult(student, quizStart)).thenReturn(0);
        int actual = this.resultService.calculateQuizResult(student, quizStart);
        assertEquals(expected, actual);
    }

}
