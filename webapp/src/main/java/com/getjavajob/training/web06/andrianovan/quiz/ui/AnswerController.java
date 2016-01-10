package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.service.AnswerService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public List<Question> getQuestionList() {
        return questionService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/answerInfo", method = RequestMethod.POST)
    public Answer getQuestionInfo(@RequestParam("id") int id) {
        return answerService.get(id);
    }

    @ResponseBody
    @RequestMapping(value = "/answerDelete", method = RequestMethod.POST)
    public void answerDelete(@RequestParam("id") int id) throws ServiceException {
        Answer answer = answerService.get(id);
        answerService.delete(answer);
    }
}
