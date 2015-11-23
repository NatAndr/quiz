package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

/**
 * Created by Nat on 08.11.2015.
 */
public class QuestionService extends AbstractService<Question> {

    private static AnswerService answerService = new AnswerService();

    public QuestionService() {
        super(DaoFactory.getDaoFactory().getQuestionDao());
    }

    @Override
    public void insert(Question entity) throws ServiceException {
            super.insert(entity);
            for (Answer answer : entity.getAnswers()) {
                answerService.insert(answer);
            }
    }

    @Override
    public void update(Question entity) throws ServiceException {
        super.update(entity);
        for (Answer answer : entity.getAnswers()) {
            try {
                DaoFactory.getDaoFactory().getAnswerDao().updateQuestionId(answer, entity);
            } catch (DaoException e) {
                throw new ServiceException(CANNOT_UPDATE + entity + e.getLocalizedMessage());
            }
        }
    }

//    public List<Question> getQuestionsByQuiz(QuizSet quizHeader) {
//        return ((QuestionDao)super.getDao()).getQuestionsByQuizSet(quizHeader);
//    }

}
