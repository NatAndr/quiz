package com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.*;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Nat on 01.11.2015.
 */
public abstract class DaoFactory {
    public abstract StudyGroupDao getStudyGroupDao();

    public abstract StudentDao getStudentDao();

    public abstract QuizSetDao getQuizSetDao();

    public abstract QuestionDao getQuestionDao();

    public abstract AnswerDao getAnswerDao();

    public abstract ResultDao getResultDao();

    public abstract QuizStartDao getQuizStartDao();

    public static DaoFactory getDaoFactory() {
        String factory = null;
        Properties props = new Properties();
        try {
            props.load(DaoFactory.class.getClassLoader().getResourceAsStream("quiz.properties"));
            factory = props.getProperty("datasource");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (factory != null) {
            switch (factory) {
                case "MYSQL":
                case "H2":
//                    return new DatabaseDaoFactory();
            }
        }
        return null;
    }
}
