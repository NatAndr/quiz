package com.getjavajob.training.web06.andrianovan.quiz.model;

import javax.persistence.*;

/**
 * Created by Nat on 30.10.2015.
 */
@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {

    @Column(nullable = false)
    private String answer;

    @Column(name = "is_correct", columnDefinition = "INTEGER(1)")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer() {
    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public Answer(String answer, boolean isCorrect) {
        this(answer);
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return answer + '\'' +
                ", getIsCorrect=" + isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        return answer.equals(answer1.answer);

    }

    @Override
    public int hashCode() {
        return answer.hashCode();
    }
}
