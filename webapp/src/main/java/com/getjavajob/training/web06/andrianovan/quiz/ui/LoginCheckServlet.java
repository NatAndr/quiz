package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.*;

/**
 * Created by user on 03.12.2015.
 */
public class LoginCheckServlet extends HttpServlet {

    private static final String LOGIN_PROPERTIES = "login.properties";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("email");
        String userPass = req.getParameter("password");

        Properties props = new Properties();
        props.load(LoginCheckServlet.class.getClassLoader().getResourceAsStream(LOGIN_PROPERTIES));
        String name = props.getProperty("user.name");
        String pass = props.getProperty("user.password");

        if (name.equals(userName) && pass.equals(userPass)) {
            if (req.getParameter("rememberMe") != null) {
                addCookie(resp, COOKIE_USERNAME, userName, COOKIE_AGE);
                addCookie(resp, COOKIE_PASSWORD, userPass, COOKIE_AGE);
            } else {
                removeCookie(resp, COOKIE_USERNAME);
                removeCookie(resp, COOKIE_PASSWORD);
            }
            HttpSession session = req.getSession();
            session.setAttribute("userName", userName);
            session.setAttribute("password", userPass);
            req.getRequestDispatcher("/WEB-INF/jsp/quizzesSearch.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorLoginMsg", "Wrong username or password");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
