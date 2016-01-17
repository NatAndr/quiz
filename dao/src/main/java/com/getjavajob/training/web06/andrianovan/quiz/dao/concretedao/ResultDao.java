package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

//    @Autowired
//    public ResultDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
//        super(dataSource, jdbcTemplate);
//    }

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
    protected Object[] getEntityFields(Result entity) {
        return new Object[]{entity.getStudent().getId(), entity.getAnswer().getId(), entity.getInputAnswer(), entity.getQuizStart().getId()};
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
    public void update(Result entity) throws DaoException {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_UPDATE_RESULT);
    }

    @Override
    public Result get(int id) {
        return entityManager.find(Result.class, id);
    }

    @Override
    public List<Result> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Result> criteriaQuery = criteriaBuilder.createQuery(Result.class);
        CriteriaQuery<Result> select = criteriaQuery.select(criteriaQuery.from(Result.class));
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    public void delete(Result entity) {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_UPDATE_RESULT);
    }

    public List<Result> getAllAnswersByStudentAndQuestionAndQuizStart(Student student, Question question,
                                                                      QuizStart quizStart) throws DaoException {
//        Integer[] params = new Integer[]{student.getId(), question.getId(), quizStart.getId()};
//        return super.doExecuteQueryWithParams(SELECT_ALL_STUDENTS_ANSWERS, params);
        return entityManager.createQuery(
                "SELECT r FROM Result r WHERE r.student = ?1 and r.quizStart = ?2 and r.answer.question = ?3")
                .setParameter(1, student)
                .setParameter(2, quizStart)
                .setParameter(3, question)
                .getResultList();
    }

}
