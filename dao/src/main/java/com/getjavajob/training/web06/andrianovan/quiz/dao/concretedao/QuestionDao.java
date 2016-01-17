package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
@Repository
public class QuestionDao extends AbstractDao<Question> {
    public QuestionDao() {
    }

    @Override
    public Question get(int id) {
        return entityManager.find(Question.class, id);
    }

    @Override
    public List<Question> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        CriteriaQuery<Question> select = criteriaQuery.select(criteriaQuery.from(Question.class));
        return entityManager.createQuery(select).getResultList();
    }

}
