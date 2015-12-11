package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
public class StudentDao extends AbstractDao<Student> {

    private static final String TABLE_NAME = "Student";
    private static final StudentDao instance = new StudentDao();
    public static final String INSERT = "INSERT INTO Student (group_id, first_name, last_name)  VALUES (?,?,?)";
    public static final String UPDATE = "UPDATE Student SET group_id=?, first_name=?, last_name=? WHERE id=?";
    private static final String SELECT_FROM_STUDENT_BY_GROUP_ID = "SELECT * FROM Student WHERE group_id = ?";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    private StudentDao() {
    }

    public static StudentDao getInstance() {
        return instance;
    }

    @Override
    protected Student createInstanceFromResult(ResultSet resultSet) throws DaoException {
        Student student = new Student();
        try {
            StudyGroup studyGroup = StudyGroupDao.getInstance().get(resultSet.getInt("group_id"));
            student.setId(resultSet.getInt("id"));
            student.setStudyGroup(studyGroup);
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return student;
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

    public List<Student> getStudentsByStudyGroup(StudyGroup studyGroup) throws DaoException {
        return super.doExecuteQueryWithParams(SELECT_FROM_STUDENT_BY_GROUP_ID, new Integer[]{studyGroup.getId()});
    }
}
