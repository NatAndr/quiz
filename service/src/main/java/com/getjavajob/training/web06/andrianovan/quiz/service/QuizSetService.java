package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    @Override
//    @Transactional
//    public void insert(QuizSet entity) throws ServiceException {
//        super.insert(entity);
//        for (Question question : entity.getQuestions()) {
//            try {
//                questionDao.insert(question);
//            } catch (DaoException e) {
//                throw new ServiceException(CANNOT_INSERT + entity + e.getLocalizedMessage());
//            }
//            linkQuestionToQuizSet(entity, question);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void update(QuizSet entity) throws ServiceException {
//        super.update(entity);
//        for (Question question : entity.getQuestions()) {
//            linkQuestionToQuizSet(entity, question);
//        }
//    }

    @Transactional
    public void linkQuestionToQuizSet(QuizSet quizSet, Question question) throws ServiceException {
        try {
            questionDao.updateQuestionsQuizId(question, quizSet);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_UPDATE + quizSet + e.getLocalizedMessage());
        }
    }

//    @Transactional
//    public void insertQuestionToExistingQuizSet(QuizSet entity) throws ServiceException {
//        for (Question question : entity.getQuestions()) {
//            questionService.insert(question);
//            linkQuestionToQuizSet(entity, question);
//        }
//    }

    public List<QuizSet> searchQuizSetBySubstring(String str) throws ServiceException {
        try {
            return ((QuizSetDao) super.getDao()).searchQuizSetBySubstring(str);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get quiz by substring " + str + e.getLocalizedMessage());
        }
    }

//    public List<QuizSet> getQuizSetByQuestion(Question question) throws ServiceException {
//        try {
//            return ((QuizSetDao) super.getDao()).getQuizSetByQuestion(question);
//        } catch (DaoException e) {
//            throw new ServiceException("Cannot get quiz set by question " + e.getLocalizedMessage());
//        }
//    }
}
