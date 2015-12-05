package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 03.12.2015.
 */
public class LoginCheckServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("email");
        String pass = req.getParameter("password");

        if ("user@gmail.com".equalsIgnoreCase(name) && "123".equalsIgnoreCase(pass)) {
            req.getRequestDispatcher("/WEB-INF/jsp/quizesSearch.jsp").forward(req, resp);
        } else {//if name&pass not match then it display error page//
            //resp.sendRedirect(req.getContextPath() + "/studyGroups");
            //todo include error msg
        }
    }
}
