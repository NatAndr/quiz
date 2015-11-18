package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.CrudDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;

import java.util.List;

/**
 * Created by Nat on 14.11.2015.
 */
public class AbstractService<T extends BaseEntity> {

    private AbstractDao<T> dao;

    protected AbstractService(AbstractDao<T> dao) {
        this.dao = dao;
    }

    public AbstractDao<T> getDao() {
        return dao;
    }

    public void setDao(AbstractDao<T> dao) {
        this.dao = dao;
    }

    public T get(int id) {
        return this.dao.get(id);
    }

    public List<T> getAll() {
        return this.dao.getAll();
    }

    public void delete(T entity) {
        this.dao.delete(entity);
    }

    public void insert(T entity) throws DaoException {
        this.dao.insert(entity);
    }

    public void update(T entity) throws DaoException {
        this.dao.update(entity);
    }
}
