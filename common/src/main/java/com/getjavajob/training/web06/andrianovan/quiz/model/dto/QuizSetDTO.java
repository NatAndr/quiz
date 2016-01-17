package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

/**
 * Created by user on 16.01.2016.
 */
public class QuizSetDTO {
    private int id;
    private String name;

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
}
