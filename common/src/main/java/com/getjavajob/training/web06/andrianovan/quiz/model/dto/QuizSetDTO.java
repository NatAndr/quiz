package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.List;

/**
 * Created by user on 16.01.2016.
 */
@XStreamAlias("quiz")
public class QuizSetDTO {
    @XStreamOmitField
    private int id;
    @XStreamAlias("text")
    private String name;
    @XStreamAlias("questions")
    private List<QuestionDTO> questionDTOs;

    public QuizSetDTO() {
    }

    public QuizSetDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionDTO> getQuestionDTOs() {
        return questionDTOs;
    }

    public void setQuestionDTOs(List<QuestionDTO> questionDTOs) {
        this.questionDTOs = questionDTOs;
    }
}
