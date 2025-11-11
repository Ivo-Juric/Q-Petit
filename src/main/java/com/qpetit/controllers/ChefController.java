package com.qpetit.controllers;

import com.qpetit.dao.DataBaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChefController {

    public void create(String nombre, String tarifa) throws SQLException {
        String sql = "INSERT INTO Staff (DNI, first_name, last_name, age, staff_type_ID, phone, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Generar un DNI único simple (timestamp)
            int dni = (int) (System.currentTimeMillis() % 100000000);
            
            stmt.setInt(1, dni);
            stmt.setString(2, nombre);
            stmt.setString(3, "Chef"); // Apellido por defecto
            stmt.setInt(4, 30); // Edad por defecto
            stmt.setInt(5, 1); // Tipo Chef
            stmt.setString(6, tarifa); // Guardamos tarifa en el campo teléfono por simplicidad
            stmt.setString(7, nombre.toLowerCase() + "@chef.com");

            stmt.executeUpdate();
            System.out.println("✅ Chef agregado correctamente: " + nombre);
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar chef: " + e.getMessage());
            throw e;
        }
    }

    public List<Map<String, Object>> getAll() throws SQLException {
        List<Map<String, Object>> chefs = new ArrayList<>();
        String sql = "SELECT DNI, first_name, phone FROM Staff WHERE staff_type_ID = 1";
        
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> chef = new HashMap<>();
                chef.put("nombre", rs.getString("first_name"));
                chef.put("tarifa", rs.getString("phone")); // Recuperamos tarifa del campo teléfono
                chefs.add(chef);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener chefs: " + e.getMessage());
            throw e;
        }
        return chefs;
    }

    public void delete(String nombre) throws SQLException {
        String sql = "DELETE FROM Staff WHERE first_name = ? AND staff_type_ID = 1";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Chef eliminado correctamente: " + nombre);
            } else {
                System.out.println("⚠️ No se encontró chef con nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar chef: " + e.getMessage());
            throw e;
        }
    }
}