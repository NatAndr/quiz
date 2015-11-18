package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;

import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
public class AnswerService extends AbstractService<Answer> {

    public AnswerService() {
        super(DaoFactory.getDAOFactory().getAnswerDao());
    }

//    public List<Answer> getAnswersByQuestion(Question question) {
//        return ((AnswerDao) super.getDao()).getAnswersByQuestion(question);
//    }

    public List<Answer> getCorrectAnswerByQuestion(Question question) {
        return ((AnswerDao) super.getDao()).getCorrectAnswerByQuestion(question);
    }

}
