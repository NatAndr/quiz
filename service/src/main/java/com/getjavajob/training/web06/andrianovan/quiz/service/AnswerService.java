package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.AnswerDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class AnswerService extends AbstractService<Answer> {

    @Autowired
    protected AnswerService(AnswerDao dao) {
        super(dao);
    }

    public AnswerService() {
    }
}
