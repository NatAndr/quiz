package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuestionType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.List;

/**
 * Created by user on 16.01.2016.
 */
@XStreamAlias("question")
public class QuestionDTO {
    @XStreamOmitField
    private int id;
    @XStreamAlias("text")
    private String question;
    @XStreamAsAttribute
    private QuestionType questionType;
    @XStreamAsAttribute
    private int weight;
    @XStreamOmitField
    private byte[] picture;
    @XStreamAlias("answers")
    private List<AnswerDTO> answerDTOs;

    public QuestionDTO() {
    }

    public QuestionDTO(int id, String question, QuestionType questionType, int weight, byte[] picture) {
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<AnswerDTO> getAnswerDTOs() {
        return answerDTOs;
    }

    public void setAnswerDTOs(List<AnswerDTO> answerDTOs) {
        this.answerDTOs = answerDTOs;
    }
}
