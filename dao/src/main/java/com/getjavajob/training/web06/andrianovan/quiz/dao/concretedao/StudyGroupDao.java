package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Nat on 30.10.2015.
 */
@Repository
public class StudyGroupDao extends AbstractDao<StudyGroup> {
    public StudyGroupDao() {
    }

    @Override
    public StudyGroup get(int id) {
        return entityManager.find(StudyGroup.class, id);
    }

    @Override
    public List<StudyGroup> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudyGroup> criteriaQuery = criteriaBuilder.createQuery(StudyGroup.class);
        CriteriaQuery<StudyGroup> select = criteriaQuery.select(criteriaQuery.from(StudyGroup.class));
        return entityManager.createQuery(select).getResultList();
    }
}
