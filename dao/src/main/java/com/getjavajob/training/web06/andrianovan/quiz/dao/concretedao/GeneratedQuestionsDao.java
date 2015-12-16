package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nat on 24.11.2015.
 */
@Repository
public class GeneratedQuestionsDao extends AbstractDao<GeneratedQuestions> {

    private static final String TABLE_NAME = "quiz_generated_questions";
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (quiz_start_id, question_id) VALUES (?,?)";
    @Autowired
    private QuizStartDao quizStartDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    public GeneratedQuestionsDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public GeneratedQuestionsDao() {
    }

    @Override
    protected String getSelectByIdStatement() {
        return getSelectAllStatement() + " WHERE quiz_start_id = ?";
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
        return null;
    }
//
//    @Override
//    public void insert(GeneratedQuestions entity) throws DaoException {
//        QuizStart quizStart = entity.getQuizStart();
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            for (Question question : entity.getQuestions()) {
//                try (PreparedStatement prepareStatement = connection.prepareStatement(getInsertStatement())) {
//                    prepareStatement.setInt(1, quizStart.getId());
//                    prepareStatement.setInt(2, question.getId());
//                    prepareStatement.executeUpdate();
//                    connection.commit();
//                } catch (SQLException e) {
//                    throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
//                } finally {
//                    try {
//                        connection.rollback();
//                    } catch (SQLException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        } finally {
//            ConnectionPool.getInstance().releaseConnection();
//        }
//    }
//
    @Override
    protected GeneratedQuestions createInstanceFromResult(ResultSet resultSet) throws DaoException {
        GeneratedQuestions quizGeneratedQuestions = new GeneratedQuestions();
        try {
            QuizStart quizStart = quizStartDao.get(resultSet.getInt("quiz_start_id"));
            quizGeneratedQuestions.setQuizStart(quizStart);

            List<Question> questions = new ArrayList<>();
            do {
                questions.add(questionDao.get(resultSet.getInt("question_id")));
            } while (resultSet.next());

            quizGeneratedQuestions.setQuestions(questions);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return quizGeneratedQuestions;
    }
//
//    @Override
//    public List<GeneratedQuestions> getAll() {
//        throw new UnsupportedOperationException("Cannot get all records from quiz_generated_questions");
//    }
}
