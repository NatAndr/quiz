package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.AnswerDTO;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuestionDTO;
import com.getjavajob.training.web06.andrianovan.quiz.service.AnswerService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "/questionList", method = RequestMethod.POST)
    public List<QuestionDTO> getQuestionList() {
        logger.debug("Get questions list");
        List<QuestionDTO> dtoList = new ArrayList<>();
        for (Question question : questionService.getAll()) {
            dtoList.add(new QuestionDTO(question.getId(), question.getQuestion(), question.getQuestionType(),
                    question.getWeight(), question.getPicture()));
        }
        logger.debug("End of get questions list");
        return dtoList;
    }

    @ResponseBody
    @RequestMapping(value = "/answerInfo", method = RequestMethod.POST)
    public AnswerDTO getQuestionInfo(@RequestParam("id") int id) {
        logger.debug("Get answer info id = " + id);
        Answer answer = answerService.get(id);
        logger.debug("End of get answer info");
        return new AnswerDTO(id, answer.getAnswer(), answer.getIsCorrect());
    }

    @ResponseBody
    @RequestMapping(value = "/answerDelete", method = RequestMethod.POST)
    public void answerDelete(@RequestParam("id") int id,
                             @RequestParam(value = "questionId") int questionId) {
        logger.debug("Going to delete answer id = " + id);
        Question question = questionService.get(questionId);
        Answer answer = answerService.get(id);
        question.getAnswers().remove(answer);
        try {
            questionService.update(question);
            logger.debug("Updated question = " + question);
        } catch (ServiceException e) {
            errorLogger.error("Cannot update question to delete answer" + question);
        }
        try {
            answerService.delete(answer);
            logger.error("Deleted answer" + answer);
        } catch (ServiceException e) {
            errorLogger.error("Cannot delete answer" + answer);
        }
        logger.debug("End of delete answer");
    }

    @RequestMapping(value = "/answerUpdate", method = RequestMethod.POST)
    public
    @ResponseBody
    String answerUpdate(@RequestParam(value = "id") int id,
                        @RequestParam(value = "questionId") int questionId,
                        @RequestParam(value = "answer") String answerString,
                        @RequestParam(value = "isCorrect") String isCorrect) {
        logger.debug("Going to add or update answer id = " + id);
        Answer answer;
        Question question = questionService.get(questionId);
        if (id == 0) {
            answer = new Answer(answerString, Boolean.valueOf(isCorrect));
            answer.setQuestion(question);
            question.getAnswers().add(answer);
            logger.debug("Created answer questionId = {}, answer = {}, isCorrect = {}", questionId, answerString, isCorrect);
        } else {
            answer = answerService.get(id);
            answer.setAnswer(answerString);
            answer.setIsCorrect(Boolean.valueOf(isCorrect));
            answer.setQuestion(question);
            try {
                answerService.update(answer);
                logger.debug("Updated answer " + answer);
            } catch (ServiceException e) {
                errorLogger.error("Cannot update answer " + answer);
            }
        }
        try {
            questionService.update(question);
            logger.debug("Updated question " + question);
        } catch (ServiceException e) {
            errorLogger.error("Cannot update question " + question);
        }
        logger.debug("End of add or update answer");
        return "Saved " + answer.getAnswer() + " " + answer.getIsCorrect();
    }
}
