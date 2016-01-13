package com.getjavajob.training.web06.andrianovan.quiz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Nat on 09.11.2015.
 */
@Entity
@Table(name = "quiz_start")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class QuizStart extends BaseEntity {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="quiz_id")
    private QuizSet quizSet;

    @Column(name = "quiz_date", nullable = false)
    private Date quizDate;

    public QuizStart() {
    }

    public QuizStart(QuizSet quizSet) {
        this.quizSet = quizSet;
    }

    public QuizStart(QuizSet quizSet, Date quizDate) {
        this.quizSet = quizSet;
        this.quizDate = quizDate;
    }

    public QuizSet getQuizSet() {
        return quizSet;
    }

    public void setQuizSet(QuizSet quizHeader) {
        this.quizSet = quizHeader;
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
                "quizSet=" + quizSet +
                ", quizDate=" + quizDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizStart quizStart = (QuizStart) o;

        if (quizSet != null ? !quizSet.equals(quizStart.quizSet) : quizStart.quizSet != null) return false;
        return !(quizDate != null ? !quizDate.equals(quizStart.quizDate) : quizStart.quizDate != null);

    }

    @Override
    public int hashCode() {
        int result = quizSet != null ? quizSet.hashCode() : 0;
        result = 31 * result + (quizDate != null ? quizDate.hashCode() : 0);
        return result;
    }
}
