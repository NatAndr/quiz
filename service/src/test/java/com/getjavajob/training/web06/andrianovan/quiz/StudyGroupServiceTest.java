package com.getjavajob.training.web06.andrianovan.quiz;

import com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao.StudyGroupDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import com.getjavajob.training.web06.andrianovan.quiz.service.StudyGroupService;
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
public class StudyGroupServiceTest {

    private static final int ROWS_NUMBER = 2;
    @Mock
    private StudyGroupDao dao;
    @InjectMocks
    private StudyGroupService studyGroupService;

    @Test
    public void testGet() {
        StudyGroup expected = new StudyGroup("New group");
        when(this.dao.get(1)).thenReturn(expected);

        StudyGroup actual = this.studyGroupService.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<StudyGroup> expected = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            expected.add(new StudyGroup());
        }
        when(this.dao.getAll()).thenReturn(expected);

        List<StudyGroup> actual = this.studyGroupService.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testInsert() throws DaoException, ServiceException {
        StudyGroup quizHeader = new StudyGroup();
        this.studyGroupService.insert(quizHeader);
        verify(this.dao).insert(quizHeader);
    }

    @Test
    public void testUpdate() throws DaoException, ServiceException {
        StudyGroup quizHeader = new StudyGroup();
        this.studyGroupService.update(quizHeader);
        verify(this.dao).update(quizHeader);
    }

    @Test
    public void testDelete() throws ServiceException, DaoException {
        StudyGroup studyGroup = new StudyGroup();
        this.studyGroupService.delete(studyGroup);
        verify(this.dao).delete(studyGroup);
    }
}
