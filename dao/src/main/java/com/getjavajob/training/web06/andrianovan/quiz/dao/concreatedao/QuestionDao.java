package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuestionType;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
public class QuestionDao extends AbstractDao<Question> {
    private static final String TABLE_NAME = "Question";
    private static final QuestionDao instance = new QuestionDao();
    private static final String SELECT_FROM_QUESTION_BY_QUIZ_ID = "SELECT * FROM Question WHERE quiz_id = ?";
    private static final String INSERT = "INSERT INTO Question (question, type, weight)  VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Question SET question=?, type=?, weight=? WHERE id=?";
    private static final String UPDATE_QUIZ_ID = "UPDATE Question SET quiz_id=? WHERE id=?";
    private AnswerDao answerDao = AnswerDao.getInstance();

    private QuestionDao() {
    }

    public static QuestionDao getInstance() {
        return instance;
    }

    @Override
    protected Question createInstanceFromResult(ResultSet resultSet) throws SQLException {
        QuestionType questionType = QuestionType.values()[resultSet.getInt("type") - 1];

        Question question = new Question();
        question.setId(resultSet.getInt("id"));
        question.setQuestion(resultSet.getString("question"));
        question.setQuestionType(questionType);
        question.setWeight(resultSet.getInt("weight"));
        List<Answer> answers = answerDao.getAnswersByQuestion(question);
        question.setAnswers(answers);
        return question;
    }

//    @Override
//    public void insert(Question entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT)) {
//                prepareStatement.setString(1, entity.getQuestion());
//                prepareStatement.setInt(2, entity.getQuestionType().ordinal() + 1);
//                prepareStatement.setInt(3, entity.getWeight());
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
//    public void update(Question entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE)) {
//                prepareStatement.setString(1, entity.getQuestion());
//                prepareStatement.setInt(2, entity.getQuestionType().ordinal() + 1);
//                prepareStatement.setInt(3, entity.getWeight());
//                prepareStatement.setInt(4, entity.getId());
//                prepareStatement.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
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

    public List<Question> getQuestionsByQuizSet(QuizSet quizHeader) {
        return super.getEntitiesListByOtherEntity(SELECT_FROM_QUESTION_BY_QUIZ_ID, new int[]{quizHeader.getId()});
    }

    public void updateQuestionsQuizId(Question entity, QuizSet quiz) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUIZ_ID)) {
                prepareStatement.setInt(1, quiz.getId());
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
}
