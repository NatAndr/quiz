package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17.01.2016.
 */
@XStreamAlias("quizzes")
public class QuizSetDTOList {
    @XStreamImplicit
    @XStreamAlias("quiz")
    private List<QuizSetDTO> quizSetList;

    public QuizSetDTOList() {
        quizSetList = new ArrayList<>();
    }

    public List<QuizSetDTO> getQuizSetList() {
        return quizSetList;
    }

    public void setQuizSetList(List<QuizSetDTO> quizSetList) {
        this.quizSetList = quizSetList;
    }

    @Override
    public String toString() {
        return "QuizSetDTOList{" +
                "quizSetList=" + quizSetList +
                '}';
    }
}
