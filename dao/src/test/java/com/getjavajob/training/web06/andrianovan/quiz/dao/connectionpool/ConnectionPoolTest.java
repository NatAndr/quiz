package com.getjavajob.training.web06.andrianovan.quiz.dao.connectionpool;

import com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool.ConnectionPool;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Nat on 18.11.2015.
 */
public class ConnectionPoolTest {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

//    @Test
//    public void testGetConnection() throws DaoException {
//        Connection connection = connectionPool.getConnection();
//        assertNotNull(connection);
//        connectionPool.releaseConnection();
//    }
//
//    @Test
//    public void testReleaseConnection() throws DaoException, SQLException {
//        Connection connection = connectionPool.getConnection();
//        connectionPool.releaseConnection();
//        assertFalse(connection.isClosed());
//    }
//
//    @Test
//    public void testGetInstance() {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        assertNotNull(connectionPool);
//    }
//
//    @AfterClass
//    public static void testShutdown() throws DaoException {
//        connectionPool.shutdown();
//        assertTrue(connectionPool.getSize() == 0);
//    }
}
