package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
import org.springframework.stereotype.Controller;
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
@Controller
public class StudyGroupInfoServlet extends HttpServlet {

    private StudyGroupService studyGroupService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.studyGroupService = applicationContext.getBean(StudyGroupService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudyGroup studyGroup = studyGroupService.get(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("studyGroup", studyGroup);
        req.getRequestDispatcher("/WEB-INF/jsp/studyGroupInfo.jsp").forward(req, resp);;
    }
}
