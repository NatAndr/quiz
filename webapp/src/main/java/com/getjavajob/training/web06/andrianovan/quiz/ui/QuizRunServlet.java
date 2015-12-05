package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.GeneratedQuestionsService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 01.12.2015.
 */
public class QuizRunServlet extends HttpServlet {

    private QuizSetService quizSetService = new QuizSetService();
    private GeneratedQuestionsService genQuestionsService = new GeneratedQuestionsService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizSet quizSet = quizSetService.get(Integer.parseInt(req.getParameter("id")));
        GeneratedQuestions generatedQuestions;
        try {
            generatedQuestions = genQuestionsService.startQuiz(quizSet);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        int counter = 0;
//        req.setAttribute("counter", counter);
//        req.setAttribute("quizSet", quizSet);
        req.setAttribute("question", generatedQuestions.getQuestions().get(0));
//        req.setAttribute("questionsNumber", generatedQuestions.getQuestions().size());
//        req.setAttribute("quizStart", generatedQuestions.getQuizStart());
//        req.setAttribute("generatedQuestions", generatedQuestions.getQuestions());

        req.getSession().setAttribute("counter", counter);
        req.getSession().setAttribute("quizStart", generatedQuestions.getQuizStart());
        req.getSession().setAttribute("generatedQuestions", generatedQuestions.getQuestions());
        req.getSession().setAttribute("questionsNumber", generatedQuestions.getQuestions().size());
        req.getSession().setAttribute("quizSet", quizSet);
        counter++;
        req.getRequestDispatcher("/WEB-INF/jsp/quizRun.jsp").forward(req, resp);
    }
}
