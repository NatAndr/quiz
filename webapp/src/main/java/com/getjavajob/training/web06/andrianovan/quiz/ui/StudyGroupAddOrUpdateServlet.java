package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 30.11.2015.
 */
public class StudyGroupAddOrUpdateServlet extends HttpServlet {

    private StudyGroupService studyGroupService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.studyGroupService = applicationContext.getBean(StudyGroupService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudyGroup studyGroup = new StudyGroup();

        studyGroup.setGroupName(req.getParameter("name"));
        if (req.getParameter("id") != null) {
            studyGroup.setId(Integer.parseInt(req.getParameter("id")));
            try {
                studyGroupService.update(studyGroup);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                studyGroupService.insert(studyGroup);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/studyGroups");
    }
}
