package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {
        try {
            Connection con = DataBaseConnection.getConnection();
            if (con != null) {
                System.out.println("Conexión exitosa!");
                con.close();
            } else {
                System.out.println("No se pudo conectar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}