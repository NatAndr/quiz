package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by user on 20.12.2015.
 */
@Controller
public class StudyGroupsController {
    @Autowired
    private StudyGroupService studyGroupService;

    @RequestMapping(value = "/studyGroups", method = RequestMethod.GET)
    public ModelAndView showAllStudyGroups() {
        List<StudyGroup> studyGroups = studyGroupService.getAll();
        ModelAndView modelAndView = new ModelAndView("studyGroups");
        modelAndView.addObject("studyGroups", studyGroups);
        return modelAndView;
    }

//    @RequestMapping(value = "/studyGroupInfo", method = RequestMethod.GET)
//    public ModelAndView showStudyGroup(@RequestParam("id") int id) {
//        StudyGroup studyGroup = studyGroupService.get(id);
//        ModelAndView modelAndView = new ModelAndView("studyGroupInfo");
//        modelAndView.addObject("studyGroup", studyGroup);
//        return modelAndView;
//    }

}
