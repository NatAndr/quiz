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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.*;

/**
 * Created by user on 25.12.2015.
 */
@Controller
public class LoginController {
    private static final Logger debugLogger = LoggerFactory.getLogger("DebugLogger");
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyGroupService studyGroupService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView showLogin() {
        debugLogger.debug("Show login form");
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/")
    public String showStartPageWhenLoggedIn(HttpServletRequest req) {
        if (isAdmin(getCookieValue(req, COOKIE_USERNAME))) {
            return "redirect:/admin";
        }
        return "redirect:/search";
    }

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rememberMe", required = false) Object rememberMe,
                        HttpServletRequest req, HttpServletResponse resp) {
        debugLogger.debug("Login check");
        HttpSession session = req.getSession();
        Student student = studentService.getStudentByLogin(login);
        Student actualStudent = new Student(login, DigestUtils.md5Hex(password));
        debugLogger.debug("rememberMe = {}", rememberMe);
        if (student != null && student.equals(actualStudent)) {
            if (rememberMe != null) {
                addCookie(resp, COOKIE_USERNAME, login, COOKIE_AGE);
            } else {
                removeCookie(resp, COOKIE_USERNAME);
            }
            session.setAttribute("userName", login);
            Object url = session.getAttribute("requestedURI");
            String redirectUrl = url == null ? "redirect:/search" : "redirect:" + session.getAttribute("requestedURI").toString();
            debugLogger.debug(redirectUrl);
            return redirectUrl;
        } else {
            session.setAttribute("loginMsg", "Wrong username or password");
            session.setAttribute("alertType", "danger");
            debugLogger.debug("Wrong username or password");
            return "redirect:/login";
        }
    }

    private boolean isAdmin(String loginName) {
        return "admin".equals(loginName);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        debugLogger.debug("Logout for " + getCookieValue(req, COOKIE_USERNAME));
        removeCookie(resp, COOKIE_USERNAME);
        removeCookie(resp, COOKIE_PASSWORD);
        HttpSession session = req.getSession();
        session.invalidate();
        debugLogger.debug("End of logout");
        return "redirect:/search";
    }

    @RequestMapping(value = "/registration")
    public ModelAndView showRegistration() {
        debugLogger.debug("Show registration form");
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("studyGroupList", studyGroupService.getAll());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("login") String login,
                             @RequestParam("password") String password,
                             @RequestParam("studyGroupId") int studyGroupId,
                             HttpServletRequest req, HttpServletResponse resp) {
        debugLogger.debug("doRegister");
        HttpSession session = req.getSession();
        if (studentService.getStudentByLogin(login) != null) {
            String errorRegistrationMsg = "User with login '" + login + "' already exists";
            errorLogger.error(errorRegistrationMsg);
            session.setAttribute("errorRegistrationMsg", errorRegistrationMsg);
            return errorRegistrationMsg;
        }
        StudyGroup studyGroup = studyGroupService.get(studyGroupId);
        Student student = new Student(studyGroup, firstName, lastName, login, DigestUtils.md5Hex(password));
        try {
            studentService.insert(student);
            debugLogger.debug("Created user: {}", student);
        } catch (ServiceException e) {
            errorLogger.error("Cannot insert user: {}", student);
        }
        session.setAttribute("alertType", "success");
        String result = "Account '" + login + "' was created";
        session.setAttribute("loginMsg", result);
        debugLogger.debug(result);
        return "ok";
    }
}
