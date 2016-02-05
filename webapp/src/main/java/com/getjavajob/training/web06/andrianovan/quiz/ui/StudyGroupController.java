package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger debugLogger = LoggerFactory.getLogger("DebugLogger");
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");
    @Autowired
    private StudyGroupService studyGroupService;

    @ResponseBody
    @RequestMapping(value = "/studyGroupInfo", method = RequestMethod.POST)
    public StudyGroup getStudyGroup(@RequestParam("id") int id) {
        debugLogger.debug("Get study group info id = " + id);
        return studyGroupService.get(id);
    }

    @RequestMapping(value = "/studyGroupUpdate", method = RequestMethod.POST)
    public
    @ResponseBody
    String studyGroupUpdate(@RequestParam(value = "id") int id,
                            @RequestParam(value = "name") String name) {
        debugLogger.debug("Going to add or update study group id = " + id);
        StudyGroup studyGroup = new StudyGroup(name);
        if (id == 0) {
            try {
                studyGroupService.insert(studyGroup);
                debugLogger.debug("Added study group " + studyGroup);
            } catch (ServiceException e) {
                errorLogger.error("Cannot add study group ", studyGroup);
            }
        } else {
            studyGroup.setId(id);
            try {
                studyGroupService.update(studyGroup);
                debugLogger.debug("Updated study group " + studyGroup);
            } catch (ServiceException e) {
                errorLogger.error("Cannot update study group ", studyGroup);
            }
        }
        debugLogger.debug("End of add or update study group");
        return "Saved " + name;
    }

    @ResponseBody
    @RequestMapping(value = "/studyGroupDelete", method = RequestMethod.POST)
    public void studyGroupDelete(@RequestParam("id") int id) {
        debugLogger.debug("Going to delete study group id = " + id);
        StudyGroup studyGroup = studyGroupService.get(id);
        try {
            studyGroupService.delete(studyGroup);
            debugLogger.debug("Deleted study group " + studyGroup);
        } catch (ServiceException e) {
            errorLogger.error("Cannot delete study group ", studyGroup);
        }
        debugLogger.debug("End of delete study group");
    }
}
