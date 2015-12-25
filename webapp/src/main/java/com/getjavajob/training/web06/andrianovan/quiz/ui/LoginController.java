package com.getjavajob.training.web06.andrianovan.quiz.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.*;

/**
 * Created by user on 25.12.2015.
 */
@Controller
public class LoginController {

    private static final String LOGIN_PROPERTIES = "login.properties";
    private String nameAdmin;
    private String nameUser;
    private String pass;

    public LoginController() {
        Properties props = new Properties();
        try {
            props.load(LoginCheckServlet.class.getClassLoader().getResourceAsStream(LOGIN_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nameUser = props.getProperty("login.user");
        nameAdmin = props.getProperty("login.admin");
        pass = props.getProperty("login.password");
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest req) {
        req.getSession().setAttribute("requestURI", req.getRequestURI());
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
    public ModelAndView login(@RequestParam("email") String actualName,
                              @RequestParam("password") String actualPass,
                              @RequestParam("rememberMe") Object rememberMe,
                              HttpServletRequest req, HttpServletResponse resp) {
        if ((nameUser.equals(actualName) || nameAdmin.equals(actualName)) && pass.equals(actualPass)) {
            if (rememberMe != null) {
                addCookie(resp, COOKIE_USERNAME, actualName, COOKIE_AGE);
                addCookie(resp, COOKIE_PASSWORD, actualPass, COOKIE_AGE);
            } else {
                removeCookie(resp, COOKIE_USERNAME);
                removeCookie(resp, COOKIE_PASSWORD);
            }
            HttpSession session = req.getSession();
            session.setAttribute("userName", actualName);
            String url = session.getAttribute("requestedURI").toString();
            return url == null ? new ModelAndView("quizzesSearch") :
                    new ModelAndView(session.getAttribute("requestedURI").toString());
        } else {
            req.setAttribute("errorLoginMsg", "Wrong username or password");
            return new ModelAndView("login");
        }
    }

    private boolean isAdmin(String loginName) {
        return nameAdmin.equals(loginName);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        removeCookie(resp, COOKIE_USERNAME);
        removeCookie(resp, COOKIE_PASSWORD);
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:quizzesSearch";
    }
}
