package com.getjavajob.training.web06.andrianovan.quiz.model;

/**
 * Created by Nat on 30.10.2015.
 */
public class Result extends BaseEntity {

    private Student student;
    private Answer answer;
    private String inputAnswer;
    private QuizStart quizStart;
//    private Question question;

    public Result() {
    }

    public Result(Student student, Answer answer, QuizStart quizStart) {
        this.student = student;
        this.answer = answer;
        this.quizStart = quizStart;
    }

    public Result(Student student, Answer answer, QuizStart quizStart, Question question) {
        this.student = student;
        this.answer = answer;
        this.quizStart = quizStart;
//        this.question = question;
    }

    public Result(Student student, Answer answer, String inputAnswer, QuizStart quizStart) {
        this(student, answer, quizStart);
        this.inputAnswer = inputAnswer;
    }

//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getInputAnswer() {
        return inputAnswer;
    }

    public void setInputAnswer(String inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public QuizStart getQuizStart() {
        return quizStart;
    }

    public void setQuizStart(QuizStart quizStart) {
        this.quizStart = quizStart;
    }

    @Override
    public String toString() {
        return "Result{" +
                "student=" + student +
                ", answer=" + answer +
                ", inputAnswer='" + inputAnswer + '\'' +
                ", quizStart=" + quizStart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (student != null ? !student.equals(result.student) : result.student != null) return false;
        if (answer != null ? !answer.equals(result.answer) : result.answer != null) return false;
        if (inputAnswer != null ? !inputAnswer.equals(result.inputAnswer) : result.inputAnswer != null) return false;
        return !(quizStart != null ? !quizStart.equals(result.quizStart) : result.quizStart != null);

    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (inputAnswer != null ? inputAnswer.hashCode() : 0);
        result = 31 * result + (quizStart != null ? quizStart.hashCode() : 0);
        return result;
    }
}
