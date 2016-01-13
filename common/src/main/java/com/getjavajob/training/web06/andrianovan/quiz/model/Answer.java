package com.getjavajob.training.web06.andrianovan.quiz.model;

import javax.persistence.*;

/**
 * Created by Nat on 30.10.2015.
 */
@Entity
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Answer extends BaseEntity {

    @Column(nullable = false)
    private String answer;

    @Column(name = "is_correct", columnDefinition = "INTEGER(1)")
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public Answer(String answer, boolean isCorrect) {
        this(answer);
        this.isCorrect = isCorrect;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
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
