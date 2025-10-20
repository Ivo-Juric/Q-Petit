package com.qpetit.dao;

import com.qpetit.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    // ============================
    // INSERT
    // ============================
    public void insert(Client client) throws SQLException {
        String sql = "INSERT INTO Customers (DNI, first_name, last_name, email, phone, notes) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getDNI());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getSurname());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getContactNumber());
            stmt.setString(6, client.getObservations());

            stmt.executeUpdate();
        }
    }

    // ============================
    // SELECT ALL
    // ============================
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Customers";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clients.add(mapRowToClient(rs));
            }
        }
        return clients;
    }

    // ============================
    // SELECT BY ID
    // ============================
    public Client getById(String dni) throws SQLException {
        Client client = null;
        String sql = "SELECT * FROM Customers WHERE DNI = ?";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = mapRowToClient(rs);
                }
            }
        }
        return client;
    }

    // ============================
    // UPDATE
    // ============================
    public void update(Client client) throws SQLException {
        String sql = "UPDATE Customers SET first_name = ?, last_name = ?, email = ?, phone = ?, notes = ? WHERE DNI = ?";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getContactNumber());
            stmt.setString(5, client.getObservations());
            stmt.setString(6, client.getDNI());

            stmt.executeUpdate();
        }
    }

    // ============================
    // DELETE
    // ============================
    public void delete(String dni) throws SQLException {
        String sql = "DELETE FROM Customers WHERE DNI = ?";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            stmt.executeUpdate();
        }
    }

    // ============================
    // MAPEO RESULTSET → CLIENT
    // ============================
    private Client mapRowToClient(ResultSet rs) throws SQLException {
        String dni = rs.getString("DNI");
        String name = rs.getString("first_name");
        String surname = rs.getString("last_name");
        String email = rs.getString("email");
        String contactNumber = rs.getString("phone");
        String observations = rs.getString("notes");

        return new Client(dni, name, surname, email, contactNumber, observations);
    }
}
