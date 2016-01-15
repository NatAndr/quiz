package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
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
public class StudyGroupController {
    @Autowired
    private StudyGroupService studyGroupService;

    @ResponseBody
    @RequestMapping(value = "/studyGroupInfo", method = RequestMethod.POST)
    public StudyGroup getStudyGroup(@RequestParam("id") int id) {
        return studyGroupService.get(id);
    }

    @RequestMapping(value="/studyGroupUpdate",method=RequestMethod.POST)
    public @ResponseBody String studyGroupUpdate(@RequestParam(value = "id") int id,
                                                 @RequestParam(value = "name") String name) throws ServiceException {
        StudyGroup studyGroup = new StudyGroup(name);
        System.out.println("studyGroup id = " + studyGroup.getId());
        System.out.println("studyGroup name = " + studyGroup.getName());

        if (id == 0) {
            studyGroupService.insert(studyGroup);
        } else {
            studyGroup.setId(id);
            studyGroupService.update(studyGroup);
        }
        return "Saved " + name;
    }

    @ResponseBody
    @RequestMapping(value = "/studyGroupDelete", method = RequestMethod.POST)
    public void studyGroupDelete(@RequestParam("id") int id) throws ServiceException {
        StudyGroup studyGroup = studyGroupService.get(id);
        studyGroupService.delete(studyGroup);
    }

}
