package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class StudentService extends AbstractService<Student> {

    @Autowired
    public StudentService(StudentDao dao) {
        super(dao);
    }

    public StudentService() {
    }

    public List<Student> getStudentsByStudyGroup(StudyGroup studyGroup) throws ServiceException {
        try {
            return ((StudentDao) super.getDao()).getStudentsByStudyGroup(studyGroup);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get students by group " + studyGroup + e.getLocalizedMessage());
        }
    }
}
