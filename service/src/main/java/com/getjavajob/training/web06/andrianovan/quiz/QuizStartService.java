package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
public class QuizStartService extends AbstractService<QuizStart>{

    public QuizStartService() {
        super(DaoFactory.getDAOFactory().getQuizStartDao());
    }
}
