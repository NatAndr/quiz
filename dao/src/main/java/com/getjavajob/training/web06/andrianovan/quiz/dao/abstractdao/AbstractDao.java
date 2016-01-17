package com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.CrudDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by Nat on 30.10.2015.
 */
public abstract class AbstractDao<T extends BaseEntity> implements CrudDao<T> {

    @Autowired
    protected EntityManager entityManager;

    public AbstractDao() {
    }

    @Override
    public void delete(T entity) throws DaoException {
//            entityManager.remove(entity);
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void insert(final T entity) throws DaoException {
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Override
    public void update(T entity) throws DaoException {
        entityManager.merge(entity);
    }

}
