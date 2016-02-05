package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Nat on 31.10.2015.
 */
@Repository
public class StudentDao extends AbstractDao<Student> {
    public StudentDao() {
    }

    @Override
    public Student get(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        CriteriaQuery<Student> select = criteriaQuery.select(criteriaQuery.from(Student.class));
        return entityManager.createQuery(select).getResultList();
    }

    public Student getStudentByLogin(String login) {
        Student student = null;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        CriteriaQuery<Student> select = criteriaQuery.where(criteriaBuilder.equal(studentRoot.get("login"), login));
        try {
            student = entityManager.createQuery(select).getSingleResult();
        } catch (NoResultException e) {

        }
        return student;
    }
}
