package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudentDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudyGroupDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Nat on 03.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:quiz-context-dao.xml", "classpath:quiz-context-dao-overrides.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class StudentDaoTest {

    private static final int ROWS_NUMBER = 5;
    private static final String UPDATED_NEW_VALUE = "Сидоров";
    @Autowired
    private StudentDao dao;
    @Autowired
    private StudyGroupDao studyGroupDao;

    @Test
    public void testGetByID() {
        Student student = this.dao.get(1);
        assertEquals("Ivan", student.getFirstName());
        assertEquals("Ivanov", student.getLastName());
        assertEquals("java-algo01", student.getStudyGroup().getName());
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
        student.setStudyGroup(this.studyGroupDao.get(1));
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
    public void testDelete() throws DaoException {
        Student student = this.dao.get(1);
        this.dao.delete(student);
        Student student2 = this.dao.get(1);
        assertNull(student2);
    }

}
