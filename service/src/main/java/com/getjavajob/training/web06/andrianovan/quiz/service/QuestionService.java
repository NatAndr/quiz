package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Nat on 08.11.2015.
 */
@Service
public class QuestionService extends AbstractService<Question> {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    public QuestionService(QuestionDao dao) {
        super(dao);
    }

    public QuestionService() {
    }

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Transactional
    public void linkAnswerToQuestion(Question question, Answer answer) throws ServiceException {
        try {
            answerDao.updateQuestionId(answer, question);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_UPDATE + question + e.getLocalizedMessage());
        }
    }
}
