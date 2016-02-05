package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by user on 21.12.2015.
 */
@Controller
public class ManagementController {
    private static final Logger debugLogger = LoggerFactory.getLogger("DebugLogger");
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
        debugLogger.debug("Show admin panel");
        List<QuizSet> quizSetList = quizSetService.getAll();
        ModelAndView modelAndView = new ModelAndView("management");
        modelAndView.addObject("quizzes", quizSetList);
        modelAndView.addObject("studyGroups", studyGroupService.getAll());
        modelAndView.addObject("students", studentService.getAll());
        modelAndView.addObject("questions", questionService.getAll());
        modelAndView.addObject("answers", answerService.getAll());
        debugLogger.debug("End of show admin panel");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/updateManagement", method = RequestMethod.POST)
    public ModelAndView updateTabs(@RequestParam(value = "tab") String tab) {
        debugLogger.debug("Update admin panel, tab: {}", tab);
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
                debugLogger.debug("No such tab in admin panel");
        }
        debugLogger.debug("End of update admin panel");
        return modelAndView;
    }
}
