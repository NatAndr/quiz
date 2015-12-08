package com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.CrudDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.database.DatabaseDaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuestionType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Nat on 30.10.2015.
 */
public abstract class AbstractDao<T extends BaseEntity> extends DatabaseDaoFactory implements CrudDao<T> {

    private Map<Class<?>, Integer> typesMap = new HashMap<>();
    protected static final String CANNOT_INSERT = "Cannot insert ";
    protected static final String CANNOT_UPDATE = "Cannot update ";
    protected static final String CANNOT_CREATE_INSTANCE = "Cannot create instance for ";

    protected AbstractDao() {
        initTypesMap();
    }

    protected abstract String getTableName();

    protected abstract String getInsertStatement();

    protected abstract String getUpdateByIdStatement();

    protected abstract T createInstanceFromResult(ResultSet resultSet) throws DaoException;

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
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement prepareStatement = connection.prepareStatement(getSelectByIdStatement())) {
                prepareStatement.setInt(1, id);
                try (ResultSet resultSet = prepareStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return createInstanceFromResult(resultSet);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstance().releaseConnection();
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        Connection connection = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try (ResultSet resultSet = connection.createStatement().executeQuery(getSelectAllStatement())) {
                List<T> resultList = new ArrayList<>();
                while (resultSet.next()) {
                    resultList.add(createInstanceFromResult(resultSet));
                }
                return resultList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstance().releaseConnection();
        }
    }

    @Override
    public void delete(T entity) throws DaoException {
        int id = entity.getId();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement prepareStatement = connection.prepareStatement(getDeleteByIdStatement())) {
                prepareStatement.setInt(1, id);
                prepareStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            ConnectionPool.getInstance().releaseConnection();
        }
    }

    @Override
    public void insert(T entity) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Class<?> clazz = entity.getClass();
            Field[] fields = clazz.getDeclaredFields();
            try (PreparedStatement prepareStatement = connection.prepareStatement(getInsertStatement(), Statement.RETURN_GENERATED_KEYS)) {
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].getType() == List.class || fields[i].getName().contains("quizDate")) {
                        continue;
                    }
                    Method getMethod = null;
                    Object fieldValue;
                    try {
                        getMethod = clazz.getMethod("get" + capitalizeFirstLetter(fields[i].getName()));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Object newValue = null;
                    try {
                        if (getMethod != null) {
                            fieldValue = getMethod.invoke(entity);
                            newValue = getValue(fields[i], fieldValue);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
//                    if (newValue == null) {
//                        prepareStatement.setNull(i + 1, typesMap.get(fields[i].getType()));
//                    } else {
                        prepareStatement.setObject(i + 1, newValue);
//                    }
                }
                prepareStatement.executeUpdate();
                connection.commit();
                setGeneratedId(entity, prepareStatement);
            } catch (SQLException e) {
                throw new DaoException(CANNOT_INSERT + entity + e.getLocalizedMessage());
            } finally {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            ConnectionPool.getInstance().releaseConnection();
        }
    }

    private void setGeneratedId(T entity, PreparedStatement prepareStatement) throws SQLException {
        ResultSet rs = prepareStatement.getGeneratedKeys();
        int generatedID = -1;
        if (rs.next()) {
            generatedID = rs.getInt(1);
        }
        if (generatedID > 0) {
            entity.setId(generatedID);
        }
    }

    @Override
    public void update(T entity) throws DaoException {
        int id = entity.getId();
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<Object> values = new ArrayList<>(fields.length);
        List<Class<?>> types = new ArrayList<>(fields.length);
        for (Field field : fields) {
            if (field.getType() == List.class) {
                continue;
            }
            Method getMethod = null;
            try {
                getMethod = clazz.getMethod("get" + capitalizeFirstLetter(field.getName()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                if (getMethod != null) {
                    Object fieldValue = getMethod.invoke(entity);
                    Object newValue = getValue(field, fieldValue);
                    values.add(newValue);
                    types.add(field.getType());
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        updateField(values, types, typesMap, id);
    }

    private void initTypesMap() {
        typesMap.put(Date.class, Types.DATE);
        typesMap.put(String.class, Types.VARCHAR);
        typesMap.put(Integer.class, Types.INTEGER);
        typesMap.put(QuestionType.class, Types.INTEGER);
        typesMap.put(java.util.Date.class, Types.DATE);
    }

    private Object getValue(Field field, Object fieldValue) {
        if (fieldValue == null) {
            return null;
        }
        Object newValue;
        if (!field.getType().isPrimitive() && !(field.getType() == String.class)
                && !(field.getType() == Date.class) && !(field.getType().isEnum())) {
            newValue = ((BaseEntity) fieldValue).getId();
        } else if (field.getType().isEnum()) {
            newValue = ((Enum) fieldValue).ordinal() + 1;
        } else {
            newValue = fieldValue;
        }
        return newValue;
    }

    private void updateField(List<Object> values, List<Class<?>> types, Map<Class<?>, Integer> typesMap, int id)
            throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            int fieldsQty = values.size();
            try (PreparedStatement prepareStatement = connection.prepareStatement(getUpdateByIdStatement())) {
                for (int i = 0; i < values.size(); i++) {
                    if (values.get(i) == null) {
                        prepareStatement.setNull(i + 1, typesMap.get(types.get(i)));
                    } else {
                        prepareStatement.setObject(i + 1, values.get(i));
                    }
                }
                prepareStatement.setObject(fieldsQty + 1, id);
                prepareStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException(CANNOT_INSERT + values + e.getLocalizedMessage());
            } finally {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            ConnectionPool.getInstance().releaseConnection();
        }
    }

    private String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    protected List<T> doExecuteQueryWithParams(String query, Object[] params) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
//                    prepareStatement.setInt(i + 1, params[i]);
                    prepareStatement.setObject(i + 1, params[i]);
                }
                try (ResultSet resultSet = prepareStatement.executeQuery()) {
                    List<T> resultList = new ArrayList<>();
                    while (resultSet.next()) {
                        resultList.add(createInstanceFromResult(resultSet));
                    }
                    return resultList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstance().releaseConnection();
        }
    }

    protected List<T> doExecuteQueryWithoutParams(String query) throws DaoException {
        Connection connection;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try (ResultSet resultSet = connection.createStatement().executeQuery(query)) {
                List<T> resultList = new ArrayList<>();
                while (resultSet.next()) {
                    resultList.add(createInstanceFromResult(resultSet));
                }
                return resultList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstance().releaseConnection();
        }
    }
}
