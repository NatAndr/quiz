package com.getjavajob.training.web06.andrianovan.quiz.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Nat on 30.10.2015.
 */
@XStreamAlias("type")
public enum QuestionType {
    SINGLE,
    MULTIPLE,
    INPUT
}
