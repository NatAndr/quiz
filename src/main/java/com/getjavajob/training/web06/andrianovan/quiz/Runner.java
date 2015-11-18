package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.daofactory.DAOFactory;
import com.getjavajob.training.web06.andrianovan.quiz.daofactory.QuizDAO;

/**
 * Created by Nat on 31.10.2015.
 */
public class Runner {

    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        QuizDAO dao;
        if (daoFactory != null) {
            dao = daoFactory.getResultDao();
            System.out.println(dao.getAll());
        }
    }
}
