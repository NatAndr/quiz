package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool.CANNOT_GET_CONNECTION;

/**
 * Created by Nat on 08.11.2015.
 */
public class QuizSetService extends AbstractService<QuizSet> {

    private QuestionService questionService = new QuestionService();
    private QuestionDao questionDao = QuestionDao.getInstance();

    public QuizSetService() {
        super(DaoFactory.getDaoFactory().getQuizSetDao());
    }

    @Override
    public QuizSet get(int id) {
        return super.get(id);
    }

    @Override
    public void insert(QuizSet entity) throws ServiceException {
        //todo транзакции
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try {
                ((QuizSetDao) super.getDao()).insert(entity, connection);
                for (Question question : entity.getQuestions()) {
                    questionDao.insert(question, connection);
                    linkQuestionToQuizSet(entity, question, connection);
                }
                connection.commit();
            } catch (SQLException e) {
                throw new ServiceException(CANNOT_INSERT + entity + e.getLocalizedMessage());
            } finally {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_GET_CONNECTION + e.getLocalizedMessage());
        }
        finally {
            ConnectionPool.getInstance().releaseConnection();
        }
    }

    @Override
    public void update(QuizSet entity) throws ServiceException {
        super.update(entity);
        //todo uncomment
//        for (Question question : entity.getQuestions()) {
//            linkQuestionToQuizSet(entity, question);
//        }
    }

    private void linkQuestionToQuizSet(QuizSet quizSet, Question question, Connection connection) throws ServiceException {
        QuestionDao questionDao = DaoFactory.getDaoFactory().getQuestionDao();
        try {
            questionDao.updateQuestionsQuizId(question, quizSet, connection);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_UPDATE + quizSet + e.getLocalizedMessage());
        }
    }

    public void insertQuestionToExistingQuizSet(QuizSet entity) throws ServiceException {
        for (Question question : entity.getQuestions()) {
            questionService.insert(question);
            //todo uncomment
//            linkQuestionToQuizSet(entity, question);
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
