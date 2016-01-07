package com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.CrudDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nat on 30.10.2015.
 */
public abstract class AbstractDao<T extends BaseEntity> implements CrudDao<T> {

    private static final String CANNOT_GET = "Cannot get ";
    private Map<Class<?>, Integer> typesMap = new HashMap<>();
    protected static final String CANNOT_INSERT = "Cannot insert ";
    protected static final String CANNOT_UPDATE = "Cannot update ";
    protected static final String CANNOT_CREATE_INSTANCE = "Cannot create instance for ";

    private DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    public AbstractDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public AbstractDao() {
    }

    protected abstract String getTableName();

    protected abstract String getInsertStatement();

    protected abstract String getUpdateByIdStatement();

    protected abstract T createInstanceFromResult(ResultSet resultSet) throws DaoException;

    protected abstract Object[] getEntityFields(T entity);

    protected String getSelectAllStatement() {
        return "SELECT * FROM " + getTableName();
    }

    protected String getSelectByIdStatement() {
        return getSelectAllStatement() + " WHERE id = ?";
    }

    protected String getDeleteByIdStatement() {
        return "DELETE FROM " + getTableName() + " WHERE id = ?";
    }

    @Override
    public T get(int id) {
        try {
            return this.jdbcTemplate.queryForObject(getSelectByIdStatement(), new Object[]{id}, new RowMapper<T>() {
                @Override
                public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                    try {
                        return createInstanceFromResult(rs);
                    } catch (DaoException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<T> getAll() {
        return this.jdbcTemplate.query(getSelectAllStatement(), new RowMapper<T>() {
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                try {
                    return createInstanceFromResult(rs);
                } catch (DaoException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    @Override
    public void delete(T entity) throws DaoException {
        this.jdbcTemplate.update(getDeleteByIdStatement(), Long.valueOf(entity.getId()));
    }

    @Override
    public void insert(final T entity) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final Object[] entityFields = getEntityFields(entity);
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(getInsertStatement(), new String[]{"id"});
                for (int i = 0; i < entityFields.length; i++) {
                    ps.setObject(i + 1, entityFields[i]);
                }
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(T entity) throws DaoException {
        this.jdbcTemplate.update(getUpdateByIdStatement(), getEntityFields(entity), Long.valueOf(entity.getId()));
    }

    protected List<T> doExecuteQueryWithParams(String query, Object[] params) throws DaoException {
        return this.jdbcTemplate.query(query, params, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                try {
                    return createInstanceFromResult(rs);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    protected List<T> doExecuteQueryWithoutParams(String query) throws DaoException {
        return this.jdbcTemplate.query(query, new RowMapper<T>() {
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                try {
                    return createInstanceFromResult(rs);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

}
