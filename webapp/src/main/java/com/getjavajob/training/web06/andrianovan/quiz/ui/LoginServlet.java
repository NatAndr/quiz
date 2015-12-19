package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.COOKIE_USERNAME;
import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.getCookieValue;

/**
 * Created by user on 03.12.2015.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("Admin".equals(getCookieValue(req, COOKIE_USERNAME))) {
            req.getRequestDispatcher("/WEB-INF/jsp/management.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
