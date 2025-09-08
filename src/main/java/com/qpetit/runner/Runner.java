package com.qpetit.runner;

import com.qpetit.dao.EventDAO;
import com.qpetit.entities.Event;
import dao.DataBaseConnection;

import java.sql.Connection;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try {
            // primero probamos la conexión
            try (Connection conn = DataBaseConnection.getConnection()) {
                System.out.println("Conexión exitosa a la base de datos");
            } catch (Exception ex) {
                System.err.println("Error conectando a la base: " + ex.getMessage());
                ex.printStackTrace();
                return; // si falla la conexión, no seguimos
            }

            // ahora traemos y mostramos los eventos
            EventDAO dao = new EventDAO();
            List<Event> eventos = dao.getAll();

            if (eventos.isEmpty()) {
                System.out.println("No hay eventos cargados en la base.");
            } else {
                for (Event ev : eventos) {
                    System.out.println("Evento ID: " + ev.getIdEvent());
                    System.out.println("Inicio: " + ev.getFechaInicio());
                    System.out.println("Fin: " + ev.getFechaFin());
                    System.out.println("Invitados: " + ev.getCantidadInvitados());
                    System.out.println("Tipo evento: " + ev.getIdTipoEvento());
                    System.out.println("Cliente DNI: " + ev.getIdCliente());
                    System.out.println("Estado: " + ev.getIdEstado());
                    System.out.println("-------------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
