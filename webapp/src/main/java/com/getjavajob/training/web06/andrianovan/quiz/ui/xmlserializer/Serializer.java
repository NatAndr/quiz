package com.getjavajob.training.web06.andrianovan.quiz.ui.xmlserializer;

import com.getjavajob.training.web06.andrianovan.quiz.model.Answer;
import com.getjavajob.training.web06.andrianovan.quiz.model.Question;
import com.getjavajob.training.web06.andrianovan.quiz.model.QuizSet;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.AnswerDTO;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuestionDTO;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuizSetDTO;
import com.getjavajob.training.web06.andrianovan.quiz.model.dto.QuizSetDTOList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17.01.2016.
 */
public class Serializer {
    public static final String FILE_XSD = "quiz.xsd";
    private XStream xstream;

    public Serializer() {
        xstream = new XStream(new DomDriver());
        xstream.processAnnotations(AnswerDTO.class);
        xstream.processAnnotations(QuestionDTO.class);
        xstream.processAnnotations(QuizSetDTO.class);
        xstream.processAnnotations(QuizSetDTOList.class);
    }

    public String toXML(Object obj) {
        return xstream.toXML(obj).replaceAll("&quot;", "\"").replaceAll("-&gt;", "->");
    }

    public List<QuizSet> fromXML(/*String fileName*/File file) throws Exception {
//        File file = new File(fileName);
//        if (!file.exists()) {
//            throw new IllegalArgumentException("File " + fileName + " not found!");
//        }
//        String validationResult = new XSDValidator().validate(FILE_XSD, file);
//        if (!"Document is valid".equals(validationResult)) {
//            System.err.println(validationResult);
////            return null;
//        }
        QuizSetDTOList quizSetDTOList = (QuizSetDTOList) xstream.fromXML(file);
        System.out.println(quizSetDTOList);
        return transfer(quizSetDTOList);
    }

    public List<QuizSet> transfer(QuizSetDTOList quizSetDTOList) {
        List<QuizSet> quizSetList = new ArrayList<>();
        for (QuizSetDTO quizSetDTO : quizSetDTOList.getQuizSetList()) {
            QuizSet quizSet = new QuizSet(quizSetDTO.getName());
            List<Question> questions = new ArrayList<>();
            for (QuestionDTO questionDTO : quizSetDTO.getQuestionDTOs()) {
                Question question = new Question(questionDTO.getQuestion(), questionDTO.getQuestionType(), questionDTO.getWeight());
                List<Answer> answers = new ArrayList<>();
                for (AnswerDTO answerDTO : questionDTO.getAnswerDTOs()) {
                    answers.add(new Answer(answerDTO.getAnswer(), answerDTO.isCorrect()));
                }
                question.setAnswers(answers);
                questions.add(question);
            }
            quizSet.setQuestions(questions);
            quizSetList.add(quizSet);
        }
        return quizSetList;
    }
}
