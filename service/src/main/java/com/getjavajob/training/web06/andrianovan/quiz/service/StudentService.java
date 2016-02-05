package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Student getStudentByLogin(String login) {
        return ((StudentDao) super.getDao()).getStudentByLogin(login);
    }
}
