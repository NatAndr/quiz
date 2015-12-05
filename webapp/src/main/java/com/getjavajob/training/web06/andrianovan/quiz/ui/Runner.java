package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizStartService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

/**
 * Created by user on 29.11.2015.
 */
public class Runner {

    public static void main(String[] args) throws ServiceException {
        QuizStartService service = new QuizStartService();
        QuizSetService quizSetService = new QuizSetService();
        QuizStart quizStart = new QuizStart(quizSetService.get(1));
        service.insert(quizStart);
    }

}
