package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import java.util.List;

/**
 * Created by user on 29.11.2015.
 */
public class Runner {

    public static void main(String[] args) {
        QuizSetService quizSetService = new QuizSetService();
        String searchParams = "E";
        List<QuizSet> quizes = null;
        try {
            quizes = quizSetService.searchQuizSetBySubstring(searchParams);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(quizes);
    }

}
