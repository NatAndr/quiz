package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nat on 14.11.2015.
 */
public class Runner {

    public static void main(String[] args) throws DaoException {

//        Student student = new StudentService().get(1);
//        QuizStart quizStart = new QuizStartService().get(3);
//
//        int actual = new ResultService().countQuizResult(student, quizStart);
//        System.out.println(actual);
//        System.out.println(student);

//        QuizSet quizSet = new QuizSet("Quiz");
//        Question question1 = new Question("question1");
//        Question question2 = new Question("question2");
//        Answer answer1 = new Answer("Answer1");
//        Answer answer2 = new Answer("Answer2");
//        question1.setAnswers(Collections.singletonList(answer1));
//        question2.setAnswers(Collections.singletonList(answer2));
//        quizSet.setQuestions(Arrays.asList(question1, question2));
//        QuizSetService quizSetService = new QuizSetService();
//        quizSetService.insert(quizSet);
//        System.out.println(quizSet);

        StringBuilder sb = new StringBuilder("<html><body>");

//        sb.append(quizSetService.get(1).getQuizName());

        QuizSetService quizSetService = new QuizSetService();
        List<QuizSet> quizSets = quizSetService.getAll();

        for (QuizSet quizSet : quizSets) {
            sb.append(quizSet.getQuizName());
            for (Question question : quizSet.getQuestions()) {
                sb.append(question.getQuestion());
                for (Answer answer : question.getAnswers()) {
                    sb.append(answer.getAnswer());
                }
            }
        }
        sb.append("</body></html>");
        System.out.println(sb.toString());
    }


}
