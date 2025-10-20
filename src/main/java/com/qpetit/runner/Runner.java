package com.qpetit.runner;

import com.qpetit.dao.EventDAO;
import com.qpetit.entities.Event;
import com.qpetit.dao.DataBaseConnection;
import java.sql.Connection;
import java.util.List;

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
            EventDAO dao = new EventDAO();
            List<Event> eventos = dao.getAll();

            if (eventos.isEmpty()) {
                System.out.println("No hay eventos cargados en la base.");
            } else {
                for (Event ev : eventos) {
                    System.out.println("Evento ID: " + ev.getEventId());
                    System.out.println("Inicio: " + ev.getStartDate());
                    System.out.println("Fin: " + ev.getEndDate());
                    System.out.println("Invitados: " + ev.getGuestCount());
                    System.out.println("Tipo evento: " + ev.getEventTypeId());
                    System.out.println("Cliente DNI: " + ev.getCustomerId());
                    System.out.println("Estado: " + ev.getStatusId());
                    System.out.println("--------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}