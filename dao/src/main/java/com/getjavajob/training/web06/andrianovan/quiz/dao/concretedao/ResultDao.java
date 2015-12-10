package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 01.11.2015.
 */
public class ResultDao extends AbstractDao<Result> {

    private static final String TABLE_NAME = "Result";
    private static final ResultDao instance = new ResultDao();
    private static final String INSERT = "INSERT INTO Result (student_id, answer_id, input_answer, quiz_start_id)  " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Result SET student_id=?, answer_id=?, input_answer=?, quiz_start_id=? WHERE id=?";
    private static final String SELECT_ALL_STUDENTS_ANSWERS = "SELECT r.* \n" +
            "FROM Result r\n" +
            "INNER JOIN Answer a ON r.answer_id = a.id\n" +
            "WHERE r.student_id = ? AND a.question_id = ? AND r.quiz_start_id = ?";

    private ResultDao() {
    }

    public static ResultDao getInstance() {
        return instance;
    }

    @Override
    protected Result createInstanceFromResult(ResultSet resultSet) throws DaoException {
        Result result = new Result();
        try {
            Student student = StudentDao.getInstance().get(resultSet.getInt("student_id"));
            Answer answer = AnswerDao.getInstance().get(resultSet.getInt("answer_id"));
            QuizStart quizStart = QuizStartDao.getInstance().get(resultSet.getInt("quiz_start_id"));

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
//        return UPDATE;
        throw new UnsupportedOperationException("It is not allowed to update result");
    }

    @Override
    public void delete(Result entity) {
        throw new UnsupportedOperationException("It is not allowed to delete result");
    }

    public List<Result> getAllAnswersByStudentAndQuestionAndQuizStart(Student student, Question question,
                                                                      QuizStart quizStart) throws DaoException {
        Integer[] params = new Integer[]{student.getId(), question.getId(), quizStart.getId()};
        return super.doExecuteQueryWithParams(SELECT_ALL_STUDENTS_ANSWERS, params);
    }

}
