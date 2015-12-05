package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 02.12.2015.
 */
public class QuizQuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int counter = (int) req.getSession().getAttribute("counter") + 1;
        if (counter == req.getSession().getAttribute("questionsNumber")) {
            getResults();
            req.getRequestDispatcher("/WEB-INF/jsp/quizResult.jsp").forward(req, resp);
            return;
        }
        List<Question> generatedQuestions = (List<Question>) req.getSession().getAttribute("generatedQuestions");
        req.getSession().setAttribute("counter", counter);
        req.setAttribute("question", generatedQuestions.get(counter));
        req.getRequestDispatcher("/WEB-INF/jsp/quizRun.jsp").forward(req, resp);
    }

    private void getResults() {
    }
}
