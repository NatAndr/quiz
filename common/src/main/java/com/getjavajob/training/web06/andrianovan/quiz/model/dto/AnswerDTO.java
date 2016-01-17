package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

/**
 * Created by user on 16.01.2016.
 */
public class AnswerDTO {
    private int id;
    private String answer;
    private boolean isCorrect;

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
