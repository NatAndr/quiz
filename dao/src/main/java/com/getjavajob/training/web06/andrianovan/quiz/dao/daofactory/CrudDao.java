package com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory;

import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.BaseEntity;

import java.util.List;

/**
 * Created by Nat on 30.10.2015.
 */
public interface CrudDao<T extends BaseEntity> {

    T get(int id);

    List<T> getAll();

    void delete(T entity) throws DaoException;

    void insert(T entity) throws DaoException;

    void update(T entity) throws DaoException;
}
