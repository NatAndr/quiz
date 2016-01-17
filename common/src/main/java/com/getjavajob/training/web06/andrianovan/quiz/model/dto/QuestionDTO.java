package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuestionType;

/**
 * Created by user on 16.01.2016.
 */
public class QuestionDTO {
    private int id;
    private String question;
    private QuestionType questionType;
    private int weight;
    private String picture;

    public QuestionDTO(int id, String question, QuestionType questionType, int weight, String picture) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
        this.weight = weight;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
