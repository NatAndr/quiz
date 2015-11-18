package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concreatedao.StudyGroupDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 03.11.2015.
 */
public class StudentDaoTest {

    private static final int ROWS_NUMBER = 5;
    private static final String UPDATED_NEW_VALUE = "Сидоров";
    private StudentDao dao = StudentDao.getInstance();

    @Before
    public void initDatabase() {
        new DatabaseInitializer().initDatabase();
    }

    @Test
    public void testGetByID() {
        Student student = this.dao.get(1);
        assertEquals("Ivan", student.getFirstName());
        assertEquals("Ivanov", student.getLastName());
        assertEquals("java-algo01", student.getStudyGroup().getGroupName());
    }

    @Test
    public void testGetAll() {
        List<Student> studentList = this.dao.getAll();
        assertEquals(ROWS_NUMBER, studentList.size());
    }

    @Test
    public void testInsert() throws DaoException {
        Student student = new Student();
        student.setLastName("Артемов");
        student.setFirstName("Артем");
        student.setStudyGroup(StudyGroupDao.getInstance().get(1));
        this.dao.insert(student);
        List<Student> studentList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, studentList.size());
    }

    @Test
    public void testUpdate() throws DaoException {
        Student student = this.dao.get(1);
        student.setLastName(UPDATED_NEW_VALUE);
        this.dao.update(student);
        Student updatedStudent = this.dao.get(1);
        assertEquals(UPDATED_NEW_VALUE, updatedStudent.getLastName());
    }

    @Test
    public void testDelete() {
        Student student = this.dao.get(1);
        this.dao.delete(student);
        Student student2 = this.dao.get(1);
        assertNull(student2);
    }

    @Test
    public void testGetStudentsByStudyGroup() {
        List<Student> actual = dao.getStudentsByStudyGroup(StudyGroupDao.getInstance().get(2));
        List<Student> expected = Arrays.asList(dao.get(3), dao.get(4));
        assertEquals(expected, actual);
    }

}
