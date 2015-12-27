package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;

import java.util.List;

/**
 * Created by user on 25.12.2015.
 */
public class QuestionForm {

    private QuizSet quizSet;
    private int questionsNumber;
    private int counter;
    private Question question;
    private List<Answer> answers;

    public QuizSet getQuizSet() {
        return quizSet;
    }

    public void setQuizSet(QuizSet quizSet) {
        this.quizSet = quizSet;
    }

    public int getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(int questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
