package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.output.Output;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



/**
 * Created by Nat on 13.11.2015.
 */
public class AllQuizzesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private QuizSetService quizSetService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.quizSetService = applicationContext.getBean(QuizSetService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("<html><body>");
        List<QuizSet> quizSets = quizSetService.getAll();
        sb.append("<table>").append("\r\n");
        for (QuizSet quizSet : quizSets) {
            sb.append("<tr><td>");
            sb.append(quizSet.getQuizName());
            sb.append("</td></tr>").append("\r\n");
            for (Question question : quizSet.getQuestions()) {
                sb.append("<tr><td>");
                sb.append(question.getQuestion());
                sb.append("</td></tr>").append("\r\n");
                for (Answer answer : question.getAnswers()) {
                    sb.append("<tr><td>");
                    sb.append(answer.getAnswer());
                    sb.append("</td></tr>").append("\r\n");
                }
            }
        }
        sb.append("</table></body></html>");
        new Output().writeToOutputStream(resp.getOutputStream(), sb.toString());
    }
}
