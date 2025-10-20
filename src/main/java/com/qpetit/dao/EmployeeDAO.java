package com.qpetit.dao;

import com.qpetit.entities.Employee;
import com.qpetit.entities.Specialties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void insert(Employee employee) throws SQLException {
        String sql = "INSERT INTO Staff (DNI, first_name, last_name, email, is_internal, staff_type_ID, availability_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employee.getIdEmployee());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getEmail());
            stmt.setBoolean(5, employee.isInteral());
            stmt.setInt(6, getStaffTypeIdBySpecialty(conn, employee.getSpecialties()));
            stmt.setInt(7, employee.getAvailability());

            stmt.executeUpdate();
        }
    }

    public List<Employee> getAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = """
                SELECT s.DNI, s.first_name, s.last_name, s.email, s.is_internal,
                       st.description AS staff_type,
                       a.availability_ID
                FROM Staff s
                LEFT JOIN StaffType st ON s.staff_type_ID = st.type_ID
                LEFT JOIN Availability a ON s.availability_ID = a.availability_ID
                """;

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                employees.add(mapRowToEmployee(rs));
            }
        }

        return employees;
    }

    public Employee getById(int id) throws SQLException {
        Employee employee = null;
        String sql = """
                SELECT s.DNI, s.first_name, s.last_name, s.email, s.is_internal,
                       st.description AS staff_type,
                       a.availability_ID
                FROM Staff s
                LEFT JOIN StaffType st ON s.staff_type_ID = st.type_ID
                LEFT JOIN Availability a ON s.availability_ID = a.availability_ID
                WHERE s.DNI = ?
                """;

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    employee = mapRowToEmployee(rs);
                }
            }
        }

        return employee;
    }

    public void update(Employee employee) throws SQLException {
        String sql = "UPDATE Staff SET first_name=?, last_name=?, email=?, is_internal=?, staff_type_ID=?, availability_ID=? WHERE DNI=?";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setBoolean(4, employee.isInteral());
            stmt.setInt(5, getStaffTypeIdBySpecialty(conn, employee.getSpecialties()));
            stmt.setInt(6, employee.getAvailability());
            stmt.setInt(7, employee.getIdEmployee());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Staff WHERE DNI=?";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Employee mapRowToEmployee(ResultSet rs) throws SQLException {
        int id = rs.getInt("DNI");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        boolean isInternal = rs.getBoolean("is_internal");
        String staffTypeDesc = rs.getString("staff_type");
        int availability = rs.getInt("availability_ID");

        // Mapeo aproximado de descripción a enum
        Specialties specialty = Specialties.valueOf(
                staffTypeDesc != null ? staffTypeDesc.toUpperCase() : "MANAGER"
        );

        return new Employee(id, firstName, lastName, email, isInternal, specialty, availability);
    }

    // Método auxiliar para obtener el ID del tipo de staff según la especialidad
    private int getStaffTypeIdBySpecialty(Connection conn, Specialties specialty) throws SQLException {
        String sql = "SELECT type_ID FROM StaffType WHERE UPPER(description) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, specialty.name());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("type_ID");
                }
            }
        }
        // Si no existe, lo insertamos
        String insertSql = "INSERT INTO StaffType (description) VALUES (?) RETURNING type_ID";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, specialty.name());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("type_ID");
                }
            }
        }
        throw new SQLException("No se pudo asignar staff_type_ID para " + specialty);
    }

    public List<Employee> getAvailableEmployees() {
        return null;
    }
}
