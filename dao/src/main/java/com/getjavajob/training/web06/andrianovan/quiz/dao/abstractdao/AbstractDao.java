package com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.CrudDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.ResultSet;
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

    @Autowired
    protected EntityManager entityManager;
//    @Autowired
//    protected EntityManagerFactory entityManagerFactory;
//
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

//    @Override
//    public T get(int id) {
//        try {
//            return this.jdbcTemplate.queryForObject(getSelectByIdStatement(), new Object[]{id}, new RowMapper<T>() {
//                @Override
//                public T mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    try {
//                        return createInstanceFromResult(rs);
//                    } catch (DaoException e) {
//                        e.printStackTrace();
//                        return null;
//                    }
//                }
//            });
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }

//    @Override
//    public List<T> getAll() {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        return em.createNativeQuery("")
//        try {
//            em.getTransaction().begin();
//            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(T.);
//            CriteriaQuery<T> select = criteriaQuery.select(criteriaQuery.from(Employee.class));
//            List<T> resultList = em.createQuery(select).getResultList();
//            em.getTransaction().commit();
//            return resultList;
//        } finally {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//        }


//        return this.jdbcTemplate.query(getSelectAllStatement(), new RowMapper<T>() {
//            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
//                try {
//                    return createInstanceFromResult(rs);
//                } catch (DaoException e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        });
//    }

    @Override
    public void delete(T entity) throws DaoException {
            entityManager.remove(entity);
    }

    @Override
    public void insert(final T entity) throws DaoException {
            entityManager.persist(entity);
    }

    @Override
    public void update(T entity) throws DaoException {
            entityManager.merge(entity);
    }

    protected List<T> doExecuteQueryWithParams(String query, Object[] params) throws DaoException {
//        return this.jdbcTemplate.query(query, params, new RowMapper<T>() {
//            @Override
//            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
//                try {
//                    return createInstanceFromResult(rs);
//                } catch (DaoException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        });
        return null;
    }

    protected List<T> doExecuteQueryWithoutParams(String query) throws DaoException {
//        return this.jdbcTemplate.query(query, new RowMapper<T>() {
//            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
//                try {
//                    return createInstanceFromResult(rs);
//                } catch (DaoException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        });
        return entityManager.createQuery(query).getResultList();
    }

}
