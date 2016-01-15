package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class QuizStartService extends AbstractService<QuizStart> {
    private static final int DEFAULT_QUESTIONS_NUMBER = 3;
    private static final String CANNOT_GENERATE_QUESTIONS = "Cannot generate questions for ";
    private static final String QUIZ_PROPERTIES = "quiz.properties";
    private static final String QUIZ_PROPERTIES_QUESTIONS_NUMBER = "quiz.questionsNumber";
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    public QuizStartService(QuizStartDao dao) {
        super(dao);
    }

    public QuizStartService() {
    }

    public List<Question> generateQuestions(QuizStart quizStart) throws ServiceException {
        int questionsNumber = getQuestionsNumberProperty();
        List<Question> generatedQuestions = new ArrayList<>(questionsNumber);
        List<Question> quizSetQuestions = quizStart.getQuizSet().getQuestions();
        if (questionsNumber > quizSetQuestions.size()) {
            questionsNumber = quizSetQuestions.size();
        }
        while (generatedQuestions.size() < questionsNumber) {
            Question question = quizSetQuestions.get(getRandomNumber(0, quizSetQuestions.size()));
            if (!generatedQuestions.contains(question)) {
                generatedQuestions.add(question);
            }
        }
        quizStart.setGeneratedQuestions(generatedQuestions);
        return generatedQuestions;
    }

    public int getQuestionsNumberProperty() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream(QUIZ_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = props.getProperty(QUIZ_PROPERTIES_QUESTIONS_NUMBER);
        return property == null ? DEFAULT_QUESTIONS_NUMBER : Integer.parseInt(property);
    }

    public int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }
}
