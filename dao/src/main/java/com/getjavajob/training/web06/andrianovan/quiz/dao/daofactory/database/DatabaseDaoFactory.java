package com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.database;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.*;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;

/**
 * Created by Nat on 01.11.2015.
 */
public class DatabaseDaoFactory extends DaoFactory {

    @Override
    public StudyGroupDao getStudyGroupDao() {
        return StudyGroupDao.getInstance();
    }

    @Override
    public StudentDao getStudentDao() {
        return StudentDao.getInstance();
    }

    @Override
    public QuizSetDao getQuizSetDao() {
        return QuizSetDao.getInstance();
    }

    @Override
    public QuestionDao getQuestionDao() {
        return QuestionDao.getInstance();
    }

    @Override
    public AnswerDao getAnswerDao() {
        return AnswerDao.getInstance();
    }

    @Override
    public ResultDao getResultDao() {
        return ResultDao.getInstance();
    }

    @Override
    public QuizStartDao getQuizStartDao() {
        return QuizStartDao.getInstance();
    }

    @Override
    public GeneratedQuestionsDao getQuizGeneratedQuestionsDao() {
        return GeneratedQuestionsDao.getInstance();
    }
}
