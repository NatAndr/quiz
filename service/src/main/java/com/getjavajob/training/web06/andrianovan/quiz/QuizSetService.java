package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;

/**
 * Created by Nat on 08.11.2015.
 */
public class QuizSetService extends AbstractService<QuizSet> {

    public QuizSetService() {
        super(DaoFactory.getDAOFactory().getQuizSetDao());
    }

    @Override
    public QuizSet get(int id) {
        return super.get(id);
    }

    @Override
    public void insert(QuizSet entity) throws DaoException {
        QuestionService questionService = new QuestionService();
        super.insert(entity);
        for (Question question : entity.getQuestions()) {
            questionService.insert(question);
        }
    }

    @Override
    public void update(QuizSet entity) throws DaoException {
        QuestionDao questionDao = DaoFactory.getDAOFactory().getQuestionDao();
        super.update(entity);
        for (Question question : entity.getQuestions()) {
            questionDao.updateQuestionsQuizId(question, entity);
        }
    }
}
