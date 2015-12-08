package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.*;

/**
 * Created by user on 07.12.2015.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeCookie(resp, COOKIE_USERNAME);
        removeCookie(resp, COOKIE_PASSWORD);
        HttpSession session = req.getSession();
        session.invalidate();
//        session.setAttribute("userName", null);
//        session.setAttribute("password", null);
//        resp.sendRedirect(".");
//        resp.sendRedirect(req.getRequestURI());
        req.getRequestDispatcher("/WEB-INF/jsp/quizesSearch.jsp").forward(req, resp);
    }
}
