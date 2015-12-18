package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudyGroupDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class StudyGroupService extends AbstractService<StudyGroup> {

    public StudyGroupService() {
    }

    @Autowired
    public StudyGroupService(StudyGroupDao dao) {
        super(dao);
        System.out.println("StudyGroupService");
    }
}
