package com.getjavajob.training.web06.andrianovan.quiz.dao;

import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nat on 11.11.2015.
 */
public class DatabaseInitializer {

    private static final String CREATE_DB_QUERY = "testDB.sql";

    public void initDatabase() throws DaoException {
        InputStream inputStream = QuizHeaderDaoTest.class.getClassLoader().getResourceAsStream(CREATE_DB_QUERY);
        Reader reader = new InputStreamReader(inputStream);
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            RunScript.execute(connection, reader);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

}
