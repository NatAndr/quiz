package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;

/**
 * Created by Nat on 09.11.2015.
 */
@Repository
public class QuizStartDao extends AbstractDao<QuizStart> {
    private static final String TABLE_NAME = "quiz_start";
//    private static final String INSERT = "INSERT INTO quiz_start (quiz_id, quiz_date) VALUES (?,?)";
    private static final String INSERT = "INSERT INTO quiz_start (quiz_id) VALUES (?)";
    private static final String UPDATE = "UPDATE quiz_start SET quiz_id=?, quiz_date=? WHERE id=?";
    @Autowired
    private QuizSetDao quizSetDao;

    @Autowired
    public QuizStartDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public QuizStartDao() {
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
    protected QuizStart createInstanceFromResult(ResultSet resultSet) throws DaoException {

        QuizStart quizStart = new QuizStart();
        try {
            QuizSet quiz = this.quizSetDao.get(resultSet.getInt("quiz_id"));
            quizStart.setId(resultSet.getInt("id"));
            quizStart.setQuizSet(quiz);
            Timestamp timestamp = resultSet.getTimestamp("quiz_date");
            Date date = timestamp == null ? null : new Date(timestamp.getTime());
            quizStart.setQuizDate(date);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return quizStart;
    }

    @Override
    @Transactional
    public void insert(final QuizStart entity) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(getInsertStatement(), Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, entity.getQuizSet().getId());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
    }
}