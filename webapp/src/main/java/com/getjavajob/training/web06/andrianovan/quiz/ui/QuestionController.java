package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuestionService;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
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
    public void studentDelete(@RequestParam("id") int id) throws ServiceException {
        Question question = questionService.get(id);
        questionService.delete(question);
    }

    @RequestMapping(value="/questionUpdate",method=RequestMethod.POST)
    public @ResponseBody String studentUpdate(@RequestParam(value = "id") int id) {

        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/questionInfo", method = RequestMethod.POST)
    public Question getQuestion(@RequestParam("id") int id) {
        return questionService.get(id);
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetList", method = RequestMethod.POST)
    public List<QuizSet> getQuizSetList() {
        return quizSetService.getAll();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = {"application/json"})
    public @ResponseBody HashMap<String, Object> uploadImage(MultipartHttpServletRequest request,
                                                          HttpServletResponse response) throws Exception {

        MultipartFile multipartFile = request.getFile("file");
        Long size = multipartFile.getSize();
        String contentType = multipartFile.getContentType();
        InputStream stream = multipartFile.getInputStream();
        byte[] bytes = IOUtils.toByteArray(stream);

        HashMap<String, Object> map = new HashMap();
        map.put("fileoriginalsize", size);
        map.put("contenttype", contentType);
        map.put("base64", new String(Base64Utils.encode(bytes)));

        return map;
    }


}
