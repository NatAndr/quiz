package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
public class QuestionDao extends AbstractDao<Question> {
    private static final QuestionDao instance = new QuestionDao();
    private static final String TABLE_NAME = "Question";
    private static final String SELECT_FROM_QUESTION_BY_QUIZ_ID = "SELECT * FROM Question WHERE quiz_id = ?";
    private static final String INSERT = "INSERT INTO Question (question, type, weight)  VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Question SET question=?, type=?, weight=? WHERE id=?";
    private static final String UPDATE_QUIZ_ID = "UPDATE Question SET quiz_id=? WHERE id=?";
    private static final String SELECT_FROM_QUIZ_GENERATED_QUESTIONS_BY_QUIZ_START_ID = "SELECT * FROM " +
            "quiz_generated_questions WHERE quiz_start_id = ?";
    private AnswerDao answerDao = AnswerDao.getInstance();

    private QuestionDao() {
    }

    public static QuestionDao getInstance() {
        return instance;
    }

    @Override
    protected Question createInstanceFromResult(ResultSet resultSet) throws DaoException {

        Question question = new Question();
        try {
            QuestionType questionType = QuestionType.values()[resultSet.getInt("type") - 1];
            question.setId(resultSet.getInt("id"));
            question.setQuestion(resultSet.getString("question"));
            question.setQuestionType(questionType);
            question.setWeight(resultSet.getInt("weight"));
            List<Answer> answers = answerDao.getAnswersByQuestion(question);
            question.setAnswers(answers);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return question;
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

    public List<Question> getQuestionsByQuizSet(QuizSet quizHeader) throws DaoException {
        return super.doExecuteQueryWithParams(SELECT_FROM_QUESTION_BY_QUIZ_ID, new Integer[]{quizHeader.getId()});
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

    public List<Question> getQuestionsFromQuizGeneratedQuestionsByQuizStart(QuizStart quizStart) throws DaoException {
        return super.doExecuteQueryWithParams(SELECT_FROM_QUIZ_GENERATED_QUESTIONS_BY_QUIZ_START_ID, new Integer[]{quizStart.getId()});
    }
}
