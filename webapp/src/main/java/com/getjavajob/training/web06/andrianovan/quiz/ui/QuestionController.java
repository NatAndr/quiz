package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuestionType;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 04.01.2016.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizSetService quizSetService;

    @ResponseBody
    @RequestMapping(value = "/questionDelete", method = RequestMethod.POST)
    public void questionDelete(@RequestParam("id") int id) throws ServiceException {
        Question question = questionService.get(id);
        questionService.delete(question);
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
                          HttpServletRequest servletRequest) throws ServiceException {
        Question question = new Question(questionString, QuestionType.valueOf(questionType), weight);
        QuizSet quizSet = quizSetService.get(quizId);
        question.setQuizSet(quizSet);

        String newImg = "";
        if (questionImage.length() != 0) {
            question.setPicture((String) servletRequest.getSession().getAttribute("image"));
            newImg = " image";
        }

//        if (id == 0) {
//            questionService.insert(question);
//        } else {
//            question.setId(id);
//            questionService.update(question);
//        }
        quizSet.getQuestions().add(question);
        quizSetService.update(quizSet);

//        quizSetService.linkQuestionToQuizSet(quizSet, question);
        return "Saved " + question + " " + weight + " " + questionType + " " + newImg;
    }

    @ResponseBody
    @RequestMapping(value = "/questionInfo", method = RequestMethod.POST)
    public Question getQuestionInfo(@RequestParam("id") int id) {
        return questionService.get(id);
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetList", method = RequestMethod.POST)
    public List<QuizSet> getQuizSetList() {
        return quizSetService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImage(MultipartHttpServletRequest request, HttpServletRequest servletRequest) throws Exception {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        byte[] bytes = file.getBytes();
        String base64DataString = new String(Base64.encodeBase64(bytes));
        servletRequest.getSession().setAttribute("image", base64DataString);
        return base64DataString;
    }

}
