package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
public class StudentService extends AbstractService<Student> {

    public StudentService() {
        super(DaoFactory.getDaoFactory().getStudentDao());
    }

    public List<Student> getStudentsByStudyGroup(StudyGroup studyGroup) throws ServiceException {
        try {
            return ((StudentDao) super.getDao()).getStudentsByStudyGroup(studyGroup);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get students by group " + studyGroup + e.getLocalizedMessage());
        }
    }
}
