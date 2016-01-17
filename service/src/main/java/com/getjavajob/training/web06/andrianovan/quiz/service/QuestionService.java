package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nat on 08.11.2015.
 */
@Service
public class QuestionService extends AbstractService<Question> {

    @Autowired
    public QuestionService(QuestionDao dao) {
        super(dao);
    }

    public QuestionService() {
    }
}
