package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    // --- OPCIÓN A: Puerto fijo ---
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/QPetitDataBase";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
