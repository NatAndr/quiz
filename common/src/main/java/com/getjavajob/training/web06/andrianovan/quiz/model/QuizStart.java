package com.getjavajob.training.web06.andrianovan.quiz.model;

import java.util.Date;

/**
 * Created by Nat on 09.11.2015.
 */
public class QuizStart extends BaseEntity {

    private QuizSet quizHeader;
    private Date quizDate;

    public QuizStart() {
    }

    public QuizStart(QuizSet quizHeader) {
        this.quizHeader = quizHeader;
    }

    public QuizStart(QuizSet quizHeader, Date quizDate) {
        this.quizHeader = quizHeader;
        this.quizDate = quizDate;
    }

    public QuizSet getQuizHeader() {
        return quizHeader;
    }

    public void setQuizHeader(QuizSet quizHeader) {
        this.quizHeader = quizHeader;
    }

    public Date getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(Date quizDate) {
        this.quizDate = quizDate;
    }

    @Override
    public String toString() {
        return "QuizStart{" +
                "quizHeader=" + quizHeader +
                ", quizDate=" + quizDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizStart quizStart = (QuizStart) o;

        if (quizHeader != null ? !quizHeader.equals(quizStart.quizHeader) : quizStart.quizHeader != null) return false;
        return !(quizDate != null ? !quizDate.equals(quizStart.quizDate) : quizStart.quizDate != null);

    }

    @Override
    public int hashCode() {
        int result = quizHeader != null ? quizHeader.hashCode() : 0;
        result = 31 * result + (quizDate != null ? quizDate.hashCode() : 0);
        return result;
    }
}
