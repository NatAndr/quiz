package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
@Repository
public class AnswerDao extends AbstractDao<Answer> {
    public AnswerDao() {
    }

    @Override
    public Answer get(int id) {
        return entityManager.find(Answer.class, id);
    }

    @Override
    public List<Answer> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Answer> criteriaQuery = criteriaBuilder.createQuery(Answer.class);
        CriteriaQuery<Answer> select = criteriaQuery.select(criteriaQuery.from(Answer.class));
        return entityManager.createQuery(select).getResultList();
    }

}
