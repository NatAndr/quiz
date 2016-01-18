package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * Created by user on 16.01.2016.
 */
@XStreamAlias("answer")
public class AnswerDTO {
    @XStreamOmitField
    private int id;
    @XStreamAlias("text")
    private String answer;
    @XStreamAsAttribute
    @XStreamConverter(value = BooleanConverter.class,
            booleans = { true },
            strings = { "yes", "no" })
    private boolean isCorrect;

    public AnswerDTO() {
    }

    public AnswerDTO(int id, String answer, boolean isCorrect) {
        this.id = id;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
