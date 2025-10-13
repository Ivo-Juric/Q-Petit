package com.qpetit.dao;

import com.qpetit.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    /**
     * Insert a new client into Customers table.
     * DNI is provided by the Client object (primary key).
     */
    public void insert(Client client) throws SQLException {
        String sql = "INSERT INTO Customers (DNI, first_name, last_name, email, phone, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

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

    /**
     * Return all clients.
     */
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT DNI, first_name, last_name, email, phone, notes FROM Customers";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clients.add(mapRowToClient(rs));
            }
        }
        return clients;
    }

    /**
     * Get a client by DNI. Strict: throws SQLException if not found.
     */
    public Client getByDni(String dni) throws SQLException {
        String sql = "SELECT DNI, first_name, last_name, email, phone, notes FROM Customers WHERE DNI = ?";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToClient(rs);
                } else {
                    throw new SQLException("Client with DNI='" + dni + "' not found in Customers table.");
                }
            }
        }
    }




    /**
     * Update an existing client; throws SQLException if the client does not exist.
     */
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

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Update failed: client with DNI='" + client.getDNI() + "' does not exist.");
            }
        }
    }

    /**
     * Delete a client by DNI; throws SQLException if the client does not exist.
     */
    public void delete(String dni) throws SQLException {
        String sql = "DELETE FROM Customers WHERE DNI = ?";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Delete failed: client with DNI='" + dni + "' does not exist.");
            }
        }
    }

    /**
     * Helper: map current ResultSet row to Client object.
     */
    private Client mapRowToClient(ResultSet rs) throws SQLException {
        String dni = rs.getString("DNI");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String notes = rs.getString("notes");

        return new Client(dni, firstName, lastName, email, phone, notes);
    }
}
