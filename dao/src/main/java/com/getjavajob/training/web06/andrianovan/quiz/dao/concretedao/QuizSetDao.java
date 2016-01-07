package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
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
public class QuizSetDao extends AbstractDao<QuizSet> {
    private static final String TABLE_NAME = "Quiz_header";
    private static final String INSERT = "INSERT INTO Quiz_header (quiz_name) VALUES (?)";
    private static final String UPDATE = "UPDATE Quiz_header SET quiz_name=? WHERE id=?";
    private static final String SEARCH_QUIZ_SET = "SELECT * FROM Quiz_header WHERE UPPER(quiz_name) LIKE ";
    private static final String GET_QUIZ_SET_BY_QUESTION_ID = "SELECT quiz_id FROM Question WHERE id=?";
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    public QuizSetDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public QuizSetDao() {
    }

    @Override
    protected QuizSet createInstanceFromResult(ResultSet resultSet) throws DaoException {
        QuizSet quiz = new QuizSet();
        try {
            quiz.setId(resultSet.getInt("id"));
            quiz.setName(resultSet.getString("quiz_name"));

            List<Question> questions = questionDao.getQuestionsByQuizSet(quiz);
            quiz.setQuestions(questions);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return quiz;
    }

    @Override
    protected Object[] getEntityFields(QuizSet entity) {
        return new Object[]{entity.getName()};
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

    @Override
    public void update(QuizSet entity) throws DaoException {
        jdbcTemplate.update(getUpdateByIdStatement(), entity.getName(), Long.valueOf(entity.getId()));
    }

//    public List<QuizSet> getQuizSetByQuestion(Question question) throws DaoException {
//        return super.doExecuteQueryWithParams(GET_QUIZ_SET_BY_QUESTION_ID, new Integer[]{question.getId()});
//    }
}
