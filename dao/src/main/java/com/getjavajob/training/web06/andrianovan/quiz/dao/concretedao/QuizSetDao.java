package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
@Repository
public class QuizSetDao extends AbstractDao<QuizSet> {
    @Autowired
    private QuestionDao questionDao;

    public QuizSetDao() {
    }

    public List<QuizSet> searchQuizSetBySubstring(String str) throws DaoException {
        return entityManager.createQuery(
                "SELECT q FROM QuizSet q WHERE UPPER(q.name) LIKE ?1")
                .setParameter(1, "%" + str.toUpperCase() + "%")
                .getResultList();
    }

    @Override
    public QuizSet get(int id) {
        return entityManager.find(QuizSet.class, id);
    }

    @Override
    public List<QuizSet> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuizSet> criteriaQuery = criteriaBuilder.createQuery(QuizSet.class);
        CriteriaQuery<QuizSet> select = criteriaQuery.select(criteriaQuery.from(QuizSet.class));
        return entityManager.createQuery(select).getResultList();
    }

}
