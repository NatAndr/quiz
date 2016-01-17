package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nat on 08.11.2015.
 */
@Service
public class QuizSetService extends AbstractService<QuizSet> {

    @Autowired
    public QuizSetService(QuizSetDao dao) {
        super(dao);
    }

    public QuizSetService() {
    }

    public List<QuizSet> searchQuizSetBySubstring(String str) throws ServiceException {
        try {
            return ((QuizSetDao) super.getDao()).searchQuizSetBySubstring(str);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get quiz by substring " + str + e.getLocalizedMessage());
        }
    }
}
