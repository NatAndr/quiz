package com.getjavajob.training.web06.andrianovan.quiz.model;

import javax.persistence.*;

/**
 * Created by Nat on 09.11.2015.
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
