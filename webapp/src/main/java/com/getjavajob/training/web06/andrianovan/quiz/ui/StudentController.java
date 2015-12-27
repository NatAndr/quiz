package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 26.12.2015.
 */
@Controller
//@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyGroupService studyGroupService;


    @RequestMapping("/students_")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students_";
    }


}
