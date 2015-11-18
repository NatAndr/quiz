package com.getjavajob.training.web06.andrianovan.quiz.dao.connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Nat on 30.10.2015.
 */
public class DatabaseConnector {
    private static Connection connection;

    private DatabaseConnector() {
    }

    private static class ConnectorHolder {
        public static final DatabaseConnector instance = new DatabaseConnector();
    }

    public static DatabaseConnector getInstance() {
        return ConnectorHolder.instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = connect();
        }
        return connection;
    }

    private Connection connect() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("quiz.properties"));
            String driver = props.getProperty("database.driver");
            String url = props.getProperty("database.url");
            String user = props.getProperty("database.user");
            String password = props.getProperty("database.password");
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
