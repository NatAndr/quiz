package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.ResultDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class ResultService extends AbstractService<Result> {

    @Autowired
    public ResultService(ResultDao dao) {
        super(dao);
    }

    public ResultService() {
    }

    public List<Result> getAllAnswersByStudentAndQuestionAndQuizStart(Student student, Question question,
                                                                      QuizStart quizStart) throws ServiceException {
        try {
            return ((ResultDao) super.getDao()).getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get student's answers " + e.getLocalizedMessage());
        }
    }

    public int calculateQuizResult(Student student, QuizStart quizStart) throws ServiceException {
        int result = 0;
        QuizSet quizSet = quizStart.getQuizSet();

        for (Question question : quizSet.getQuestions()) {
            List<Result> results = getAllAnswersByStudentAndQuestionAndQuizStart(student, question, quizStart);
            switch (question.getQuestionType()) {
                case SINGLE:
                case MULTIPLE:
                    result += getSingleOrMultipleQuestionResult(results, question);
                    break;
                case INPUT:
                    result += getInputQuestionResult(results, question);
                    break;
            }
        }
        return result;
    }

    protected int getInputQuestionResult(List<Result> results, Question question) throws ServiceException {
        String correctAnswer = question.getCorrectAnswers().get(0).getAnswer();
        if (results.isEmpty()) {
            return 0;
        }
        String actualAnswer = results.get(0).getInputAnswer().trim();
        if (correctAnswer.equalsIgnoreCase(actualAnswer.trim())) {
            return question.getWeight();
        }
        return 0;
    }

    protected int getSingleOrMultipleQuestionResult(List<Result> results, Question question) throws ServiceException {
        List<String> actualAnswers = new ArrayList<>();
        if (results.isEmpty()) {
            return 0;
        }
        for (Result result : results) {
            actualAnswers.add(result.getAnswer().getAnswer());
        }
        List<String> correctAnswers = new ArrayList<>();
        List<Answer> correctAnswerByQuestion = question.getCorrectAnswers();
        for (Answer answer : correctAnswerByQuestion) {
            correctAnswers.add(answer.getAnswer());
        }
        if (CollectionUtils.isEqualCollection(actualAnswers, correctAnswers)) {
            return question.getWeight();
        }
        return 0;
    }

}
