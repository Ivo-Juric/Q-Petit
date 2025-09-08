package com.qpetit.dao;

import com.qpetit.entities.Event;
import dao.DataBaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Evento table
 */
public class EventDAO {

    public void insert(Event event) throws SQLException {
        String sql = "INSERT INTO Evento " +
                "(fecha_Inicio, fecha_Fin, CantidadInvitados, id_TipoEvento, tipo_Menu, id_Proveedor, " +
                "LinkHojaServicio, id_Cliente, id_Menu, id_Estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(event.getFechaInicio()));
            stmt.setTimestamp(2, Timestamp.valueOf(event.getFechaFin()));
            stmt.setInt(3, event.getCantidadInvitados());
            stmt.setInt(4, event.getIdTipoEvento());
            stmt.setInt(5, event.getTipoMenu());
            stmt.setInt(6, event.getIdProveedor());
            stmt.setString(7, event.getLinkHojaServicio());
            stmt.setString(8, event.getIdCliente());
            stmt.setInt(9, event.getIdMenu());
            stmt.setInt(10, event.getIdEstado());

            stmt.executeUpdate();
        }
    }

    public List<Event> getAll() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Evento";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                events.add(mapRowToEvent(rs));
            }
        }
        return events;
    }

    public Event getById(int id) throws SQLException {
        Event event = null;
        String sql = "SELECT * FROM Evento WHERE id_Evento = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    event = mapRowToEvent(rs);
                }
            }
        }
        return event;
    }

    public void update(Event event) throws SQLException {
        String sql = "UPDATE Evento SET fecha_Inicio=?, fecha_Fin=?, CantidadInvitados=?, id_TipoEvento=?, tipo_Menu=?, id_Proveedor=?, LinkHojaServicio=?, id_Cliente=?, id_Menu=?, id_Estado=? WHERE id_Evento=?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(event.getFechaInicio()));
            stmt.setTimestamp(2, Timestamp.valueOf(event.getFechaFin()));
            stmt.setInt(3, event.getCantidadInvitados());
            stmt.setInt(4, event.getIdTipoEvento());
            stmt.setInt(5, event.getTipoMenu());
            stmt.setInt(6, event.getIdProveedor());
            stmt.setString(7, event.getLinkHojaServicio());
            stmt.setString(8, event.getIdCliente());
            stmt.setInt(9, event.getIdMenu());
            stmt.setInt(10, event.getIdEstado());
            stmt.setInt(11, event.getIdEvent());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Evento WHERE id_Evento=?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Event mapRowToEvent(ResultSet rs) throws SQLException {
        int idEvent = rs.getInt("id_Evento");
        LocalDateTime fechaInicio = rs.getTimestamp("fecha_Inicio").toLocalDateTime();
        LocalDateTime fechaFin = rs.getTimestamp("fecha_Fin").toLocalDateTime();
        int cantidadInvitados = rs.getInt("CantidadInvitados");
        int idTipoEvento = rs.getInt("id_TipoEvento");
        int tipoMenu = rs.getInt("tipo_Menu");
        int idProveedor = rs.getInt("id_Proveedor");
        String linkHojaServicio = rs.getString("LinkHojaServicio");
        String idCliente = rs.getString("id_Cliente");
        int idMenu = rs.getInt("id_Menu");
        int idEstado = rs.getInt("id_Estado");

        return new Event(idEvent, fechaInicio, fechaFin, cantidadInvitados, idTipoEvento,
                tipoMenu, idProveedor, linkHojaServicio, idCliente, idMenu, idEstado);
    }
}
