package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;

import java.util.List;

/**
 * Created by Nat on 08.11.2015.
 */
public class QuestionService extends AbstractService<Question> {

    public QuestionService() {
        super(DaoFactory.getDAOFactory().getQuestionDao());
    }

    @Override
    public void insert(Question entity) throws DaoException {
        super.insert(entity);
        for (Answer answer : entity.getAnswers()) {
            new AnswerService().insert(answer);
        }
    }

    @Override
    public void update(Question entity) throws DaoException {
        super.update(entity);
        for (Answer answer : entity.getAnswers()) {
            DaoFactory.getDAOFactory().getAnswerDao().updateQuestionId(answer, entity);
        }
    }

//    public List<Question> getQuestionsByQuiz(QuizSet quizHeader) {
//        return ((QuestionDao)super.getDao()).getQuestionsByQuizSet(quizHeader);
//    }

}
