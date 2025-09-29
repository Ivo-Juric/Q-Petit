package com.qpetit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBaseConnection {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/QPetitDataBase";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "[PASSWORD]";

    private DataBaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("No se encontró el driver de PostgreSQL", e);
        }
    }

    // Holder idiom: lazy y thread-safe
    private static class Holder {
        private static final DataBaseConnection INSTANCE = new DataBaseConnection();
    }

    public static DataBaseConnection getInstance() {
        return Holder.INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
