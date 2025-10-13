package com.qpetit.controllers;

import com.qpetit.dao.DataBaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventController implements EventAdministrator{
    private static DataBaseConnection DBConnection;

    public EventController(){}

    //@Override
    public static void createEvent() throws SQLException {

        String sql = "INSERT INTO Evento (id_Evento, fecha_Inicio, fecha_Fin) " +
                "VALUES (nextval('Evento_id_seq'), ?, ?-)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 1);
            pstmt.setDate(2, Date.valueOf("1996-05-07"));
            pstmt.setDate(2, Date.valueOf("1996-05-10"));

            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateEvent() {

    }

    @Override
    public void deleteEvent() {

    }

    @Override
    public void assignEmployee() {

    }

    @Override
    public void assignMenu() {

    }

    @Override
    public void generateServicePaper() {

    }
}
