package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 30.11.2015.
 */
public class StartQuizServlet extends HttpServlet {

    private QuizSetService quizSetService = new QuizSetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizSet quizSet = quizSetService.get(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("quiz", quizSet);
        req.getRequestDispatcher("/WEB-INF/jsp/startQuiz.jsp").forward(req, resp);
    }
}
