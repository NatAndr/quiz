package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by user on 20.12.2015.
 */
@Controller
public class QuizzesController {

    @Autowired
    private QuizSetService quizSetService;

    @RequestMapping(value = "/search")
    public ModelAndView showQuizzesSearch() {
        return new ModelAndView("quizzesSearch");
    }

    @RequestMapping(value = "/quizzesSearch", method = RequestMethod.GET)
    public ModelAndView doQuizzesSearch(@RequestParam("searchParams") String searchParams) {
        ModelAndView modelAndView = new ModelAndView("quizzesSearch");
        if (searchParams != null) {
            List<QuizSet> quizes;
            try {
                quizes = quizSetService.searchQuizSetBySubstring(searchParams);
            } catch (ServiceException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            modelAndView.addObject("foundQuiz", quizes);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/quizInfo", method = RequestMethod.GET)
    public ModelAndView showQuizInfo(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("quizInfo");
        QuizSet quizSet = quizSetService.get(id);
        modelAndView.addObject("quiz", quizSet);
        modelAndView.addObject("questionsNumber", quizSet.getQuestions().size());
        return modelAndView;
    }
}
