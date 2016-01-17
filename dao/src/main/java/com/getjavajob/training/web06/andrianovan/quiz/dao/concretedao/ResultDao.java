package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.model.Result;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Nat on 01.11.2015.
 */
@Repository
public class ResultDao extends AbstractDao<Result> {
    private static final String NOT_ALLOWED_TO_UPDATE_RESULT = "It is not allowed to update result";

    public ResultDao() {
    }

    @Override
    public void update(Result entity) throws DaoException {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_UPDATE_RESULT);
    }

    @Override
    public Result get(int id) {
        return entityManager.find(Result.class, id);
    }

    @Override
    public List<Result> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Result> criteriaQuery = criteriaBuilder.createQuery(Result.class);
        CriteriaQuery<Result> select = criteriaQuery.select(criteriaQuery.from(Result.class));
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    public void delete(Result entity) {
        throw new UnsupportedOperationException(NOT_ALLOWED_TO_UPDATE_RESULT);
    }

    public List<Result> getAllAnswersByStudentAndQuestionAndQuizStart(Student student, Question question,
                                                                      QuizStart quizStart) throws DaoException {
        return entityManager.createQuery(
                "SELECT r FROM Result r WHERE r.student = ?1 and r.quizStart = ?2 and r.answer.question = ?3")
                .setParameter(1, student)
                .setParameter(2, quizStart)
                .setParameter(3, question)
                .getResultList();
    }

}
