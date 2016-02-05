package com.getjavajob.training.web06.andrianovan.quiz.dao.connector.pool;

import com.getjavajob.training.web06.andrianovan.quiz.dao.exception.DaoException;
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
    public static final String CANNOT_GET_CONNECTION = "Cannot get connection ";
    private static final String CANNOT_SHUTDOWN_CONNECTION_POOL = "Cannot shutdown connection pool ";
    private static final String CANNOT_LOAD_PROPERTIES = "Cannot load properties";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<Connection> pool;
    private int poolSize;
    private String url;
    private String user;
    private String password;
    private ThreadLocal<ConnectionHolder> connectionHolder = new ThreadLocal<>();
    private volatile boolean stop;

    private ConnectionPool() {
        getProperties();
        pool = new LinkedBlockingQueue<>(poolSize);
        try {
            initConnections();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private void getProperties() {
        Properties props = new Properties();
        try {
            props.load(ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            try {
                throw new GetPropertiesException(CANNOT_LOAD_PROPERTIES);
            } catch (GetPropertiesException e1) {
                e1.printStackTrace();
            }
        }
        String driver = props.getProperty("database.driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        url = props.getProperty("database.url");
        user = props.getProperty("database.user");
        password = props.getProperty("database.password");
        String propertyPoolSize = props.getProperty("connectionPool.size");
        poolSize = propertyPoolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(propertyPoolSize);
    }

    public Connection getConnection() throws DaoException {
        if (stop) {
            return null;
        }
        ConnectionHolder ch;
        if (connectionHolder.get() == null) {
            try {
                Connection connection = pool.take();
                ch = new ConnectionHolder(connection);
                connectionHolder.set(ch);
            } catch (InterruptedException e) {
                throw new DaoException(CANNOT_CREATE_CONNECTION + e.getLocalizedMessage());
            }
        }
        connectionHolder.get().incrementCounter();
        return connectionHolder.get().getConnection();
    }


    public void releaseConnection() {
        Connection connection = connectionHolder.get().getConnection();
        connectionHolder.get().decrementCounter();
        if (connectionHolder.get().getCounter() == 0) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connectionHolder.remove();
        }
    }

    private void initConnections() throws DaoException {
        for (int i = 0; i < poolSize; i++) {
            pool.offer(createConnection());
        }
    }

    private Connection createConnection() throws DaoException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            throw new DaoException(CANNOT_GET_CONNECTION + e.getLocalizedMessage());
        }
        return connection;
    }

    public void shutdown() throws DaoException {
        for (int i = 0; i < poolSize; i++) {
            try {
                this.pool.take().close();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }

//        stop = true;
//        while (this.poolSize != this.pool.size()) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        while (!pool.isEmpty()) {
//            try {
//                this.pool.take().close();
//            } catch (SQLException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public int getSize() {
        return pool.size();
    }
}
