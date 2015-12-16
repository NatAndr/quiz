package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
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
    private QuestionService questionService;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    public QuizSetService(QuizSetDao dao) {
        super(dao);
    }

    public QuizSetService() {
    }

    @Override
    public QuizSet get(int id) {
        return super.get(id);
    }

    @Override
    public void insert(QuizSet entity) throws ServiceException {
        super.insert(entity);
        for (Question question : entity.getQuestions()) {
            try {
                questionDao.insert(question);
            } catch (DaoException e) {
                throw new ServiceException(CANNOT_INSERT + entity + e.getLocalizedMessage());
            }
            linkQuestionToQuizSet(entity, question);
        }
    }

    @Override
    public void update(QuizSet entity) throws ServiceException {
        super.update(entity);
        for (Question question : entity.getQuestions()) {
            linkQuestionToQuizSet(entity, question);
        }
    }

    private void linkQuestionToQuizSet(QuizSet quizSet, Question question) throws ServiceException {
        QuestionDao questionDao = DaoFactory.getDaoFactory().getQuestionDao();
        try {
            questionDao.updateQuestionsQuizId(question, quizSet);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_UPDATE + quizSet + e.getLocalizedMessage());
        }
    }

    public void insertQuestionToExistingQuizSet(QuizSet entity) throws ServiceException {
        for (Question question : entity.getQuestions()) {
            questionService.insert(question);
            linkQuestionToQuizSet(entity, question);
        }
    }

    public List<QuizSet> searchQuizSetBySubstring(String str) throws ServiceException {
        try {
            return ((QuizSetDao) super.getDao()).searchQuizSetBySubstring(str);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get quiz by substring " + str + e.getLocalizedMessage());
        }
    }
}
