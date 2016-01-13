package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    private static final String NOT_ALLOWED_TO_UPDATE_RESULT = "It is not allowed to update generated questions";
    private static final String NOT_ALLOWED_TO_DELETE_RESULT = "It is not allowed to delete generated questions";

    @Autowired
    private QuizStartDao quizStartDao;
    @Autowired
    private QuestionDao questionDao;
//    @Autowired
//    public GeneratedQuestionsDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
//        super(dataSource, jdbcTemplate);
//    }

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

    @Override
//    @Transactional
    public void insert(GeneratedQuestions entity) throws DaoException {
        QuizStart quizStart = entity.getQuizStart();
        for (Question question : entity.getQuestions()) {
            jdbcTemplate.update(INSERT, quizStart.getId(), question.getId());
        }
    }

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

    @Override
    protected Object[] getEntityFields(GeneratedQuestions entity) {
        return new Object[]{entity.getQuizStart(), entity.getQuestions()};
    }

    @Override
    public void update(GeneratedQuestions entity) throws DaoException {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_UPDATE_RESULT);
    }

    @Override
    public GeneratedQuestions get(int id) {
        return entityManager.find(GeneratedQuestions.class, id);
    }

    @Override
    public List<GeneratedQuestions> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GeneratedQuestions> criteriaQuery = criteriaBuilder.createQuery(GeneratedQuestions.class);
        CriteriaQuery<GeneratedQuestions> select = criteriaQuery.select(criteriaQuery.from(GeneratedQuestions.class));
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    public void delete(GeneratedQuestions entity) {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_DELETE_RESULT);
    }
}
