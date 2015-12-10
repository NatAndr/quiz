package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuestionDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.daofactory.DaoFactory;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.GeneratedQuestions;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Nat on 24.11.2015.
 */
public class GeneratedQuestionsService extends AbstractService<GeneratedQuestions> {

    private static final int DEFAULT_QUESTIONS_NUMBER = 3;
    private static final String CANNOT_GENERATE_QUESTIONS = "Cannot generate questions for ";
    private static final String QUIZ_PROPERTIES = "quiz.properties";
    private static final String QUIZ_PROPERTIES_QUESTIONS_NUMBER = "quiz.questionsNumber";
    private QuestionDao questionDao = QuestionDao.getInstance();
    private QuizStartService quizStartService = new QuizStartService();

    public GeneratedQuestionsService() {
        super(DaoFactory.getDaoFactory().getQuizGeneratedQuestionsDao());
    }

    @Override
    public void insert(GeneratedQuestions entity) throws ServiceException {
//        for (Question question : entity.getQuestions()) {
            super.insert(entity);
//        }
    }

    public GeneratedQuestions generateQuestions(QuizStart quizStart) throws ServiceException {
        int questionsNumber = getQuestionsNumberProperty();
        List<Question> generatedQuestions = new ArrayList<>(questionsNumber);
        List<Question> quizSetQuestions;
        try {
            quizSetQuestions = questionDao.getQuestionsByQuizSet(quizStart.getQuizSet());
        } catch (DaoException e) {
            throw new ServiceException(CANNOT_GENERATE_QUESTIONS + quizStart.getQuizSet() +
                    e.getLocalizedMessage());
        }
        if (questionsNumber > quizSetQuestions.size()) {
            questionsNumber = quizSetQuestions.size();
        }
        while (generatedQuestions.size() < questionsNumber) {
            Question question = quizSetQuestions.get(getRandomNumber(0, quizSetQuestions.size()));
            if (!generatedQuestions.contains(question)) {
                generatedQuestions.add(question);
            }
        }
        GeneratedQuestions quizGeneratedQuestions = new GeneratedQuestions();
        quizGeneratedQuestions.setQuizStart(quizStart);
        quizGeneratedQuestions.setQuestions(generatedQuestions);
        this.insert(quizGeneratedQuestions);
        return quizGeneratedQuestions;
    }

    private int getQuestionsNumberProperty() {
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

    public GeneratedQuestions startQuiz(QuizSet quizSet) throws ServiceException {
        QuizStart quizStart = new QuizStart(quizSet, null);
        quizStartService.insert(quizStart);
        return generateQuestions(quizStart);
    }
}
