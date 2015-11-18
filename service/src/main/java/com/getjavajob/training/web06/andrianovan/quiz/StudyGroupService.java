package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.StudyGroupDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
public class StudyGroupService extends AbstractService<StudyGroup> {

    public StudyGroupService() {
        super(DaoFactory.getDAOFactory().getStudyGroupDao());
    }

}
