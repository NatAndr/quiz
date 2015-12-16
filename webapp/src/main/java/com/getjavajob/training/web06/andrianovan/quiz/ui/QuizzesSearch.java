package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nat on 25.11.2015.
 */
public class QuizzesSearch extends HttpServlet {

    private QuizSetService quizSetService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.quizSetService = applicationContext.getBean(QuizSetService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchParams = req.getParameter("searchParams");
        if (searchParams != null) {
            List<QuizSet> quizes;
            try {
                quizes = quizSetService.searchQuizSetBySubstring(searchParams);
            } catch (ServiceException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            req.setAttribute("foundQuiz", quizes);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/quizzesSearch.jsp").forward(req, resp);
    }
}

