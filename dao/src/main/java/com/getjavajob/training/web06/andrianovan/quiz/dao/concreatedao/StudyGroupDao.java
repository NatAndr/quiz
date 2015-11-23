package com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;

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
    protected StudyGroup createInstanceFromResult(ResultSet resultSet) throws DaoException {
        StudyGroup studyGroup = new StudyGroup();
        try {
            studyGroup.setId(resultSet.getInt("id"));
            studyGroup.setGroupName(resultSet.getString("group_name"));
        } catch (SQLException e) {
            throw new DaoException(CANNOT_SET_INSTANCE + this.getClass().getSimpleName());
        }
        return studyGroup;
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
}
