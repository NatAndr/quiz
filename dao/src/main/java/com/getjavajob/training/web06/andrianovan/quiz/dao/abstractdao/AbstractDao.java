package com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.CrudDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public T get(int id) {
        return this.jdbcTemplate.queryForObject(getSelectByIdStatement(), new Object[]{id}, new RowMapper<T>() {
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

    @Override
    @Transactional
    public List<T> getAll() {
        return this.jdbcTemplate.query(getSelectAllStatement(), new RowMapper<T>() {
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

    @Override
    @Transactional
    public void delete(T entity) throws DaoException {
        this.jdbcTemplate.update(getDeleteByIdStatement(), entity.getId());
    }

    @Override
    @Transactional
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
    @Transactional
    public void update(T entity) throws DaoException {
        this.jdbcTemplate.update(getUpdateByIdStatement(), getEntityFields(entity), entity.getId());
    }
//    public void insert(T entity) throws DaoException {
//        try (Connection connection = getDataSource().getConnection()) {
//            Class<?> clazz = entity.getClass();
//            Field[] fields = clazz.getDeclaredFields();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(getInsertStatement(), Statement.RETURN_GENERATED_KEYS)) {
//                for (int i = 0; i < fields.length; i++) {
//                    if (fields[i].getType() == List.class || fields[i].getName().contains("quizDate")) {
//                        continue;
//                    }
//                    Method getMethod = null;
//                    Object fieldValue;
//                    try {
//                        getMethod = clazz.getMethod("get" + capitalizeFirstLetter(fields[i].getName()));
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    }
//                    Object newValue = null;
//                    try {
//                        if (getMethod != null) {
//                            fieldValue = getMethod.invoke(entity);
//                            newValue = getValue(fields[i], fieldValue);
//                        }
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                    prepareStatement.setObject(i + 1, newValue);
//                }
//                prepareStatement.executeUpdate();
//                connection.commit();
//                setGeneratedId(entity, prepareStatement);
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
//            } finally {
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } catch (SQLException e) {
//            throw new DaoException(CANNOT_GET_CONNECTION + e.getLocalizedMessage());
//        }
//    }

//    protected void setGeneratedId(T entity, PreparedStatement prepareStatement) throws SQLException {
//        ResultSet rs = prepareStatement.getGeneratedKeys();
//        int generatedID = -1;
//        if (rs.next()) {
//            generatedID = rs.getInt(1);
//        }
//        if (generatedID > 0) {
//            entity.setId(generatedID);
//        }
//    }

//    public void update(T entity) throws DaoException {
//        int id = entity.getId();
//        Class<?> clazz = entity.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        List<Object> values = new ArrayList<>(fields.length);
//        List<Class<?>> types = new ArrayList<>(fields.length);
//        for (Field field : fields) {
//            if (field.getType() == List.class) {
//                continue;
//            }
//            Method getMethod = null;
//            try {
//                getMethod = clazz.getMethod("get" + capitalizeFirstLetter(field.getName()));
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (getMethod != null) {
//                    Object fieldValue = getMethod.invoke(entity);
//                    Object newValue = getValue(field, fieldValue);
//                    values.add(newValue);
//                    types.add(field.getType());
//                }
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        updateField(values, types, typesMap, id);
//    }

//    private void initTypesMap() {
//        typesMap.put(Date.class, Types.DATE);
//        typesMap.put(String.class, Types.VARCHAR);
//        typesMap.put(Integer.class, Types.INTEGER);
//        typesMap.put(QuestionType.class, Types.INTEGER);
//        typesMap.put(java.util.Date.class, Types.DATE);
//    }

//    private Object getValue(Field field, Object fieldValue) {
//        if (fieldValue == null) {
//            return null;
//        }
//        Object newValue;
//        if (!field.getType().isPrimitive() && !(field.getType() == String.class)
//                && !(field.getType() == Date.class) && !(field.getType().isEnum())) {
//            newValue = ((BaseEntity) fieldValue).getId();
//        } else if (field.getType().isEnum()) {
//            newValue = ((Enum) fieldValue).ordinal() + 1;
//        } else {
//            newValue = fieldValue;
//        }
//        return newValue;
//    }

//    private void updateField(List<Object> values, List<Class<?>> types, Map<Class<?>, Integer> typesMap, int id)
//            throws DaoException {
//        try (Connection connection = getDataSource().getConnection()) {
//            int fieldsQty = values.size();
//            try (PreparedStatement prepareStatement = connection.prepareStatement(getUpdateByIdStatement())) {
//                for (int i = 0; i < values.size(); i++) {
//                    if (values.get(i) == null) {
//                        prepareStatement.setNull(i + 1, typesMap.get(types.get(i)));
//                    } else {
//                        prepareStatement.setObject(i + 1, values.get(i));
//                    }
//                }
//                prepareStatement.setObject(fieldsQty + 1, id);
//                prepareStatement.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                throw new DaoException(CANNOT_INSERT + values + e.getLocalizedMessage());
//            } finally {
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } catch (SQLException e) {
//            throw new DaoException(CANNOT_GET_CONNECTION + e.getLocalizedMessage());
//        }
//    }

//    private String capitalizeFirstLetter(String str) {
//        return str.substring(0, 1).toUpperCase() + str.substring(1);
//    }

    @Transactional
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

    @Transactional
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
