package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
@Repository
public class QuizStartDao extends AbstractDao<QuizStart> {
    @Autowired
    private QuizSetDao quizSetDao;

    public QuizStartDao() {
    }

    @Override
    public void update(QuizStart entity) throws DaoException {
        throw new UnsupportedOperationException("Update is not allowed for " + entity);
    }

    @Override
    public QuizStart get(int id) {
        return entityManager.find(QuizStart.class, id);
    }

    @Override
    public List<QuizStart> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuizStart> criteriaQuery = criteriaBuilder.createQuery(QuizStart.class);
        CriteriaQuery<QuizStart> select = criteriaQuery.select(criteriaQuery.from(QuizStart.class));
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    public void delete(QuizStart entity) {
        throw new UnsupportedOperationException("Delete is not allowed for " + entity);
    }
}
