package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.GeneratedQuestionsService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizStartService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by user on 01.12.2015.
 */
public class QuizRunServlet extends HttpServlet {

    private QuizSetService quizSetService;
    private QuizStartService quizStartService;
    private GeneratedQuestionsService genQuestionsService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.quizSetService = applicationContext.getBean(QuizSetService.class);
        this.quizStartService = applicationContext.getBean(QuizStartService.class);
        this.genQuestionsService = applicationContext.getBean(GeneratedQuestionsService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizSet quizSet = quizSetService.get(Integer.parseInt(req.getParameter("id")));
        GeneratedQuestions generatedQuestions;
        QuizStart quizStart;
        try {
            quizStart = new QuizStart(quizSet, null);
            quizStartService.insert(quizStart);
//            generatedQuestions = genQuestionsService.startQuiz(quizSet);
            generatedQuestions = genQuestionsService.generateQuestions(quizStart);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        int counter = 0;
        req.setAttribute("question", generatedQuestions.getQuestions().get(0));
        HttpSession session = req.getSession();
        session.setAttribute("counter", counter);
        session.setAttribute("quizStart", quizStart);
        session.setAttribute("generatedQuestions", generatedQuestions.getQuestions());
        session.setAttribute("questionsNumber", generatedQuestions.getQuestions().size());
        session.setAttribute("quizSet", quizSet);
//        counter++;
        req.getRequestDispatcher("/WEB-INF/jsp/quizRun.jsp").forward(req, resp);
    }
}
