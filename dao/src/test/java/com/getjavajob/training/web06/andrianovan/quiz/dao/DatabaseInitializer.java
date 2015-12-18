package com.getjavajob.training.web06.andrianovan.quiz.dao;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by Nat on 11.11.2015.
 */
public class DatabaseInitializer {

    private static final String CREATE_DB_QUERY = "testDB.sql";
//
//    public void initDatabase() throws DaoException {
//        InputStream inputStream = QuizHeaderDaoTest.class.getClassLoader().getResourceAsStream(CREATE_DB_QUERY);
//        Reader reader = new InputStreamReader(inputStream);
//        Connection connection = ConnectionPool.getInstance().getConnection();
//        try {
//            RunScript.execute(connection, reader);
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            } finally {
//                ConnectionPool.getInstance().releaseConnection();
//            }
//        }
//    }

    public EmbeddedDatabase initDatabase() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(CREATE_DB_QUERY)
                .build();
    }

}
