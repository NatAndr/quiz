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
    private String nameAdmin;
    private String nameUser;
    private String pass;

    @Override
    public void init() throws ServletException {
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actualName = req.getParameter("email");
        String actualPass = req.getParameter("password");

        if ((nameUser.equals(actualName) || nameAdmin.equals(actualName)) && pass.equals(actualPass)) {
            if (req.getParameter("rememberMe") != null) {
                addCookie(resp, COOKIE_USERNAME, actualName, COOKIE_AGE);
                addCookie(resp, COOKIE_PASSWORD, actualPass, COOKIE_AGE);
            } else {
                removeCookie(resp, COOKIE_USERNAME);
                removeCookie(resp, COOKIE_PASSWORD);
            }
            HttpSession session = req.getSession();
            session.setAttribute("userName", actualName);
//            session.setAttribute("password", actualPass);
            if (isAdmin(actualName)) {
                req.getRequestDispatcher("/WEB-INF/jsp/management.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/jsp/quizzesSearch.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorLoginMsg", "Wrong username or password");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }

    private boolean isAdmin(String loginName) {
        return nameAdmin.equals(loginName);
    }
}
