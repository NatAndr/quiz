package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 01.11.2015.
 */
@Repository
public class ResultDao extends AbstractDao<Result> {

    private static final String TABLE_NAME = "Result";
    private static final String INSERT = "INSERT INTO Result (student_id, answer_id, input_answer, quiz_start_id)  " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Result SET student_id=?, answer_id=?, input_answer=?, quiz_start_id=? WHERE id=?";
    private static final String SELECT_ALL_STUDENTS_ANSWERS = "SELECT r.* \n" +
            "FROM Result r\n" +
            "INNER JOIN Answer a ON r.answer_id = a.id\n" +
            "WHERE r.student_id = ? AND a.question_id = ? AND r.quiz_start_id = ?";
    private static final String NOT_ALLOWED_TO_UPDATE_RESULT = "It is not allowed to update result";

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuizStartDao quizStartDao;

    @Autowired
    public ResultDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public ResultDao() {
    }

    @Override
    protected Result createInstanceFromResult(ResultSet resultSet) throws DaoException {
        Result result = new Result();
        try {
            Student student = this.studentDao.get(resultSet.getInt("student_id"));
            Answer answer = this.answerDao.get(resultSet.getInt("answer_id"));
            QuizStart quizStart = this.quizStartDao.get(resultSet.getInt("quiz_start_id"));

            result.setId(resultSet.getInt("id"));
            result.setStudent(student);
            result.setAnswer(answer);
            result.setInputAnswer(resultSet.getString("input_answer"));
            result.setQuizStart(quizStart);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return result;
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

    @Override
    @Transactional
    public void insert(final Result entity) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(getInsertStatement(), new String[]{"id"});
                ps.setInt(1, entity.getStudent().getId());
                ps.setInt(2, entity.getAnswer().getId());
                ps.setString(3, entity.getInputAnswer());
                ps.setInt(4, entity.getQuizStart().getId());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Result entity) throws DaoException {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_UPDATE_RESULT);
    }

    @Override
    public void delete(Result entity) {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_UPDATE_RESULT);
    }

    public List<Result> getAllAnswersByStudentAndQuestionAndQuizStart(Student student, Question question,
                                                                      QuizStart quizStart) throws DaoException {
        Integer[] params = new Integer[]{student.getId(), question.getId(), quizStart.getId()};
        return super.doExecuteQueryWithParams(SELECT_ALL_STUDENTS_ANSWERS, params);
    }

}
