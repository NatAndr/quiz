package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.Converter;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuizSetDTO;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuizSetDTOList;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import com.getjavajob.training.web06.andrianovan.quiz.ui.output.Output;
import com.getjavajob.training.web06.andrianovan.quiz.ui.xmlserializer.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(QuizSetController.class);
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");
    @Autowired
    private QuizSetService quizSetService;
    @Value("${quiz.XMLFileName}")
    private String fileName;

    @ResponseBody
    @RequestMapping(value = "/quizSetInfo", method = RequestMethod.POST)
    public QuizSetDTO quizSetInfo(@RequestParam("id") int id) {
        logger.debug("Show quizSet info for id = " + id);
        QuizSet quizSet = quizSetService.get(id);
        return new QuizSetDTO(quizSet.getId(), quizSet.getName());
    }

    @RequestMapping(value = "/quizSetUpdate", method = RequestMethod.POST)
    public
    @ResponseBody
    String quizSetUpdate(@RequestParam(value = "id") int id,
                         @RequestParam(value = "name") String name) {
        logger.debug("Going to add or update quiz set");
        QuizSet quizSet = new QuizSet(name);
        if (id == 0) {
            try {
                quizSetService.insert(quizSet);
                logger.debug("Added quiz set: {}", name);
            } catch (ServiceException e) {
                errorLogger.error("Cannot add quiz set: {}", name);
            }
        } else {
            quizSet.setId(id);
            try {
                quizSetService.update(quizSet);
                logger.debug("Updated quiz set: {}", quizSet);
            } catch (ServiceException e) {
                errorLogger.error("Cannot update quiz set: {}", quizSet);
            }
        }
        logger.debug("End of add or update quiz set");
        return "Saved " + name;
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetDelete", method = RequestMethod.POST)
    public void quizSetDelete(@RequestParam("id") int id) {
        logger.debug("Going to delete or update quiz set");
        QuizSet quizSet = quizSetService.get(id);
        try {
            quizSetService.delete(quizSet);
            logger.debug("Deleted quiz set: {}", quizSet);
        } catch (ServiceException e) {
            errorLogger.error("Cannot delete quiz set: {}", quizSet);
        }
        logger.debug("End of delete quiz set");
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetToXML", method = RequestMethod.POST)
    public String quizSetToXML(@RequestParam("checkedQuizzes") String[] checkedQuizzes) {
        logger.debug("Going to export quiz sets to xml");
        Converter converter = new Converter();
        QuizSetDTOList quizSetDTOList = new QuizSetDTOList();
        StringBuffer sb = new StringBuffer("Exported to XML: ");
        for (int i = 0; i < checkedQuizzes.length; i++) {
            QuizSet quizSet = quizSetService.get(Integer.parseInt(checkedQuizzes[i]));
            quizSetDTOList.getQuizSetList().add(converter.quizSetToQuizSetDTO(quizSet));
            sb.append(quizSet.getName()).append(',').append(' ');
        }
        String xml = new Serializer().toXML(quizSetDTOList);
        logger.debug("Quiz sets exported to xml");
        new Output().stringToFile(new File(fileName), xml);
        logger.debug("Written xml file");
        logger.debug("End of export quiz sets to xml");
        return sb.deleteCharAt(sb.length() - 2).toString();
    }

    @ResponseBody
    @RequestMapping(value = "/quizSetFromXML", method = RequestMethod.POST)
    public String quizSetFromXML(MultipartHttpServletRequest request) {
        logger.debug("Going to import quiz sets from xml");
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        File xmlFile = null;
        try {
            xmlFile = multipartToFile(file);
        } catch (IOException e) {
            errorLogger.error("Cannot convert MultipartFile: {} to File", file);
        }
        StringBuffer sb = new StringBuffer("Imported from XML: ");
        List<QuizSet> quizSetList = null;
        try {
            quizSetList = new Serializer().fromXML(xmlFile);
            logger.debug("Quiz sets imported from xml");
        } catch (Exception e) {
            errorLogger.error("Cannot import quiz set from xml");
        }
        if (quizSetList != null) {
            for (QuizSet quizSet : quizSetList) {
                try {
                    quizSetService.insert(quizSet);
                    logger.debug("Quiz sets from xml created");
                } catch (ServiceException e) {
                    errorLogger.error("Cannot create quiz set {} from xml", quizSet);
                }
                sb.append(quizSet.getName()).append(',').append(' ');
            }
        }
        logger.debug("End of import quiz sets from xml");
        return sb.deleteCharAt(sb.length() - 2).toString();
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convertedFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convertedFile);
        return convertedFile;
    }

}
