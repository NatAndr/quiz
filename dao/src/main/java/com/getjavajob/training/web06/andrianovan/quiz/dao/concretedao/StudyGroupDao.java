package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nat on 30.10.2015.
 */
@Repository
public class StudyGroupDao extends AbstractDao<StudyGroup> {

    private static final String TABLE_NAME = "Study_group";
    public static final String INSERT = "INSERT INTO Study_group (group_name) VALUES (?)";
    public static final String UPDATE = "UPDATE Study_group SET group_name=? WHERE id=?";

    public StudyGroupDao() {
    }

    @Autowired
    public StudyGroupDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    @Override
    protected StudyGroup createInstanceFromResult(ResultSet resultSet) throws DaoException {
        StudyGroup studyGroup = new StudyGroup();
        try {
            studyGroup.setId(resultSet.getInt("id"));
            studyGroup.setGroupName(resultSet.getString("group_name"));
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
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

    @Override
    @Transactional
    public void insert(final StudyGroup entity) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(getInsertStatement(), new String[]{"id"});
                ps.setString(1, entity.getGroupName());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(StudyGroup entity) throws DaoException {
        super.jdbcTemplate.update(getUpdateByIdStatement(), entity.getGroupName());
    }
}
