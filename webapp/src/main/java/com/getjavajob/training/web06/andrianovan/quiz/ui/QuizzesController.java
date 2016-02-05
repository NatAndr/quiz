package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * Created by user on 20.12.2015.
 */
@Controller
public class QuizzesController {
    private static final Logger debugLogger = LoggerFactory.getLogger("DebugLogger");
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");
    private static final int STUDENT_ID = 1;
    private static final String CANNOT_SAVE_RESULT = "Cannot save result ";
    @Autowired
    private QuizSetService quizSetService;
    @Autowired
    private QuizStartService quizStartService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ResultService resultService;
    @Value("${quiz.time}")
    private int time;
    @Value("${quiz.questionsNumber}")
    private int questionsNumber;

    @RequestMapping(value = "/search")
    public ModelAndView showQuizzesSearch() {
        debugLogger.debug("Show quizzes search");
        return new ModelAndView("quizzesSearch");
    }

    @RequestMapping(value = "/quizzesSearch", method = RequestMethod.GET)
    public ModelAndView doQuizzesSearch(@RequestParam("searchParams") String searchParams) {
        debugLogger.debug("Quizzes search for " + searchParams);
        ModelAndView modelAndView = new ModelAndView("quizzesSearch");
        if (searchParams != null) {
            List<QuizSet> quizzes = null;
            try {
                quizzes = quizSetService.searchQuizSetBySubstring(searchParams);
                debugLogger.debug("Found quizzes");
            } catch (ServiceException e) {
                errorLogger.error("Cannot find quiz by substring {}", searchParams);
            }
            modelAndView.addObject("foundQuiz", quizzes);
        }
        debugLogger.debug("End of quizzes search");
        return modelAndView;
    }

    @RequestMapping(value = "/quizInfo", method = RequestMethod.GET)
    public ModelAndView showQuizInfo(@RequestParam("id") int id) {
        debugLogger.debug("Show quiz info for " + id);
        ModelAndView modelAndView = new ModelAndView("quizInfo");
        QuizSet quizSet = quizSetService.get(id);
        modelAndView.addObject("quiz", quizSet);
        modelAndView.addObject("questionsNumber", this.questionsNumber);
        debugLogger.debug("End of show quiz info");
        return modelAndView;
    }

    @RequestMapping(value = "/initializeQuiz", method = RequestMethod.POST)
    public ModelAndView startQuiz(@RequestParam("id") int id,
                                  HttpServletRequest req) {
        debugLogger.debug("Initialize quiz for quiz set id = " + id);
        clearSessionQuizAttributes(req);
        ModelAndView modelAndView = new ModelAndView("quizContainer");
        QuizSet quizSet = quizSetService.get(id);
        QuizStart quizStart;
        quizStart = new QuizStart(quizSet);
        try {
            quizStartService.generateQuestions(quizStart);
            debugLogger.debug("Generated questions for quiz start " + quizStart);
        } catch (ServiceException e) {
            errorLogger.error("Cannot generate questions for quizStart: {}", quizStart);
        }
        try {
            quizStartService.insert(quizStart);
            debugLogger.debug("Created quiz start " + quizStart);
        } catch (ServiceException e) {
            errorLogger.error("Cannot insert quizStart: {}", quizStart);
        }
        HttpSession session = req.getSession();
        session.setAttribute("counter", 0);
        session.setAttribute("quizStart", quizStart);
        session.setAttribute("questionsNumber", quizStart.getGeneratedQuestions().size());
        session.setAttribute("quizSetName", quizStart.getQuizSet().getName());
        modelAndView.addObject("time", this.time);
        debugLogger.debug("End of initialize quiz");
        return modelAndView;
    }

    @RequestMapping(value = "/showQuestion")
    public String showQuestion(ModelMap model, HttpServletRequest req) {
        debugLogger.debug("Show question");
        HttpSession session = req.getSession();
        int counter = (int) session.getAttribute("counter");
        QuizStart quizStart = (QuizStart) session.getAttribute("quizStart");
        model.addAttribute("question", quizStart.getGeneratedQuestions().get(counter));
        debugLogger.debug("End of show question");
        return "quizRunPart";
    }

    @RequestMapping(value = "/quizQuestion", method = RequestMethod.POST)
    public String processQuiz(@RequestParam("inputAnswer") String inputAnswer,
                              @RequestParam("answers") String[] answers,
                              ModelMap model,
                              HttpServletRequest req) {
        debugLogger.debug("Process answer: inputAnswer = {}, answers = {}", inputAnswer, answers);
        HttpSession session = req.getSession();
//        Student student = studentService.get(STUDENT_ID);
        String login = (String) session.getAttribute("login");
        Student student = studentService.getStudentByLogin(login);
        QuizStart quizStart = (QuizStart) session.getAttribute("quizStart");
        int counter = (int) session.getAttribute("counter") + 1;
        saveResult(inputAnswer, answers, student, quizStart);
        if (counter == session.getAttribute("questionsNumber")) {
            try {
                String result = String.valueOf(resultService.calculateQuizResult(student, quizStart));
                session.setAttribute("result", result);
                debugLogger.debug("Calculated quiz result: ", result);
            } catch (ServiceException e) {
                errorLogger.error("Cannot calculate quiz result for student: {}, quizStart: {}", student, quizStart);
            }
            return "quizResult";
        }
        session.setAttribute("counter", counter);
        model.addAttribute("question", quizStart.getGeneratedQuestions().get(counter));
        debugLogger.debug("End of Process answer");
        return "quizRunPart";
    }

    private void saveResult(String inputAnswer, String[] results, Student student, QuizStart quizStart) {
        debugLogger.debug("Going to save result for inputAnswer = {}, results = {}, student = {}, quizStart = {} ",
                inputAnswer, results, student, quizStart);
        if (Objects.equals(inputAnswer, "")) {
            inputAnswer = null;
        }
        for (int i = 0; i < results.length; i++) {
            Answer answer = answerService.get(Integer.parseInt(results[i]));
            Result result = new Result(student, answer, inputAnswer, quizStart);
            try {
                resultService.insert(result);
                debugLogger.debug("Saved result " + result);
            } catch (ServiceException e) {
                errorLogger.error("Cannot save result: {}", result);
            }
        }
        debugLogger.debug("End of save result");
    }

    @RequestMapping(value = "/result")
    public ModelAndView showResult() {
        debugLogger.debug("Show result");
        return new ModelAndView("quizResult");
    }

    @RequestMapping(value = "/repeat")
    public String doUpdate() {
        debugLogger.debug("Redirect to search");
        return "redirect:/search";
    }

    private void clearSessionQuizAttributes(HttpServletRequest req) {
        debugLogger.debug("Clear session quiz attributes");
        HttpSession session = req.getSession();
        session.setAttribute("result", null);
        session.setAttribute("counter", null);
        session.setAttribute("quizStart", null);
        session.setAttribute("generatedQuestions", null);
        session.setAttribute("questionsNumber", null);
        session.setAttribute("quizSet", null);
        debugLogger.debug("End of clear session quiz attributes");
    }
}
