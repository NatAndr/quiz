package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nat on 30.10.2015.
 */
public class StudyGroupDao extends AbstractDao<StudyGroup> {

    private static final String TABLE_NAME = "Study_group";
    private static final StudyGroupDao instance = new StudyGroupDao();
    public static final String INSERT = "INSERT INTO Study_group (group_name) VALUES (?)";
    public static final String UPDATE = "UPDATE Study_group SET group_name=? WHERE id=?";

    private StudyGroupDao() {
    }

    public static StudyGroupDao getInstance() {
        return instance;
    }

    @Override
    protected StudyGroup createInstanceFromResult(ResultSet resultSet) throws SQLException {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setId(resultSet.getInt("id"));
        studyGroup.setGroupName(resultSet.getString("group_name"));
        return studyGroup;
    }

//    @Override
//    public void insert(StudyGroup entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT)) {
//                prepareStatement.setString(1, entity.getGroupName());
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
//    }
//
//    @Override
//    public void update(StudyGroup entity) throws DaoException {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE)) {
//                prepareStatement.setString(1, entity.getGroupName());
//                prepareStatement.setInt(2, entity.getId());
//                prepareStatement.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
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
}
