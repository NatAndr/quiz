package com.getjavajob.training.web06.andrianovan.quiz.model;

/**
 * Created by Nat on 30.10.2015.
 */
public class Student extends BaseEntity {

    private StudyGroup studyGroup;
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(StudyGroup studyGroup, String firstName, String lastName) {
        this(firstName, lastName);
        this.studyGroup = studyGroup;
    }

    public Student(StudyGroup studyGroup, String firstName, String lastName, String login, String password) {
        this(studyGroup, firstName, lastName);
        this.login = login;
        this.password = password;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup StudyGroup) {
        this.studyGroup = StudyGroup;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studyGroup=" + studyGroup +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        return !(lastName != null ? !lastName.equals(student.lastName) : student.lastName != null);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
