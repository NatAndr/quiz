package com.getjavajob.training.web06.andrianovan.quiz.model;

import java.util.List;

/**
 * Created by Nat on 30.10.2015.
 */
public class Question extends BaseEntity {

    private String question;
    private QuestionType questionType;
    private int weight;
    private List<Answer> answers;
    private byte[] image;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
}
