package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.AnswerService;
import com.getjavajob.training.web06.andrianovan.quiz.service.ResultService;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 02.12.2015.
 */
public class QuizQuestionServlet extends HttpServlet {

    private static final int STUDENT_ID = 1;
    private static final String CANNOT_SAVE_RESULT = "Cannot save result ";
    //@Autowired
    private StudentService studentService;
    //@Autowired
    private AnswerService answerService;
    //@Autowired
    private ResultService resultService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.studentService = applicationContext.getBean(StudentService.class);
        this.answerService = applicationContext.getBean(AnswerService.class);
        this.resultService = applicationContext.getBean(ResultService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = studentService.get(STUDENT_ID);
        QuizStart quizStart = (QuizStart) session.getAttribute("quizStart");
        int counter = (int) session.getAttribute("counter") + 1;
        saveResult(req, student, quizStart);

        if (counter == req.getSession().getAttribute("questionsNumber")) {
            try {
                req.setAttribute("result", resultService.calculateQuizResult(student, quizStart));
            } catch (ServiceException e) {
                throw new RuntimeException("Cannot get result " + student + quizStart + e.getLocalizedMessage());
            }
            req.getRequestDispatcher("/WEB-INF/jsp/quizResult.jsp").forward(req, resp);
            return;
        }
        List<Question> generatedQuestions = (List<Question>) req.getSession().getAttribute("generatedQuestions");
        session.setAttribute("counter", counter);
        req.setAttribute("question", generatedQuestions.get(counter));
        req.getRequestDispatcher("/WEB-INF/jsp/quizRun.jsp").forward(req, resp);
    }

    private void saveResult(HttpServletRequest req, Student student, QuizStart quizStart) {
        String inputAnswer = req.getParameter("inputAnswer");
        String[] results = req.getParameterValues("answer");
        for (int i = 0; i < results.length; i++) {
            Answer answer = answerService.get(Integer.parseInt(results[i]));
            Result result = new Result(student, answer, inputAnswer, quizStart);
            try {
                resultService.insert(result);
            } catch (ServiceException e) {
                throw new RuntimeException(CANNOT_SAVE_RESULT + student + answer + inputAnswer + quizStart
                        + e.getLocalizedMessage());
            }
        }
    }
}
