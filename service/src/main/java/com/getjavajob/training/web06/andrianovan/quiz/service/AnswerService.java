package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
public class AnswerService extends AbstractService<Answer> {

    public AnswerService() {
        super(DaoFactory.getDaoFactory().getAnswerDao());
    }

//    public List<Answer> getAnswersByQuestion(Question question) {
//        return ((AnswerDao) super.getDao()).getAnswersByQuestion(question);
//    }

    public List<Answer> getCorrectAnswerByQuestion(Question question) throws ServiceException {
        try {
            return ((AnswerDao) super.getDao()).getCorrectAnswerByQuestion(question);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get correct answers by question " + question + ": " +
                    e.getLocalizedMessage());
        }
    }

}
