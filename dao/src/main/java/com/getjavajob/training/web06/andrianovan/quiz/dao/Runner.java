package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuizSetDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;

/**
 * Created by Nat on 31.10.2015.
 */
public class Runner {

    public static void main(String[] args) {
        QuizSetDao dao;
        dao = DaoFactory.getDAOFactory().getQuizSetDao();

        System.out.println(dao.getAll());

    }
}
