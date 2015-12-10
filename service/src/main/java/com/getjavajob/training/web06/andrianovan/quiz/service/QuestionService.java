package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

/**
 * Created by Nat on 08.11.2015.
 */
public class QuestionService extends AbstractService<Question> {

    private AnswerService answerService = new AnswerService();
    private static AnswerDao answerDao = DaoFactory.getDaoFactory().getAnswerDao();

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public static void setAnswerDao(AnswerDao answerDao) {
        QuestionService.answerDao = answerDao;
    }

    public QuestionService() {
        super(DaoFactory.getDaoFactory().getQuestionDao());
    }

    @Override
    public void insert(Question entity) throws ServiceException {
        super.insert(entity);
        insertAnswerToExistingQuestion(entity);
//        this.update(entity);
    }

    @Override
    public void update(Question entity) throws ServiceException {
        super.update(entity);
        for (Answer answer : entity.getAnswers()) {
            linkAnswerToQuestion(entity, answer);
        }
    }

    private void linkAnswerToQuestion(Question question, Answer answer) throws ServiceException {
        try {
            answerDao.updateQuestionId(answer, question);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_UPDATE + question + e.getLocalizedMessage());
        }
    }

    public void insertAnswerToExistingQuestion(Question question) throws ServiceException {
        for (Answer answer : question.getAnswers()) {
            try {
                answerDao.insert(answer);
            } catch (DaoException e) {
                throw new ServiceException(CANNOT_INSERT + question + e.getLocalizedMessage());
            }
            linkAnswerToQuestion(question, answer);
        }
    }
}
