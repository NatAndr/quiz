package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudentService;
import com.getjavajob.training.web06.andrianovan.quiz.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nat on 12.11.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    private static final int ROWS_NUMBER = 2;
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentDao dao;

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
    public void testInsert() throws DaoException, ServiceException {
        Student student = new Student();
        this.studentService.insert(student);
        verify(this.dao).insert(student);
    }

    @Test
    public void testUpdate() throws DaoException, ServiceException {
        Student student = new Student();
        this.studentService.update(student);
        verify(this.dao).update(student);
    }

    @Test
    public void testDelete() throws ServiceException, DaoException {
        Student student = new Student();
        this.studentService.delete(student);
        verify(this.dao).delete(student);
    }
}
