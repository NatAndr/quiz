package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

/**
 * Created by Nat on 08.11.2015.
 */
public class QuizSetService extends AbstractService<QuizSet> {

    private static QuestionService questionService = new QuestionService();

    public QuizSetService() {
        super(DaoFactory.getDaoFactory().getQuizSetDao());
    }

    @Override
    public QuizSet get(int id) {
        return super.get(id);
    }

    @Override
    public void insert(QuizSet entity) throws ServiceException {
        super.insert(entity);
        for (Question question : entity.getQuestions()) {
            questionService.insert(question);
        }
    }

    @Override
    public void update(QuizSet entity) throws ServiceException {
        QuestionDao questionDao = DaoFactory.getDaoFactory().getQuestionDao();
        super.update(entity);
        for (Question question : entity.getQuestions()) {
            try {
                questionDao.updateQuestionsQuizId(question, entity);
            } catch (DaoException e) {
                throw new ServiceException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
            }
        }
    }
}
