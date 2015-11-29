package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Nat on 14.11.2015.
 */
public class Runner {

    public static void main(String[] args) throws DaoException, ServiceException {

//        Answer answer1 = new Answer("Calling the SetPriority() method on a Thread object.");
//        Answer answer2 = new Answer("Calling the wait() method on an object.");
//        Answer answer3 = new Answer("Calling notify() method on an object.");
//        Answer answer4 = new Answer("Calling read() method on an InputStream object.");
//        Answer answer5 = new Answer("interrupt();");
//        Answer answer6 = new Answer("wait(long msecs);");
//        Answer answer7 = new Answer("sleep(long msecs);");
//        Answer answer8 = new Answer("yield();");

//        Question question = new Question("Which cannot directly cause a thread to stop executing?",
//                QuestionType.SINGLE, 1);
//
//        QuizSet quizSet = QuizSetDao.getInstance().get(1); /////!!!!!

//        question.setAnswers(Arrays.asList(answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8));
//        question.setAnswers(Arrays.asList(answer1, answer2, answer3, answer4));
//        quizSet.setQuestions(Collections.singletonList(question));
//
//        QuizSetService quizSetService = new QuizSetService();
//        quizSetService.insertQuestionToExistingQuizSet(quizSet);



//        QuizStart quizStart = new QuizStart(quizSet);
//        new QuizStartService().insert(quizStart);
//        GeneratedQuestionsService generatedQuestionsService = new GeneratedQuestionsService();
//        generatedQuestionsService.generateQuestions(quizStart);
//        System.out.println(generatedQuestionsService.getAll());

        QuizSetService quizSetService = new QuizSetService();
        String searchParams = "e";
        List<QuizSet> quizes = quizSetService.searchQuizSetBySubstring(searchParams);
        System.out.println(quizes);
    }
}
