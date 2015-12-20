package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nat on 14.11.2015.
 */
public class AbstractService<T extends BaseEntity> {

    protected static final String CANNOT_DELETE = "Cannot delete ";
    protected static final String CANNOT_INSERT = "Cannot insert ";
    protected static final String CANNOT_UPDATE = "Cannot update ";
    private AbstractDao<T> dao;

    protected AbstractService(AbstractDao<T> dao) {
        this.dao = dao;
    }

    public AbstractService() {
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

    @Transactional
    public void delete(T entity) throws ServiceException {
        try {
            this.dao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_DELETE + entity + e.getLocalizedMessage());
        }
    }

    @Transactional
    public void insert(T entity) throws ServiceException {
        try {
            this.dao.insert(entity);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_INSERT + entity + e.getLocalizedMessage());
        }
    }

    @Transactional
    public void update(T entity) throws ServiceException {
        try {
            this.dao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
        }
    }
}
