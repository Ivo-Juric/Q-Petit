package com.qpetit.dao;

import com.qpetit.entities.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // =======================
    // INSERT
    // =======================
    public void insert(Event event) throws SQLException {
        String sql = """
                INSERT INTO Events
                (start_date, end_date, guest_count, event_type_ID, menu_type_ID,
                 supplier_ID, service_sheet_link, customer_ID, menu_ID, status_ID)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
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

    // =======================
    // SELECT ALL
    // =======================
    public List<Event> getAll() throws SQLException {
        List<Event> events = new ArrayList<>();

        String sql = """
                SELECT e.event_ID, e.start_date, e.end_date, e.guest_count,
                       e.event_type_ID, e.menu_type_ID, e.supplier_ID,
                       e.service_sheet_link, e.customer_ID, e.menu_ID, e.status_ID,
                       et.description AS event_type_desc,
                       mt.description AS menu_type_desc,
                       s.name AS supplier_name,
                       st.description AS status_desc
                FROM Events e
                LEFT JOIN EventTypes et ON e.event_type_ID = et.event_type_ID
                LEFT JOIN MenuTypes mt ON e.menu_type_ID = mt.type_ID
                LEFT JOIN Suppliers s ON e.supplier_ID = s.supplier_ID
                LEFT JOIN Status st ON e.status_ID = st.status_ID
                """;

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                events.add(mapRowToEvent(rs));
            }
        }
        return events;
    }

    // =======================
    // SELECT BY ID
    // =======================
    public Event getById(int id) throws SQLException {
        Event event = null;

        String sql = """
                SELECT e.event_ID, e.start_date, e.end_date, e.guest_count,
                       e.event_type_ID, e.menu_type_ID, e.supplier_ID,
                       e.service_sheet_link, e.customer_ID, e.menu_ID, e.status_ID
                FROM Events e
                WHERE e.event_ID = ?
                """;

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
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

    // =======================
    // UPDATE
    // =======================
    public void update(Event event) throws SQLException {
        String sql = """
                UPDATE Events
                SET start_date=?, end_date=?, guest_count=?, event_type_ID=?,
                    menu_type_ID=?, supplier_ID=?, service_sheet_link=?,
                    customer_ID=?, menu_ID=?, status_ID=?
                WHERE event_ID=?
                """;

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
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

    // =======================
    // DELETE
    // =======================
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Events WHERE event_ID=?";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // =======================
    // MAPPER
    // =======================
    private Event mapRowToEvent(ResultSet rs) throws SQLException {
        int idEvent = rs.getInt("event_ID");
        LocalDateTime fechaInicio = rs.getTimestamp("start_date").toLocalDateTime();
        LocalDateTime fechaFin = rs.getTimestamp("end_date").toLocalDateTime();
        int cantidadInvitados = rs.getInt("guest_count");
        int idTipoEvento = rs.getInt("event_type_ID");
        int tipoMenu = rs.getInt("menu_type_ID");
        int idProveedor = rs.getInt("supplier_ID");
        String linkHojaServicio = rs.getString("service_sheet_link");
        String idCliente = rs.getString("customer_ID");
        int idMenu = rs.getInt("menu_ID");
        int idEstado = rs.getInt("status_ID");

        return new Event(
                idEvent,
                fechaInicio,
                fechaFin,
                cantidadInvitados,
                idTipoEvento,
                tipoMenu,
                idProveedor,
                linkHojaServicio,
                idCliente,
                idMenu,
                idEstado
        );
    }
}
