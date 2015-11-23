package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
public class AnswerDao extends AbstractDao<Answer> {

    private static final String TABLE_NAME = "Answer";
    private static final AnswerDao instance = new AnswerDao();
    private static final String INSERT = "INSERT INTO Answer (answer, is_correct) VALUES (?,?)";
    private static final String UPDATE = "UPDATE Answer SET answer=?, is_correct=? WHERE id=?";
    private static final String UPDATE_QUESTION_ID = "UPDATE Answer SET question_id=? WHERE id=?";
    private static final String SELECT_FROM_ANSWER_BY_QUESTION_ID = "SELECT * FROM Answer WHERE question_id = ?";
    private static final String SELECT_CORRECT_ANSWERS_BY_QUESTION_ID = "SELECT * FROM Answer WHERE is_correct = 1 AND question_id = ?";

    private AnswerDao() {
    }

    public static AnswerDao getInstance() {
        return instance;
    }

    @Override
    protected Answer createInstanceFromResult(ResultSet resultSet) throws DaoException {
        Answer answer = new Answer();
        try {
            answer.setId(resultSet.getInt("id"));
            answer.setAnswer(resultSet.getString("answer"));
            answer.setIsCorrect(resultSet.getInt("is_correct") == 1);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_SET_INSTANCE + this.getClass().getSimpleName());
        }
        return answer;
    }

    /*@Override
    public void insert(Answer entity) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT)) {
                prepareStatement.setString(1, entity.getAnswer());
                prepareStatement.setInt(2, entity.getIsCorrect() ? 1 : 0);
                prepareStatement.executeUpdate();
                connection.commit();
                entity.setId(prepareStatement.getGeneratedKeys().getInt("id"));
            } catch (SQLException e) {
                throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
            } finally {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }*/

    @Override
    public void update(Answer entity) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE)) {
                prepareStatement.setString(1, entity.getAnswer());
                prepareStatement.setInt(2, entity.getIsCorrect() ? 1 : 0);
                prepareStatement.setInt(3, entity.getId());
                prepareStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
            } finally {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

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

    public void updateQuestionId(Answer entity, Question question) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUESTION_ID)) {
                prepareStatement.setInt(1, question.getId());
                prepareStatement.setInt(2, entity.getId());
                prepareStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
            } finally {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public List<Answer> getAnswersByQuestion(Question question) throws DaoException {
        int questionID = question.getId();
        int[] params = new int[]{questionID};
        return super.doExecuteQuery(SELECT_FROM_ANSWER_BY_QUESTION_ID, params);
    }

    public List<Answer> getCorrectAnswerByQuestion(Question question) throws DaoException {
        return super.doExecuteQuery(SELECT_CORRECT_ANSWERS_BY_QUESTION_ID, new int[]{question.getId()});
    }

}
