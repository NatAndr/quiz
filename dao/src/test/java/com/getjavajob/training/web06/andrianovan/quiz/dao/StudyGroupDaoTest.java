package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudyGroupDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
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
@ContextConfiguration(locations = {"classpath:quiz-context-dao.xml", "classpath:quiz-context-dao-test.xml"})
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudyGroupDaoTest {

    private static final int ROWS_NUMBER = 6;
    private static final String VALUE_FOR_ID_1 = "java-algo02";
    private static final String INSERTED_VALUE = "Group 4";
    private static final String UPDATED_NEW_VALUE = "Gr 1";
//    private EmbeddedDatabase db;
//    private DatabaseInitializer databaseInitializer = new DatabaseInitializer();
    @Autowired
    private StudyGroupDao dao;

//    @Before
//    public void initDatabase() throws DaoException {
//        databaseInitializer.initDatabase();
//    }

    @Test
    public void testGetByID() {
        StudyGroup studyGroup = this.dao.get(2);
        assertEquals(VALUE_FOR_ID_1, studyGroup.getGroupName());
    }

    @Test
    public void testGetAll() {
        List<StudyGroup> studyGroupList = this.dao.getAll();
        assertEquals(ROWS_NUMBER, studyGroupList.size());
    }

    @Test
    @Transactional
    public void testInsert() throws DaoException {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setGroupName(INSERTED_VALUE);
        this.dao.insert(studyGroup);
        List<StudyGroup> studyGroupList = this.dao.getAll();
        assertEquals(ROWS_NUMBER + 1, studyGroupList.size());
    }

    @Test
    @Transactional
    public void testUpdate() throws DaoException {
        StudyGroup studyGroup = this.dao.get(1);
        studyGroup.setGroupName(UPDATED_NEW_VALUE);
        this.dao.update(studyGroup);
        StudyGroup updatedStudyGroup = this.dao.get(1);
        assertEquals(UPDATED_NEW_VALUE, updatedStudyGroup.getGroupName());
    }

    @Test
    @Transactional
    public void testDelete() throws DaoException {
        StudyGroup studyGroup = this.dao.get(1);
        this.dao.delete(studyGroup);
        StudyGroup studyGroup2 = this.dao.get(1);
        assertNull(studyGroup2);
    }

//    @After
//    public void tearDown() {
//
//    }
}
