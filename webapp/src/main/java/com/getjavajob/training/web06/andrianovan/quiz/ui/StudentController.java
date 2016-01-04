package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 26.12.2015.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyGroupService studyGroupService;

    @ResponseBody
    @RequestMapping(value = "/studyGroupsList", method = RequestMethod.POST)
    public List<StudyGroup> getStudyGroupsList() {
        return studyGroupService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/studentDelete", method = RequestMethod.POST)
    public void studentDelete(@RequestParam("id") int id) throws ServiceException {
        Student student = studentService.get(id);
        studentService.delete(student);
    }

    @ResponseBody
    @RequestMapping(value = "/studentInfo", method = RequestMethod.POST)
    public Student getStudent(@RequestParam("id") int id) {
        return studentService.get(id);
    }

    @RequestMapping(value="/studentUpdate",method=RequestMethod.POST)
    public @ResponseBody String studentUpdate(@RequestParam(value = "id") int id,
                                              @RequestParam(value = "firstName", required = true) String firstName,
                                              @RequestParam(value = "lastName", required = true) String lastName,
                                              @RequestParam(value = "studyGroupId") int studyGroupId ) throws ServiceException {
        StudyGroup studyGroup = studyGroupService.get(studyGroupId);
        Student student = new Student(studyGroup, firstName, lastName);
        if (id == 0) {
            studentService.insert(student);
        } else {
            student.setId(id);
            studentService.update(student);
        }
        return "Saved " + firstName + " " + lastName + " " + studyGroup.getName();
    }
}
