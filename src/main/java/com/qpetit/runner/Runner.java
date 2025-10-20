package com.qpetit.runner;

import com.qpetit.controllers.CentralController;
import com.qpetit.dao.DataBaseConnection;
import java.sql.Connection;

public class Runner {
    public static void main(String[] args) {
        try {
            try (Connection conn = DataBaseConnection.getInstance().getConnection()) {
                System.out.println("Conexión exitosa a la base de datos");
            } catch (Exception ex) {
                System.err.println("Error conectando a la base: " + ex.getMessage());
                ex.printStackTrace();
                return;
            }

            // Inicializar controlador central que crea la UI y carga datos
            new CentralController();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}