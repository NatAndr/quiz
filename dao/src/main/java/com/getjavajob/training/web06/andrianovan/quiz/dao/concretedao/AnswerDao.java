package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
@Repository
public class AnswerDao extends AbstractDao<Answer> {
    private static final String TABLE_NAME = "Answer";
    private static final String INSERT = "INSERT INTO Answer (answer, is_correct) VALUES (?,?)";
    private static final String UPDATE = "UPDATE Answer SET answer=?, is_correct=? WHERE id=?";
    private static final String UPDATE_QUESTION_ID = "UPDATE Answer SET question_id=? WHERE id=?";
    private static final String SELECT_FROM_ANSWER_BY_QUESTION_ID = "SELECT * FROM Answer WHERE question_id = ?";
    private static final String SELECT_CORRECT_ANSWERS_BY_QUESTION_ID = "SELECT * FROM Answer WHERE is_correct = 1 AND question_id = ?";

    @Autowired
    public AnswerDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public AnswerDao() {
    }

    @Override
    protected Answer createInstanceFromResult(ResultSet resultSet) throws DaoException {
        Answer answer = new Answer();
        try {
            answer.setId(resultSet.getInt("id"));
            answer.setAnswer(resultSet.getString("answer"));
            answer.setIsCorrect(resultSet.getInt("is_correct") == 1);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return answer;
    }

    @Override
    protected Object[] getEntityFields(Answer entity) {
        return new Object[]{entity.getAnswer(), entity.getIsCorrect()};
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
    public void update(Answer entity) throws DaoException {
        jdbcTemplate.update(getUpdateByIdStatement(), entity.getAnswer(), entity.getIsCorrect(), Long.valueOf(entity.getId()));
    }

    public void updateQuestionId(Answer entity, Question question) throws DaoException {
        super.jdbcTemplate.update(UPDATE_QUESTION_ID, question.getId(), entity.getId());
    }

    public List<Answer> getAnswersByQuestion(Question question) throws DaoException {
        return super.doExecuteQueryWithParams(SELECT_FROM_ANSWER_BY_QUESTION_ID, new Integer[]{question.getId()});
    }

    public List<Answer> getCorrectAnswerByQuestion(Question question) throws DaoException {
        return super.doExecuteQueryWithParams(SELECT_CORRECT_ANSWERS_BY_QUESTION_ID, new Integer[]{question.getId()});
    }

}
