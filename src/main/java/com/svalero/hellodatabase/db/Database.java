package com.svalero.hellodatabase.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.svalero.hellodatabase.util.Constants.CONNECTION_STRING;
import static com.svalero.hellodatabase.util.Constants.DRIVER;

public class Database {

    private Connection connection;
    private String username;
    private String password;

    public Database(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(CONNECTION_STRING, username, password);
    }

    public void close() throws SQLException{
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}

