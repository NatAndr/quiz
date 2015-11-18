package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.AnswerService;
import com.getjavajob.training.web06.andrianovan.quiz.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nat on 13.11.2015.
 */
public class QuizesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private QuizSetService quizSetService = new QuizSetService();
    private QuestionService questionService = new QuestionService();
    private AnswerService answerService = new AnswerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("<html><body>");

//        sb.append(quizSetService.get(1).getQuizName());

        List<QuizSet> quizSets = quizSetService.getAll();
        sb.append("<table>");
        for (QuizSet quizSet : quizSets) {
            sb.append("<tr><td>");
            sb.append(quizSet.getQuizName());
            sb.append("</td></tr>");
            for (Question question : quizSet.getQuestions()) {
                sb.append("<tr><td>");
                sb.append(question.getQuestion());
                sb.append("</td></tr>");
                for (Answer answer : question.getAnswers()) {
                    sb.append("<tr><td>");
                    sb.append(answer.getAnswer());
                    sb.append("</td></tr>");
                }
            }
        }
        sb.append("</table></body></html>");
        resp.getOutputStream().write(sb.toString().getBytes());
    }
}
