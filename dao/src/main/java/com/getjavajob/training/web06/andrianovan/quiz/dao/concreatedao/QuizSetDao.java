package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
public class QuizSetDao extends AbstractDao<QuizSet> {

    private static final String TABLE_NAME = "Quiz_header";
    private static final QuizSetDao instance = new QuizSetDao();
    private static final String INSERT = "INSERT INTO Quiz_header (quiz_name) VALUES (?)";
    private static final String UPDATE = "UPDATE Quiz_header SET quiz_name=? WHERE id=?";
    private QuestionDao questionDao = QuestionDao.getInstance();

    private QuizSetDao() {
    }

    public static QuizSetDao getInstance() {
        return instance;
    }

    @Override
    protected QuizSet createInstanceFromResult(ResultSet resultSet) throws SQLException {
        QuizSet quiz = new QuizSet();
        quiz.setId(resultSet.getInt("id"));
        quiz.setQuizName(resultSet.getString("quiz_name"));

        List<Question> questions = questionDao.getQuestionsByQuizSet(quiz);
//        List<Question> questions = quiz.getQuestions();
        quiz.setQuestions(questions);
        return quiz;
    }

//    @Override
//    public void insert(QuizSet entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT)) {
//                prepareStatement.setString(1, entity.getQuizName());
//                prepareStatement.executeUpdate();
//                connection.commit();
//                entity.setId(prepareStatement.getGeneratedKeys().getInt("id"));
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
//            } finally {
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } finally {
//            ConnectionPool.getInstance().releaseConnection(connection);
//        }
//    }
//
//    @Override
//    public void update(QuizSet entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE)) {
//                prepareStatement.setString(1, entity.getQuizName());
//                prepareStatement.setInt(2, entity.getId());
//                prepareStatement.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
//            } finally {
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } finally {
//            ConnectionPool.getInstance().releaseConnection(connection);
//        }
//    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getInsertStatement() {
        return INSERT;
    }

    @Override
    protected String getUpdateByIdStatement() {
        return UPDATE;
    }

}
