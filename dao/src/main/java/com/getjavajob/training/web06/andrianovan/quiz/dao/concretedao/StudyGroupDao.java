package com.getjavajob.training.web06.andrianovan.quiz.dao.concretedao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.abstractdao.AbstractDao;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import com.getjavajob.training.web06.andrianovan.quiz.model.StudyGroup;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nat on 30.10.2015.
 */
@Repository
public class StudyGroupDao extends AbstractDao<StudyGroup> {
    private static final String TABLE_NAME = "Study_group";
    public static final String INSERT = "INSERT INTO Study_group (group_name) VALUES (?)";
    public static final String UPDATE = "UPDATE Study_group SET group_name=? WHERE id=?";

    public StudyGroupDao() {
    }

//    @Autowired
//    public StudyGroupDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
//        super(dataSource, jdbcTemplate);
//    }

    @Override
    protected StudyGroup createInstanceFromResult(ResultSet resultSet) throws DaoException {
        StudyGroup studyGroup = new StudyGroup();
        try {
            studyGroup.setId(resultSet.getInt("id"));
            studyGroup.setName(resultSet.getString("group_name"));
        } catch (SQLException e) {
            throw new DaoException(CANNOT_CREATE_INSTANCE + this.getClass().getSimpleName());
        }
        return studyGroup;
    }

    @Override
    protected Object[] getEntityFields(StudyGroup entity) {
        return new Object[]{entity.getName()};
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getInsertStatement() {
        return INSERT;
    }

    @Override
    protected String getUpdateByIdStatement() {
        return UPDATE;
    }

    @Override
    public StudyGroup get(int id) {
        return entityManager.find(StudyGroup.class, id);
    }

    @Override
    public List<StudyGroup> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudyGroup> criteriaQuery = criteriaBuilder.createQuery(StudyGroup.class);
        CriteriaQuery<StudyGroup> select = criteriaQuery.select(criteriaQuery.from(StudyGroup.class));
        return entityManager.createQuery(select).getResultList();
    }
}
