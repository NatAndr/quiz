package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;
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
public class QuestionDao extends AbstractDao<Question> {
    private static final String TABLE_NAME = "Question";
    private static final String SELECT_FROM_QUESTION_BY_QUIZ_ID = "SELECT * FROM Question WHERE quiz_id = ?";
    private static final String INSERT = "INSERT INTO Question (question, type, weight, image)  VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Question SET question=?, type=?, weight=?, image=? WHERE id=?";
    private static final String UPDATE_QUIZ_ID = "UPDATE Question SET quiz_id=? WHERE id=?";
    private static final String SELECT_FROM_QUIZ_GENERATED_QUESTIONS_BY_QUIZ_START_ID = "SELECT * FROM " +
            "quiz_generated_questions WHERE quiz_start_id = ?";
    @Autowired
    private AnswerDao answerDao;

    @Autowired
    public QuestionDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public QuestionDao() {
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
            question.setImage(resultSet.getBytes("image"));
            List<Answer> answers = answerDao.getAnswersByQuestion(question);
            question.setAnswers(answers);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return question;
    }

    @Override
    protected Object[] getEntityFields(Question entity) {
        return new Object[]{entity.getQuestion(), entity.getQuestionType().ordinal() + 1, entity.getWeight(), entity.getImage()};
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
    public void update(Question entity) throws DaoException {
        jdbcTemplate.update(getUpdateByIdStatement(), entity.getQuestion(), entity.getQuestionType().ordinal() + 1,
                entity.getWeight(), entity.getImage(), Long.valueOf(entity.getId()));
    }

    public List<Question> getQuestionsByQuizSet(QuizSet quizSet) throws DaoException {
        return super.doExecuteQueryWithParams(SELECT_FROM_QUESTION_BY_QUIZ_ID, new Integer[]{quizSet.getId()});
    }

    public void updateQuestionsQuizId(Question entity, QuizSet quiz) throws DaoException {
        jdbcTemplate.update(UPDATE_QUIZ_ID, quiz.getId(), entity.getId());
    }

    public List<Question> getQuestionsFromQuizGeneratedQuestionsByQuizStart(QuizStart quizStart) throws DaoException {
        return super.doExecuteQueryWithParams(SELECT_FROM_QUIZ_GENERATED_QUESTIONS_BY_QUIZ_START_ID,
                new Integer[]{quizStart.getId()});
    }

}
