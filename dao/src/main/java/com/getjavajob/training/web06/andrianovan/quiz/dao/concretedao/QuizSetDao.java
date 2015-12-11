package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
public class QuizSetDao extends AbstractDao<QuizSet> {

    private static final String TABLE_NAME = "Quiz_header";
    private static final QuizSetDao instance = new QuizSetDao();
    private static final String INSERT = "INSERT INTO Quiz_header (quiz_name) VALUES (?)";
    private static final String UPDATE = "UPDATE Quiz_header SET quiz_name=? WHERE id=?";
    private static final String SEARCH_QUIZ_SET = "SELECT * FROM Quiz_header WHERE UPPER(quiz_name) LIKE ";
    private QuestionDao questionDao = QuestionDao.getInstance();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuizSetDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    private QuizSetDao() {
    }

    public static QuizSetDao getInstance() {
        return instance;
    }

    @Override
    protected QuizSet createInstanceFromResult(ResultSet resultSet) throws DaoException {
        QuizSet quiz = new QuizSet();
        try {
            quiz.setId(resultSet.getInt("id"));
            quiz.setQuizName(resultSet.getString("quiz_name"));

            List<Question> questions = questionDao.getQuestionsByQuizSet(quiz);
            quiz.setQuestions(questions);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return quiz;
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

    public List<QuizSet> searchQuizSetBySubstring(String str) throws DaoException {
        String query = SEARCH_QUIZ_SET + "'%" + str.toUpperCase() + "%'";
        return super.doExecuteQueryWithoutParams(query);
    }

    public void insert(QuizSet entity, Connection connection) throws DaoException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, entity.getQuizName());
            prepareStatement.executeUpdate();
            setGeneratedId(entity, prepareStatement);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
        }
    }
}
