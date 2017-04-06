package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyGroupService studyGroupService;

    @ResponseBody
    @RequestMapping(value = "/studyGroupsList", method = RequestMethod.POST)
    public List<StudyGroup> getStudyGroupsList() {
        logger.debug("Get study groups list");
        return studyGroupService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/studentDelete", method = RequestMethod.POST)
    public void studentDelete(@RequestParam("id") int id) {
        logger.debug("Going to delete student id = " + id);
        Student student = studentService.get(id);
        try {
            studentService.delete(student);
            logger.debug("Delete student ", student);
        } catch (ServiceException e) {
            errorLogger.error("Cannot delete student ", student);
        }
        logger.debug("End of delete student");
    }

    @ResponseBody
    @RequestMapping(value = "/studentInfo", method = RequestMethod.POST)
    public Student getStudent(@RequestParam("id") int id) {
        logger.debug("Get student info, id = " + id);
        return studentService.get(id);
    }

    @RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
    public
    @ResponseBody
    String studentUpdate(@RequestParam(value = "id") int id,
                         @RequestParam(value = "firstName", required = true) String firstName,
                         @RequestParam(value = "lastName", required = true) String lastName,
                         @RequestParam(value = "login", required = true) String login,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "studyGroupId") int studyGroupId) {
        logger.debug("Going to update student id = " + id);
        StudyGroup studyGroup = studyGroupService.get(studyGroupId);
        Student student = new Student(studyGroup, firstName, lastName, login, DigestUtils.md5Hex(password));
        if (id == 0) {
            try {
                studentService.insert(student);
                logger.debug("Created student " + student);
            } catch (ServiceException e) {
                errorLogger.error("Cannot create student ", student);
            }
        } else {
            student.setId(id);
            try {
                studentService.update(student);
                logger.debug("Updated student " + student);
            } catch (ServiceException e) {
                errorLogger.error("Cannot update student ", student);
            }
        }
        logger.debug("End of update student");
        return "Saved " + firstName + " " + lastName + " " + studyGroup.getName();
    }
}
