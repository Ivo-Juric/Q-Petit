package dao;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        Connection con = DataBaseConnection.getConnection();

        if (con != null) {
            System.out.println("Conexión exitosa!");
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo conectar.");
        }
    }
}