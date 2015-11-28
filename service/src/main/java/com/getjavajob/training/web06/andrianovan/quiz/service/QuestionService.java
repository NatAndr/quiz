package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.AnswerDao;
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
        for (Answer answer : entity.getAnswers()) {
//            answerService.insert(answer);
            try {
                answerDao.insert(answer);
            } catch (DaoException e) {
                throw new ServiceException(CANNOT_INSERT + entity + e.getLocalizedMessage());
            }
        }
        this.update(entity);
    }

    @Override
    public void update(Question entity) throws ServiceException {
        super.update(entity);
        for (Answer answer : entity.getAnswers()) {
            try {
                answerDao.updateQuestionId(answer, entity);
            } catch (DaoException e) {
                throw new ServiceException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
            }
        }
    }

    public void insertAnswerToExistingQuestion(Question question) {
        //todo
    }
}
