package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuestionType;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.Converter;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuestionDTO;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuizSetDTO;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 04.01.2016.
 */
@Controller
public class QuestionController {
    private static final Logger debugLogger = LoggerFactory.getLogger("DebugLogger");
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizSetService quizSetService;

    @ResponseBody
    @RequestMapping(value = "/questionDelete", method = RequestMethod.POST)
    public void questionDelete(@RequestParam("id") int id,
                               @RequestParam(value = "quizId") int quizId) {
        debugLogger.debug("Going to delete question id = " + id);
        QuizSet quizSet = quizSetService.get(quizId);
        Question question = questionService.get(id);
        quizSet.getQuestions().remove(question);
        try {
            quizSetService.update(quizSet);
            debugLogger.debug("Update quiz set " + quizSet);
        } catch (ServiceException e) {
            errorLogger.error("Cannot update quiz set ", quizSet);
        }
        try {
            questionService.delete(question);
            debugLogger.debug("Deleted question " + question);
        } catch (ServiceException e) {
            errorLogger.error("Cannot delete question ", question);
        }
        debugLogger.debug("End of delete question");
    }

    @RequestMapping(value = "/questionUpdate", method = RequestMethod.POST)
    public
    @ResponseBody
    String questionUpdate(@RequestParam(value = "id") int id,
                          @RequestParam(value = "question") String questionString,
                          @RequestParam(value = "weight") int weight,
                          @RequestParam(value = "quizId") int quizId,
                          @RequestParam(value = "questionType") String questionType,
                          @RequestParam(value = "questionImage") String questionImage,
                          HttpServletRequest servletRequest) {
        debugLogger.debug("Going to add or update id = " + id);
        Question question = null;
        QuizSet quizSet = quizSetService.get(quizId);
        String newImg = null;
        if (!questionImage.isEmpty()) {
            newImg = " image";
        }
        String image = (String) servletRequest.getSession().getAttribute("image");
        if (id == 0) {
            question = new Question(questionString, QuestionType.valueOf(questionType), weight);
            if (image != null) {
                question.setPicture(image);
            }
            question.setQuizSet(quizSet);
            quizSet.getQuestions().add(question);
        } else {
            question = questionService.get(id);
            question.setQuestion(questionString);
            question.setWeight(weight);
            question.setQuizSet(quizSet);
            question.setQuestionType(QuestionType.valueOf(questionType));
            if (image != null) {
                question.setPicture(image);
            }
            try {
                questionService.update(question);
                debugLogger.debug("Updated question " + question);
            } catch (ServiceException e) {
                errorLogger.error("Cannot update question ", question);
            }
            debugLogger.debug("Question updated id = " + id);
        }
        try {
            quizSetService.update(quizSet);
            debugLogger.debug("Updated quiz set " + quizSet);
        } catch (ServiceException e) {
            errorLogger.error("Cannot update quiz set ", quizSet);
        }
        debugLogger.debug("End of question update");
        return "Saved " + question.getQuestion() + " " + question.getWeight() + " " + question.getQuestionType() + " " + newImg;
    }

    @ResponseBody
    @RequestMapping(value = "/questionInfo", method = RequestMethod.POST)
    public QuestionDTO getQuestionInfo(@RequestParam("id") int id) {
        debugLogger.debug("Get question info id = " + id);
        Question question = questionService.get(id);
        debugLogger.debug("End of question info");
        return new QuestionDTO(question.getId(), question.getQuestion(), question.getQuestionType(),
                question.getWeight(), question.getPicture());
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetList", method = RequestMethod.POST)
    public List<QuizSetDTO> getQuizSetList() {
        debugLogger.debug("Get quiz set list");
//        List<QuizSetDTO> dtoList = new ArrayList<>();
//        for (QuizSet quizSet : quizSetService.getAll()) {
//            dtoList.add(new QuizSetDTO(quizSet.getId(), quizSet.getName()));
//        }
        debugLogger.debug("End of get quiz set list");
        return new Converter().quizSetListToQuizSetDTOList(quizSetService.getAll());
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImage(MultipartHttpServletRequest request, HttpServletRequest servletRequest) {
        debugLogger.debug("Upload image");
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            errorLogger.error("Cannot get bytes ");
        }
        String base64DataString = new String(Base64.encodeBase64(bytes));
        servletRequest.getSession().setAttribute("image", base64DataString);
        debugLogger.debug("End of upload image");
        return base64DataString;
    }
}
