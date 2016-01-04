package com.getjavajob.training.web06.andrianovan.quiz.ui;

import com.getjavajob.training.web06.andrianovan.quiz.model.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.*;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final int STUDENT_ID = 1;
    private static final String CANNOT_SAVE_RESULT = "Cannot save result ";
    @Autowired
    private QuizSetService quizSetService;
    @Autowired
    private QuizStartService quizStartService;
    @Autowired
    private GeneratedQuestionsService genQuestionsService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ResultService resultService;

    private QuizSet quizSet;
    private GeneratedQuestions generatedQuestions;
    private QuizStart quizStart;
    private int counter;
    private Student student;

    @RequestMapping(value = "/search")
    public ModelAndView showQuizzesSearch() {
        return new ModelAndView("quizzesSearch");
    }

    @RequestMapping(value = "/quizzesSearch", method = RequestMethod.GET)
    public ModelAndView doQuizzesSearch(@RequestParam("searchParams") String searchParams) {
        ModelAndView modelAndView = new ModelAndView("quizzesSearch");
        if (searchParams != null) {
            List<QuizSet> quizes;
            try {
                quizes = quizSetService.searchQuizSetBySubstring(searchParams);
            } catch (ServiceException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            modelAndView.addObject("foundQuiz", quizes);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/quizInfo", method = RequestMethod.GET)
    public ModelAndView showQuizInfo(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("quizInfo");
        QuizSet quizSet = quizSetService.get(id);
        modelAndView.addObject("quiz", quizSet);
        modelAndView.addObject("questionsNumber", quizSet.getQuestions().size());
        return modelAndView;
    }

    @RequestMapping(value = "/initializeQuiz", method = RequestMethod.POST)
    public ModelAndView startQuiz(@RequestParam("id") int id,
                                  HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("quizContainer");
        this.quizSet = quizSetService.get(id);
        try {
            quizStart = new QuizStart(quizSet);
            quizStartService.insert(quizStart);
            generatedQuestions = genQuestionsService.generateQuestions(quizStart);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = req.getSession();
        session.setAttribute("counter", counter);
        session.setAttribute("quizStart", quizStart);
        session.setAttribute("generatedQuestions", generatedQuestions.getQuestions());
        session.setAttribute("questionsNumber", generatedQuestions.getQuestions().size());
        session.setAttribute("quizSet", quizSet);
        return modelAndView;
    }

    @RequestMapping(value="/showQuestion")
    public String showQuestion(ModelMap model, HttpServletRequest req) {
        Question question = generatedQuestions.getQuestions().get(counter);
        model.addAttribute("question", question);
        return "quizRunPart";
    }

    @RequestMapping(value = "/quizQuestion", method = RequestMethod.POST)
    public String processQuiz(/*@RequestParam("inputAnswer") String inputAnswer,
                              @RequestParam("answer") String[] results,*/ModelMap model,
                                  HttpServletRequest req) {
        Student student = studentService.get(STUDENT_ID);
        HttpSession session = req.getSession();
        QuizStart quizStart = (QuizStart) session.getAttribute("quizStart");
        this.counter = (int) session.getAttribute("counter") + 1;
        saveResult(req, student, quizStart);

        if (this.counter == session.getAttribute("questionsNumber")) {
            try {
                session.setAttribute("result", resultService.calculateQuizResult(student, quizStart));
            } catch (ServiceException e) {
                throw new RuntimeException("Cannot get result " + student + " " + quizStart + " " + e.getLocalizedMessage());
            }
            return "quizResult";
        }
        List<Question> generatedQuestions = (List<Question>) session.getAttribute("generatedQuestions");
        session.setAttribute("counter", counter);
        model.addAttribute("question", generatedQuestions.get(counter));
        return "quizRunPart";
    }

    private void saveResult(HttpServletRequest req, Student student, QuizStart quizStart) {
        String inputAnswer = req.getParameter("inputAnswer");
        String[] results = req.getParameterValues("answer");
        if (Objects.equals(inputAnswer, "")) {
            inputAnswer = null;
        }
        for (int i = 0; i < results.length; i++) {
            Answer answer = answerService.get(Integer.parseInt(results[i]));
            Result result = new Result(student, answer, inputAnswer, quizStart);
            try {
                resultService.insert(result);
            } catch (ServiceException e) {
                throw new RuntimeException(CANNOT_SAVE_RESULT + student + answer + inputAnswer + quizStart
                        + e.getLocalizedMessage());
            }
        }
    }

    @RequestMapping(value = "/repeat")
    public String doUpdate(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("result", null);
        session.setAttribute("counter", null);
        session.setAttribute("quizStart", null);
        session.setAttribute("generatedQuestions", null);
        session.setAttribute("questionsNumber", null);
        session.setAttribute("quizSet", null);
        return "redirect:/search";
    }
}
