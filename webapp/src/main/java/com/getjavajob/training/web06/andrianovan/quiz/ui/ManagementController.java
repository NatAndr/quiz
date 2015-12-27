package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


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
//        ModelAndView modelAndView = new ModelAndView("management");
        ModelAndView modelAndView = new ModelAndView("classic");
        modelAndView.addObject("quizzes", quizSetService.getAll());
        modelAndView.addObject("studyGroups", studyGroupService.getAll());
        modelAndView.addObject("students", studentService.getAll());
        return modelAndView;
    }

    @ModelAttribute("studyGroup")
    public StudyGroup constructStudyGroup() {
        return new StudyGroup();
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
        return "Deleted id = "+id;
    }

    @RequestMapping(value = "/studyGroupAdd", method = RequestMethod.POST)
    public ModelAndView showAdd() {
        ModelAndView modelAndView = new ModelAndView("studyGroupAddOrUpdate");
        modelAndView.addObject("studyGroup", null);
        return modelAndView;
    }

    @RequestMapping(value = "/studyGroupAddOrUpdate", method = RequestMethod.POST)
    public ModelAndView studyGroupAddOrUpdate(@ModelAttribute StudyGroup studyGroup) throws ServiceException {
        if (studyGroup.getId() > 0) {
            studyGroupService.update(studyGroup);
        } else {
            studyGroupService.insert(studyGroup);
        }
//        return "redirect:/admin";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new RedirectView("admin#studyGroup", true));
        return modelAndView;
    }

    @RequestMapping(value = "/studentShowModal", method = RequestMethod.POST)
    public ModelAndView studentShowModal(@RequestParam("id") int id) {
        Student student = studentService.get(id);
        ModelAndView modelAndView = new ModelAndView("studyGroupAddOrUpdate");
        modelAndView.addObject("studyGroup", student);
        return modelAndView;
    }

    @RequestMapping(value = "/studentDelete", method = RequestMethod.GET)
    public ModelAndView studentDelete(@RequestParam("id") int id) throws ServiceException {
        Student student = studentService.get(id);
        studentService.delete(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new RedirectView("management#students", true));
        return modelAndView;
    }



    @RequestMapping(value = "/studentInfo/{id}/view", method = RequestMethod.GET)
    public ModelAndView showFormStudent() {
        ModelAndView modelAndView = new ModelAndView("studentInfo");
        modelAndView.addObject("groups", this.studyGroupService.getAll());
        return new ModelAndView("studentInfo");
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.POST)
    public Student getStudent(@RequestParam("id") int id) {
        return studentService.get(id);
    }

//    @RequestMapping(value = "/group/save.htm", method = RequestMethod.POST)
//    public String save(@ModelAttribute("groupAttribute")Student student, BindingResult result) {
//        if (!result.hasErrors()) {
//            if (student.getId() == null) {
//                log.debug("group save");
//                this.groupsService.addEntity(group);
//            }
//        }
//        return "redirect:/group/list.htm";
//    }

    @RequestMapping(value = "/studentAddOrUpdate", method = RequestMethod.POST)
    public ModelAndView studentAddOrUpdate(@ModelAttribute Student student) throws ServiceException {
        if (student.getId() > 0) {
            studentService.update(student);
        } else {
            studentService.insert(student);
        }
        ModelAndView modelAndView = new ModelAndView();
//        return "redirect:/admin";
        modelAndView.setView(new RedirectView("admin#students", true));
        return modelAndView;
    }

    @ModelAttribute("student")
    public Student constructStudent() {
        return new Student();
    }

    @RequestMapping(value = "/studentAdd", method = RequestMethod.POST)
    public String doAddStudent(@ModelAttribute("student") Student student) throws ServiceException {
        studentService.insert(student);
        return "redirect:/admin";
    }


}
