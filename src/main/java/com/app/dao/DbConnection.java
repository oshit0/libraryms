package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/dblibrary_ms?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    //private static final String DRIVER = "com.mysql.jdbc.Driver";

    public Connection connect() throws SQLException{
            // Class.forName(driver);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
