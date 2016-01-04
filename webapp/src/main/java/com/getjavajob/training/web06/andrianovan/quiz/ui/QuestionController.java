package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by user on 04.01.2016.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizSetService quizSetService;


}
