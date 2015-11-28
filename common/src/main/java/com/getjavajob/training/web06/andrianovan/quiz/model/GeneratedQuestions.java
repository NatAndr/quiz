package com.getjavajob.training.web06.andrianovan.quiz.model;

import java.util.List;

/**
 * Created by Nat on 24.11.2015.
 */
public class GeneratedQuestions extends BaseEntity{

    private QuizStart quizStart;
    private List<Question> questions;

    public GeneratedQuestions() {
    }

    public GeneratedQuestions(QuizStart quizStart) {
        this.quizStart = quizStart;
    }

    public GeneratedQuestions(QuizStart quizStart, List<Question> questions) {
        this(quizStart);
        this.questions = questions;
    }

    public QuizStart getQuizStart() {
        return quizStart;
    }

    public void setQuizStart(QuizStart quizStart) {
        this.quizStart = quizStart;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "GeneratedQuestions{" +
                "quizStart=" + quizStart +
                ", questions=" + questions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneratedQuestions that = (GeneratedQuestions) o;

        if (quizStart != null ? !quizStart.equals(that.quizStart) : that.quizStart != null) return false;
        return !(questions != null ? !questions.equals(that.questions) : that.questions != null);

    }

    @Override
    public int hashCode() {
        int result = quizStart != null ? quizStart.hashCode() : 0;
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }
}
