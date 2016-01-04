package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.service.QuizSetService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 20.12.2015.
 */
@Controller
public class QuizSetController {
    @Autowired
    private QuizSetService quizSetService;

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

}
