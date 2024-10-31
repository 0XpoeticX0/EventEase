package com.mycompany.eventmanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    public static Connection getConnection() throws SQLException {

        System.out.println(DatabaseConfig.getDatabaseUrl());

        return DriverManager.getConnection(
            DatabaseConfig.getDatabaseUrl(),
            DatabaseConfig.getUsername(),
            DatabaseConfig.getPassword()
        );
    }
}


