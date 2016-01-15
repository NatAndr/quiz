package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by user on 14.01.2016.
 */
public class MyJsonMapper extends ObjectMapper {
    public MyJsonMapper() {
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
