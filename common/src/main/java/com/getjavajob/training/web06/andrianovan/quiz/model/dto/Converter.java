package com.getjavajob.training.web06.andrianovan.quiz.model.dto;

import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18.01.2016.
 */
public class Converter {

    public List<AnswerDTO> answerListToAnswerDTOList(List<Answer> answerList) {
        List<AnswerDTO> answerDTOs = new ArrayList<>(answerList.size());
        for (Answer answer : answerList) {
            answerDTOs.add(new AnswerDTO(answer.getId(), answer.getAnswer(), answer.getIsCorrect()));
        }
        return answerDTOs;
    }

    public List<QuestionDTO> questionListToQuestionDTOList(List<Question> questionList) {
        ArrayList<QuestionDTO> questionDTOs = new ArrayList<>(questionList.size());
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO(question.getId(), question.getQuestion(), question.getQuestionType(),
                    question.getWeight(), question.getPicture());
            questionDTO.setAnswerDTOs(this.answerListToAnswerDTOList(question.getAnswers()));
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }

    public List<QuizSetDTO> quizSetListToQuizSetDTOList(List<QuizSet> quizSetList) {
        ArrayList<QuizSetDTO> quizSetDTOs = new ArrayList<>(quizSetList.size());
        for (QuizSet quizSet : quizSetList) {
            QuizSetDTO quizSetDTO = new QuizSetDTO(quizSet.getId(), quizSet.getName());
            quizSetDTO.setQuestionDTOs(this.questionListToQuestionDTOList(quizSet.getQuestions()));
            quizSetDTOs.add(quizSetDTO);
        }
        return quizSetDTOs;
    }

    public QuizSetDTO quizSetToQuizSetDTO(QuizSet quizSet) {
        QuizSetDTO quizSetDTO = new QuizSetDTO(quizSet.getId(), quizSet.getName());
        quizSetDTO.setQuestionDTOs(this.questionListToQuestionDTOList(quizSet.getQuestions()));
        return quizSetDTO;
    }
}
