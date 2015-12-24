package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/admin")
    public ModelAndView showAdminPanel() {
        ModelAndView modelAndView = new ModelAndView("management");
        modelAndView.addObject("quizzes", quizSetService.getAll());
        modelAndView.addObject("studyGroups", studyGroupService.getAll());
        modelAndView.addObject("students", studentService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/studyGroupInfo", method = RequestMethod.GET)
    public ModelAndView showStudyGroup(@RequestParam("id") int id) {
        StudyGroup studyGroup = studyGroupService.get(id);
        ModelAndView modelAndView = new ModelAndView("studyGroupInfo");
        modelAndView.addObject("studyGroup", studyGroup);
        return modelAndView;
    }

    @RequestMapping(value = "/studyGroupEdit", method = RequestMethod.POST)
    public ModelAndView showUpdate(@RequestParam("id") int id) {
        StudyGroup studyGroup = studyGroupService.get(id);
        ModelAndView modelAndView = new ModelAndView("studyGroupAddOrUpdate");
        modelAndView.addObject("studyGroup", studyGroup);
        return modelAndView;
    }

    @RequestMapping(value = "/studyGroupDelete", method = RequestMethod.POST)
    public @ResponseBody String doDelete(@RequestParam("id") int id) throws ServiceException {
        studyGroupService.delete(studyGroupService.get(id));
        return "Deleted id="+id;
    }

    @RequestMapping(value = "/studyGroupAdd", method = RequestMethod.POST)
    public ModelAndView showAdd() {
        ModelAndView modelAndView = new ModelAndView("studyGroupAddOrUpdate");
        modelAndView.addObject("studyGroup", null);
        return modelAndView;
    }

    @RequestMapping(value = "/studyGroupAddOrUpdate", method = RequestMethod.POST)
    public String doUpdate(@ModelAttribute StudyGroup studyGroup) throws ServiceException {
        if (studyGroup.getId() > 0) {
            studyGroupService.update(studyGroup);
        } else {
            studyGroupService.insert(studyGroup);
        }
        return "redirect:/admin";
    }

}
