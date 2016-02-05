package com.getjavajob.training.web06.andrianovan.quiz.model;

import javax.persistence.*;

/**
 * Created by Nat on 30.10.2015.
 */
@Entity
@Table(name = "student")
public class Student extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private StudyGroup studyGroup;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;

    public Student() {
    }

    public Student(StudyGroup studyGroup, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studyGroup = studyGroup;
    }

    public Student(StudyGroup studyGroup, String firstName, String lastName, String login, String password) {
        this(studyGroup, firstName, lastName);
        this.login = login;
        this.password = password;
    }

    public Student(String login, String password) {
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
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (login != null ? !login.equals(student.login) : student.login != null) return false;
        return !(password != null ? !password.equals(student.password) : student.password != null);

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
