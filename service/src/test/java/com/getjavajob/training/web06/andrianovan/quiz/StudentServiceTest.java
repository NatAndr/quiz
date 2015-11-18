package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 12.11.2015.
 */
public class StudentServiceTest {

    private static final int ROWS_NUMBER = 2;
    private StudentService studentService;
    private StudentDao dao;

    @Before
    public void onBefore() {
        this.studentService = new StudentService();
        this.dao = mock(StudentDao.class);
        this.studentService.setDao(dao);
    }

    @Test
    public void testGet() {
        StudyGroup studyGroup = new StudyGroup("New group");
        Student expected = new Student(studyGroup, "Ivan", "Ivanov");
        when(this.dao.get(1)).thenReturn(expected);

        Student actual = this.studentService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<Student> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new Student());
        }
        when(this.dao.getAll()).thenReturn(expected);

        List<Student> actual = this.studentService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException {
        Student quizHeader = new Student();
        this.studentService.insert(quizHeader);
        verify(this.dao).insert(quizHeader);
    }

    @Test
    public void testUpdate() throws DaoException {
        Student quizHeader = new Student();
        this.studentService.update(quizHeader);
        verify(this.dao).update(quizHeader);
    }

    @Test
    public void testDelete() {
        Student student = new Student();
        this.studentService.delete(student);
        verify(this.dao).delete(student);
    }

    @Test
    public void testGetStudentsByStudyGroup() {
        StudyGroup studyGroup = new StudyGroup("java-algo02");
        studyGroup.setId(2);
        Student student1 = new Student(studyGroup, "Oleg", "Sokolov");
        Student student2 = new Student(studyGroup, "Artem", "Artemov");
        List<Student> expected = Arrays.asList(student1, student2);

        when(this.dao.getStudentsByStudyGroup(studyGroup)).thenReturn(expected);
        List<Student> actual = this.studentService.getStudentsByStudyGroup(studyGroup);
        assertEquals(expected, actual);
    }
}
