package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    // Cambiá el nombre de tu base y el host según corresponda
    private static final String DATABASE_NAME = "QPetitDataBase";

    // --- OPCIÓN A: Puerto fijo ---
     private static final String URL = "jdbc:sqlserver://DESKTOP-SE1Q2HO:1433;databaseName=QPetitDataBase;instance=SQLEXPRESS;encrypt=false;trustServerCertificate=true;";

    // --- OPCIÓN B: Instancia nombrada SQLEXPRESS con SQL Server Browser activo ---
   // private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=QPetitDataBase;integratedSecurity=true;";


    public static Connection getConnection() {
        try {
            // No necesitás usuario ni password con Windows Authentication
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
