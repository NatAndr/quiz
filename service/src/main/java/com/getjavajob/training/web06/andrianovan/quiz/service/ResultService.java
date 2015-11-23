package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.ResultDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory.getDaoFactory;

/**
 * Created by Nat on 09.11.2015.
 */
public class ResultService extends AbstractService<Result> {

    public ResultService() {
        super(getDaoFactory().getResultDao());
    }

    public List<Result> getAllAnswersByStudentAndQuestionAndQuizStart(Student student, Question question,
                                                                      QuizStart quizStart) throws ServiceException {
//        return this.dao.getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart);
        try {
            return ((ResultDao) super.getDao()).getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get student's answers " + e.getLocalizedMessage());
        }
    }

    public int countQuizResult(Student student, QuizStart quizStart) throws ServiceException {
        int quizResult = 0;
//        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        QuizSetService quizHeaderService = new QuizSetService();

//        QuizSet quizHeader = getDaoFactory().getQuizSetDao().get(quizStart.getQuizHeader().getId());
        QuizSet quizSet = quizHeaderService.get(quizStart.getQuizHeader().getId());
//        List<Question> questions = questionService.getQuestionsByQuiz(quizHeader);

//        for (Question question : questions) {
        for (Question question : quizSet.getQuestions()) {
            switch (question.getQuestionType()) {
                case SINGLE:
                case MULTIPLE:
                    quizResult += getSingleOrMultipleQuestionResult(student, quizStart, answerService, question);
                    break;
                case INPUT:
                    quizResult += getInputQuestionResult(student, quizStart, answerService, question);
                    break;
            }
        }
        return quizResult;
    }

    protected int getInputQuestionResult(Student student, QuizStart quizStart,
                                         AnswerService answerService, Question question) throws ServiceException {
        String correctAnswer = answerService.getCorrectAnswerByQuestion(question).get(0).getAnswer().toUpperCase();

        List<Result> results = getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart);
        if (results.isEmpty()) {
            return 0;
        }
        String actualAnswer = results.get(0).getInputAnswer().trim().toUpperCase();
        if (correctAnswer.equals(actualAnswer)) {
            return question.getWeight();
        }
        return 0;
    }

    protected int getSingleOrMultipleQuestionResult(Student student, QuizStart quizStart,
                                                    AnswerService answerService, Question question) throws ServiceException {
        List<String> actualAnswers = new ArrayList<>();
        List<Result> studentResults = this.getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart);
        if (studentResults.isEmpty()) {
            return 0;
        }
        for (Result result : studentResults) {
            actualAnswers.add(result.getAnswer().getAnswer());
        }
        List<String> correctAnswers = new ArrayList<>();
        List<Answer> correctAnswerByQuestion = answerService.getCorrectAnswerByQuestion(question);
        for (Answer answer : correctAnswerByQuestion) {
            correctAnswers.add(answer.getAnswer());
        }
        if (CollectionUtils.isEqualCollection(actualAnswers, correctAnswers)) {
            return question.getWeight();
        }
        return 0;
    }

}
