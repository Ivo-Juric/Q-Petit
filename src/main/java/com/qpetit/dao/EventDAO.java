package com.qpetit.dao;

import com.qpetit.entities.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class EventDAO {

    public void insert(Event event) throws SQLException {
        String sql = "INSERT INTO Events " +
                "(start_date, end_date, guest_count, event_type_id, menu_type_id, supplier_id, " +
                "service_sheet_link, customer_id, menu_id, status_id " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(event.getStartDate()));
            stmt.setTimestamp(2, Timestamp.valueOf(event.getEndDate()));
            stmt.setInt(3, event.getGuestCount());
            stmt.setInt(4, event.getEventTypeId());
            stmt.setInt(5, event.getMenuType());
            stmt.setInt(6, event.getSupplierId());
            stmt.setString(7, event.getServiceSheetLink());
            stmt.setString(8, event.getCustomerId());
            stmt.setInt(9, event.getMenuId());
            stmt.setInt(10, event.getStatusId());

            stmt.executeUpdate();
        }
    }

    public List<Event> getAll() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
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
        String sql = "SELECT * FROM Events WHERE event_ID = ?";
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

    public void update(Event event) throws SQLException {
        String sql = "UPDATE Events SET start_date=?, end_date=?, guest_count=?, event_type_ID=?, menu_type_ID=?, supplier_ID=?, service_sheet_link=?, customer_ID=?, menu_ID=?, status_ID=? WHERE event_ID=?";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(event.getStartDate()));
            stmt.setTimestamp(2, Timestamp.valueOf(event.getEndDate()));
            stmt.setInt(3, event.getGuestCount());
            stmt.setInt(4, event.getEventTypeId());
            stmt.setInt(5, event.getMenuType());
            stmt.setInt(6, event.getSupplierId());
            stmt.setString(7, event.getServiceSheetLink());
            stmt.setString(8, event.getCustomerId());
            stmt.setInt(9, event.getMenuId());
            stmt.setInt(10, event.getStatusId());
            stmt.setInt(11, event.getEventId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Events WHERE event_ID=?";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Event mapRowToEvent(ResultSet rs) throws SQLException {
        int eventId = rs.getInt("event_ID");
        LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
        LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
        int guestCount = rs.getInt("guest_count");
        int eventTypeId = rs.getInt("event_type_ID");
        int menuTypeId = rs.getInt("menu_type_ID");
        int supplierId = rs.getInt("supplier_ID");
        String serviceSheetLink = rs.getString("service_sheet_link");
        String customerId = rs.getString("customer_ID");
        int menuId = rs.getInt("menu_ID");
        int statusId = rs.getInt("status_ID");

        return new Event(eventId, startDate, endDate, guestCount, eventTypeId,
                menuTypeId, supplierId, serviceSheetLink, customerId, menuId, statusId);
    }
}
