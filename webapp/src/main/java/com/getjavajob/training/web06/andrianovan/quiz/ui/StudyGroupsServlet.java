package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nat on 25.11.2015.
 */
public class StudyGroupsServlet extends HttpServlet {

    private StudyGroupService studyGroupService = new StudyGroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudyGroup> studyGroups;
        studyGroups = studyGroupService.getAll();
        req.setAttribute("studyGroups", studyGroups);
        req.getRequestDispatcher("/WEB-INF/jsp/studyGroups.jsp").forward(req, resp);
    }
}
