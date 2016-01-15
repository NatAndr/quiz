package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ResponseBody
    @RequestMapping(value = "/updateManagement", method = RequestMethod.POST)
    public ModelAndView updateTabs(@RequestParam(value = "tab") String tab) {
        ModelAndView modelAndView = new ModelAndView();
        switch (tab) {
            case "students":
                modelAndView.setViewName("students");
                modelAndView.addObject("students", studentService.getAll());
                break;
            case "studyGroups":
                modelAndView.setViewName("studyGroups");
                modelAndView.addObject("studyGroups", studyGroupService.getAll());
                break;
            case "answers":
                modelAndView.setViewName("answers");
                modelAndView.addObject("answers", answerService.getAll());
                modelAndView.addObject("questions", questionService.getAll());
                break;
            case "questions":
                modelAndView.setViewName("questions");
                modelAndView.addObject("questions", questionService.getAll());
                modelAndView.addObject("quizzes", quizSetService.getAll());
                break;
            case "quizSets":
                modelAndView.setViewName("quizSets");
                modelAndView.addObject("quizzes", quizSetService.getAll());
                break;
            default:
                System.out.println("no tab");
        }
        return modelAndView;
    }
}
