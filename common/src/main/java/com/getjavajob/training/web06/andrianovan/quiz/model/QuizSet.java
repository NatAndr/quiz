package com.getjavajob.training.web06.andrianovan.quiz.model;

import java.util.List;

/**
 * Created by Nat on 30.10.2015.
 */
public class QuizSet extends BaseEntity {

    private String quizName;
    private List<Question> questions;

    public QuizSet() {
    }

    public QuizSet(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return quizName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizSet that = (QuizSet) o;

        return !(quizName != null ? !quizName.equals(that.quizName) : that.quizName != null);

    }

    @Override
    public int hashCode() {
        return quizName != null ? quizName.hashCode() : 0;
    }
}
