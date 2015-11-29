package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nat on 25.11.2015.
 */
public class QuizesSearch extends HttpServlet {

//    private QuizSetService quizSetService = new QuizSetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String searchParams = "e";//req.getParameter("searchParams");
//        if (searchParams != null) {
            try {
                QuizSetService quizSetService = new QuizSetService();
                List<QuizSet> quizes = quizSetService.searchQuizSetBySubstring(searchParams);
                req.setAttribute("foundQuiz", quizes);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
//        }
        req.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(req, resp);
    }
}
