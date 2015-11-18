package com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool;

import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.ConnectionException;
import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.GetPropertiesException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Nat on 05.11.2015.
 */
public class ConnectionPool {

    private static final String PROPERTIES_FILE_NAME = "quiz.properties";
    private static final String CANNOT_CREATE_CONNECTION = "Cannot create connection ";
    private static ConnectionPool instance = new ConnectionPool();
    private static BlockingQueue<Connection> pool;
    private static int poolSize;
    private static String url;
    private static String user;
    private static String password;
    private static final int DEFAULT_POOL_SIZE = 10;

    static {
        Properties props = new Properties();
        try {
            props.load(ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
            String driver = props.getProperty("database.driver");
            Class.forName(driver);
            url = props.getProperty("database.url");
            user = props.getProperty("database.user");
            password = props.getProperty("database.password");
            String propertyPoolSize = props.getProperty("connectionPool.size");
            poolSize = propertyPoolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(propertyPoolSize);
            pool = new LinkedBlockingQueue<>(poolSize);
            try {
                initConnections();
            } catch (ConnectionException | SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            try {
                throw new GetPropertiesException("Cannot load properties");
            } catch (GetPropertiesException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void releaseConnection(Connection connection) {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void initConnections() throws ConnectionException, SQLException {
        for (int i = 0; i < poolSize; i++) {
            pool.offer(createConnection());
        }
    }

    private static Connection createConnection() throws SQLException, ConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ConnectionException(CANNOT_CREATE_CONNECTION + e.getLocalizedMessage());
//            e.printStackTrace();
        }
        return connection;
    }

    public void shutdown() {
        while (!pool.isEmpty()) {
            try {
                pool.take().close();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
