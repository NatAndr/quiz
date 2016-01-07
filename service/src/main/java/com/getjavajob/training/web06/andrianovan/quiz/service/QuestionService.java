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
    private AnswerService answerService;
    @Autowired
    private AnswerDao answerDao;

    @Autowired
    public QuestionService(QuestionDao dao) {
        super(dao);
    }

    public QuestionService() {
    }

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    @Transactional
    public void insert(Question entity) throws ServiceException {
        super.insert(entity);
        if (!entity.getAnswers().isEmpty()) {
            insertAnswerToExistingQuestion(entity);
        }
    }

    @Override
    @Transactional
    public void update(Question entity) throws ServiceException {
        super.update(entity);
        if (!entity.getAnswers().isEmpty()) {
            for (Answer answer : entity.getAnswers()) {
                linkAnswerToQuestion(entity, answer);
            }
        }
    }

    @Transactional
    private void linkAnswerToQuestion(Question question, Answer answer) throws ServiceException {
        try {
            answerDao.updateQuestionId(answer, question);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_UPDATE + question + e.getLocalizedMessage());
        }
    }

    @Transactional
    public void insertAnswerToExistingQuestion(Question question) throws ServiceException {
        for (Answer answer : question.getAnswers()) {
            answerService.insert(answer);
            linkAnswerToQuestion(question, answer);
        }
    }
}
