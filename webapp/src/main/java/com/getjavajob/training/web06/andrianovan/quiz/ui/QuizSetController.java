package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.Converter;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuizSetDTOList;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import com.getjavajob.training.web06.andrianovan.quiz.service.output.Output;
import com.getjavajob.training.web06.andrianovan.quiz.ui.xmlserializer.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 20.12.2015.
 */
@Controller
public class QuizSetController {
    @Autowired
    private QuizSetService quizSetService;
    @Value("${quiz.XMLFileName}")
    private String fileName;

    @ResponseBody
    @RequestMapping(value = "/quizSetInfo", method = RequestMethod.POST)
    public QuizSet quizSetInfo(@RequestParam("id") int id) {
        return quizSetService.get(id);
    }

    @RequestMapping(value="/quizSetUpdate",method=RequestMethod.POST)
    public @ResponseBody String quizSetUpdate(@RequestParam(value = "id") int id,
                                                 @RequestParam(value = "name") String name) throws ServiceException {
        QuizSet quizSet = new QuizSet(name);
        if (id == 0) {
            quizSetService.insert(quizSet);
        } else {
            quizSet.setId(id);
            quizSetService.update(quizSet);
        }
        return "Saved " + name;
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetDelete", method = RequestMethod.POST)
    public void quizSetDelete(@RequestParam("id") int id) throws ServiceException {
        QuizSet quizSet = quizSetService.get(id);
        quizSetService.delete(quizSet);
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetToXML", method = RequestMethod.POST)
    public void quizSetToXML(@RequestParam("checkedQuizzes") String[] checkedQuizzes) {
        Converter converter = new Converter();
        QuizSetDTOList quizSetDTOList = new QuizSetDTOList();
        for (int i = 0; i < checkedQuizzes.length; i++) {
            quizSetDTOList.getQuizSetList().add(converter.quizSetToQuizSetDTO(quizSetService.get(Integer.parseInt(checkedQuizzes[i]))));
        }
        String xml = new Serializer().toXML(quizSetDTOList);
        System.out.println(xml);
        new Output().stringToFile(new File(fileName), xml);
    }

//    @ResponseBody
//    @RequestMapping(value = "/quizSetFromXML", method = RequestMethod.POST)
//    public void quizSetFromXML(@RequestParam("filename") String filename) throws Exception {
//        new Serializer().fromXML(filename);
//    }

    @ResponseBody
    @RequestMapping(value = "/quizSetFromXML", method = RequestMethod.POST)
    public void quizSetFromXML(MultipartHttpServletRequest request) throws Exception {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        File xmlFile = multipartToFile(file);
        List<QuizSet> quizSetList = new Serializer().fromXML(xmlFile);
        for (QuizSet quizSet : quizSetList) {
            quizSetService.insert(quizSet);
        }
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

}
