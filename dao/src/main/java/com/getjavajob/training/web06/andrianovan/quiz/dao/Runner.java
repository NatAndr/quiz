package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;

/**
 * Created by Nat on 31.10.2015.
 */
public class Runner {

    public static void main(String[] args) {
        AbstractDao dao;
        dao = DaoFactory.getDaoFactory().getQuizSetDao();

        System.out.println(dao.getAll());

        dao = DaoFactory.getDaoFactory().getResultDao();
        System.out.println(dao.getAll());

    }
}
