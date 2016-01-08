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

import java.util.Arrays;
import java.util.HashMap;
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

    @RequestMapping(value="/questionUpdate", method=RequestMethod.POST)
    public @ResponseBody String questionUpdate(@RequestParam(value = "id") int id,
                                              @RequestParam(value = "question") String questionString,
                                              @RequestParam(value = "weight") int weight,
                                              @RequestParam(value = "quizId") int quizId,
                                              @RequestParam(value = "questionType") String questionType,
                                              @RequestParam(value = "questionImage") String questionImage) throws ServiceException {
        Question question = new Question(questionString, QuestionType.valueOf(questionType), weight);
        QuizSet quizSet = quizSetService.get(quizId);

        String newImg = null;
        if (questionImage.length() != 0) {
            question.setPicture(Base64.decodeBase64(questionImage)); // for field byte[]
//            question.setPicture(questionImage); //for String field
            newImg = " image";
        }

        if (id == 0) {
            questionService.insert(question);
        } else {
            question.setId(id);
            questionService.update(question);
        }
        quizSetService.linkQuestionToQuizSet(quizSet, question);
        return "Saved " + question + " " + weight + " " + questionType + newImg;
    }

    @ResponseBody
    @RequestMapping(value = "/questionInfo", method = RequestMethod.POST)
    public HashMap<String, Object> getQuestionInfo(@RequestParam("id") int id) {
        HashMap<String, Object> map = new HashMap<>();
        Question question = questionService.get(id);
        map.put("question", question);
        if (question.getPicture() != null) {
            map.put("picture", new String(Base64.encodeBase64(question.getPicture())));
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetList", method = RequestMethod.POST)
    public List<QuizSet> getQuizSetList() {
        return quizSetService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String/*HashMap<String, Object>*/ uploadImage(MultipartHttpServletRequest request) throws Exception {

//        MultipartFile multipartFile = request.getFile("file");
//        Long size = multipartFile.getSize();
//        String contentType = multipartFile.getContentType();
//        InputStream stream = multipartFile.getInputStream();
//        byte[] bytes = IOUtils.toByteArray(stream);
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("fileoriginalsize", size);
//        map.put("contenttype", contentType);
//        map.put("base64", new String(Base64Utils.encode(bytes)));
//        map.put("base64", new String(encodeBase64 , "UTF-8"));

//        InputStream is = request.getInputStream();
//        byte[] bytes = IOUtils.toByteArray(is);
//        byte[] encodeBase64 = Base64.encodeBase64(bytes);
////        String base64DataString = new String(encodeBase64 , "UTF-8");
//        String base64DataString = new String(Base64Utils.encode(bytes));


        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        byte[] bytes = file.getBytes();
        String base64DataString = new String(Base64.encodeBase64(bytes));

        System.out.println("bytes = " + Arrays.toString(bytes));
        System.out.println("base64DataString = " + base64DataString);

//        return "<img src=\'data:image/jpeg;base64," + base64DataString + "\'>";
        return base64DataString;
    }

}
