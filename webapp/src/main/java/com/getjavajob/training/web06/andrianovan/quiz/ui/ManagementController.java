package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by user on 21.12.2015.
 */
@Controller
public class ManagementController {

    @Autowired
    private QuizSetService quizSetService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyGroupService studyGroupService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/admin")
    public ModelAndView showAdminPanel() {
        ModelAndView modelAndView = new ModelAndView("management");
        modelAndView.addObject("quizzes", quizSetService.getAll());
        modelAndView.addObject("studyGroups", studyGroupService.getAll());
        modelAndView.addObject("students", studentService.getAll());
        modelAndView.addObject("questions", questionService.getAll());
        modelAndView.addObject("answers", answerService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/questionsManagement", method = RequestMethod.POST)
    public String processQuiz(ModelMap model) {
        model.addAttribute("questions", questionService.getAll());
        return "questions";
    }
}
