package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;

/**
 * Created by Nat on 09.11.2015.
 */
public class StudyGroupService extends AbstractService<StudyGroup> {

    public StudyGroupService() {
        super(DaoFactory.getDaoFactory().getStudyGroupDao());
    }

}
