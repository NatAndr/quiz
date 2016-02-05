package com.getjavajob.training.web06.andrianovan.quiz.service;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.QuizStartDao;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizStart;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nat on 09.11.2015.
 */
@Service
public class QuizStartService extends AbstractService<QuizStart> {
    @Value("${quiz.questionsNumber}")
    private int questionsNumber;

    @Autowired
    public QuizStartService(QuizStartDao dao) {
        super(dao);
    }

    public QuizStartService() {
    }

    public List<Question> generateQuestions(QuizStart quizStart) throws ServiceException {
        int questionsNumber = this.questionsNumber;
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

    public int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }
}
