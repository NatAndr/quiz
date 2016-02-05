package com.getjavajob.training.web06.andrianovan.quiz.model;

import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Nat on 09.11.2015.
 */
@Entity
@Table(name = "quiz_start")
public class QuizStart extends BaseEntity implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private QuizSet quizSet;
    @Column(name = "quiz_date", insertable = false, updatable = false, columnDefinition = "DATETIME default current_timestamp")
    @org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
    @Temporal(TemporalType.DATE)
    private Date quizDate;
    @ManyToMany
    @JoinTable(name = "quiz_generated_questions",
            joinColumns = @JoinColumn(name = "quiz_start_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private List<Question> generatedQuestions;

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

    public List<Question> getGeneratedQuestions() {
        return generatedQuestions;
    }

    public void setGeneratedQuestions(List<Question> generatedQuestions) {
        this.generatedQuestions = generatedQuestions;
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
