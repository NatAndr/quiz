package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Nat on 09.11.2015.
 */
public class QuizStartDao extends AbstractDao<QuizStart> {
    private static final String TABLE_NAME = "quiz_start";
    private static final String INSERT = "INSERT INTO quiz_start (quiz_id, quiz_date) VALUES (?,?)";
    private static final String UPDATE = "UPDATE quiz_start SET quiz_id=?, quiz_date=? WHERE id=?";
    private static final QuizStartDao instance = new QuizStartDao();

    private QuizStartDao() {
    }

    public static QuizStartDao getInstance() {
        return instance;
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
            QuizSet quiz = QuizSetDao.getInstance().get(resultSet.getInt("quiz_id"));
            quizStart.setId(resultSet.getInt("id"));
            quizStart.setQuizHeader(quiz);
            Timestamp timestamp = resultSet.getTimestamp("quiz_date");
            Date date = timestamp == null ? null : new Date(timestamp.getTime());
            quizStart.setQuizDate(date);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_SET_INSTANCE + this.getClass().getSimpleName());
        }
        return quizStart;
    }

}
