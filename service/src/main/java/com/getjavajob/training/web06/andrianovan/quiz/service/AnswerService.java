package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class AnswerService extends AbstractService<Answer> {

    @Autowired
    protected AnswerService(AnswerDao dao) {
        super(dao);
    }

    public AnswerService() {
    }

    public List<Answer> getCorrectAnswerByQuestion(Question question) throws ServiceException {
        try {
            return ((AnswerDao) super.getDao()).getCorrectAnswerByQuestion(question);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get correct answers by question " + question + ": " +
                    e.getLocalizedMessage());
        }
    }

}
