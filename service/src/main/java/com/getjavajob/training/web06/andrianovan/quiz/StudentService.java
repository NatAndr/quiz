package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
public class StudentService extends AbstractService<Student> {

    public StudentService() {
        super(DaoFactory.getDAOFactory().getStudentDao());
    }

    public List<Student> getStudentsByStudyGroup(StudyGroup studyGroup) {
        return ((StudentDao)super.getDao()).getStudentsByStudyGroup(studyGroup);
    }
}
