package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private StudentDao() {
    }

    public static StudentDao getInstance() {
        return instance;
    }

    @Override
    protected Student createInstanceFromResult(ResultSet resultSet) throws SQLException {
        StudyGroup studyGroup = StudyGroupDao.getInstance().get(resultSet.getInt("group_id"));
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setStudyGroup(studyGroup);
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        return student;
    }

//    @Override
//    public void insert(Student entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT)) {
//                prepareStatement.setInt(1, entity.getStudyGroup().getId());
//                prepareStatement.setString(2, entity.getFirstName());
//                prepareStatement.setString(3, entity.getLastName());
//                prepareStatement.executeUpdate();
//                connection.commit();
//                entity.setId(prepareStatement.getGeneratedKeys().getInt("id"));
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
//            } finally {
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } finally {
//            ConnectionPool.getInstance().releaseConnection(connection);
//        }
//
//    }
//
//    @Override
//    public void update(Student entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE)) {
//                prepareStatement.setInt(1, entity.getStudyGroup().getId());
//                prepareStatement.setString(2, entity.getFirstName());
//                prepareStatement.setString(3, entity.getLastName());
//                prepareStatement.setInt(4, entity.getId());
//                prepareStatement.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
//            } finally {
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } finally {
//            ConnectionPool.getInstance().releaseConnection(connection);
//        }
//    }

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

    public List<Student> getStudentsByStudyGroup(StudyGroup studyGroup) {
        return super.getEntitiesListByOtherEntity(SELECT_FROM_STUDENT_BY_GROUP_ID, new int[]{studyGroup.getId()});
    }
}
