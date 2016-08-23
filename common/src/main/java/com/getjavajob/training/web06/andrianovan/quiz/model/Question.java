package com.getjavajob.training.web06.andrianovan.quiz.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nat on 30.10.2015.
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity {
    @Column(nullable = false)
    private String question;
    @Enumerated
    @Column(name = "type")
    private QuestionType questionType;
    private int weight;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Answer> answers;
    @Column(name = "image")
    private String picture;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    @XStreamOmitField
    private QuizSet quizSet;

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, QuestionType questionType) {
        this(question);
        this.questionType = questionType;
    }

    public Question(String question, int weight) {
        this(question);
        this.weight = weight;
    }

    public Question(String question, QuestionType questionType, int weight) {
        this(question, questionType);
        this.weight = weight;
    }

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public QuizSet getQuizSet() {
        return quizSet;
    }

    public void setQuizSet(QuizSet quizSet) {
        this.quizSet = quizSet;
    }

    @Override
    public String toString() {
        return question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        return question.equals(question1.question);

    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }

    public List<Answer> getCorrectAnswers() {
        List<Answer> correctAnswers = this.getAnswers();
        Iterator<Answer> iterator = correctAnswers.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getIsCorrect()) {
                iterator.remove();
            }
        }
        return correctAnswers;
    }
}
