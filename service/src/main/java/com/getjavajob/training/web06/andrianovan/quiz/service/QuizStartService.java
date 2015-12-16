package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class QuizStartService extends AbstractService<QuizStart> {

    @Autowired
    public QuizStartService(QuizStartDao dao) {
        super(dao);
    }

    public QuizStartService() {
    }
}
