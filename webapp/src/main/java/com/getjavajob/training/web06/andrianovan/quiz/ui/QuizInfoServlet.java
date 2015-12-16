package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 30.11.2015.
 */
public class QuizInfoServlet extends HttpServlet {
    @Autowired
    private QuizSetService quizSetService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.quizSetService = applicationContext.getBean(QuizSetService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizSet quizSet = quizSetService.get(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("quiz", quizSet);
        req.setAttribute("questionsNumber", quizSet.getQuestions().size());
        req.getRequestDispatcher("/WEB-INF/jsp/quizInfo.jsp").forward(req, resp);
    }
}
