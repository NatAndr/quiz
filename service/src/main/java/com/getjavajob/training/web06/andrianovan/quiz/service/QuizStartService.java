package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;

/**
 * Created by Nat on 09.11.2015.
 */
public class QuizStartService extends AbstractService<QuizStart> {

    public QuizStartService() {
        super(DaoFactory.getDaoFactory().getQuizStartDao());
    }
}
