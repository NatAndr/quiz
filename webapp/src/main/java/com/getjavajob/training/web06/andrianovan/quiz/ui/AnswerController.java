package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.AnswerDTO;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuestionDTO;
import com.getjavajob.training.web06.andrianovan.quiz.service.AnswerService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 09.01.2016.
 */
@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "/questionList", method = RequestMethod.POST)
    public List<QuestionDTO> getQuestionList() {
        List<QuestionDTO> dtoList = new ArrayList<>();
        for (Question question : questionService.getAll()) {
            dtoList.add(new QuestionDTO(question.getId(), question.getQuestion(), question.getQuestionType(),
                    question.getWeight(), question.getPicture()));
        }
        return dtoList;
    }

    @ResponseBody
    @RequestMapping(value = "/answerInfo", method = RequestMethod.POST)
    public AnswerDTO getQuestionInfo(@RequestParam("id") int id) {
        Answer answer = answerService.get(id);
        return new AnswerDTO(id, answer.getAnswer(), answer.getIsCorrect());
    }

    @ResponseBody
    @RequestMapping(value = "/answerDelete", method = RequestMethod.POST)
    public void answerDelete(@RequestParam("id") int id,
                             @RequestParam(value = "questionId") int questionId) throws ServiceException {
        Question question = questionService.get(questionId);
        Answer answer = answerService.get(id);
        question.getAnswers().remove(answer);
        questionService.update(question);
        answerService.delete(answer);
    }

    @RequestMapping(value = "/answerUpdate", method = RequestMethod.POST)
    public
    @ResponseBody
    String answerUpdate(@RequestParam(value = "id") int id,
                          @RequestParam(value = "questionId") int questionId,
                          @RequestParam(value = "answer") String answerString,
                          @RequestParam(value = "isCorrect") String isCorrect) throws ServiceException {
        Answer answer;
        Question question = questionService.get(questionId);
        if (id == 0) {
            answer = new Answer(answerString, Boolean.valueOf(isCorrect));
            answer.setQuestion(question);
            question.getAnswers().add(answer);
        } else {
            answer = answerService.get(id);
            answer.setAnswer(answerString);
            answer.setIsCorrect(Boolean.valueOf(isCorrect));
            answer.setQuestion(question);
            answerService.update(answer);
        }
        questionService.update(question);
        return "Saved " + answer.getAnswer() + " " + answer.getIsCorrect();
    }
}
