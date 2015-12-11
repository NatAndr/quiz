package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.sql.DataSource;

/**
 * Created by user on 10.12.2015.
 */
public class DataSourceHolder {

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        DataSourceHolder.dataSource = dataSource;
    }
}
