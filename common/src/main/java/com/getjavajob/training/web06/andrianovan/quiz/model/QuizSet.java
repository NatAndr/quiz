package com.getjavajob.training.web06.andrianovan.quiz.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nat on 30.10.2015.
 */
@Entity
@Table(name = "quiz_header")
public class QuizSet extends BaseEntity {
    @Column(name = "quiz_name", nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private List<Question> questions;

    public QuizSet() {
    }

    public QuizSet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizSet that = (QuizSet) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
