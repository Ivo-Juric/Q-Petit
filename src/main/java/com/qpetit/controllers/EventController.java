package com.qpetit.controllers;

import com.qpetit.dao.EventDAO;
import com.qpetit.entities.Event;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventController implements Administrator {

    private final EventDAO eventDAO;
    private List<Event> eventList;

    public EventController(List<Event> events) {
        this.eventList = (events != null) ? events : new ArrayList<>();
        this.eventDAO = new EventDAO();
    }

    public EventController() {
        this.eventList = new ArrayList<>();
        this.eventDAO = new EventDAO();
    }

    // ============================
    // CREATE
    // ============================
    @Override
    public void create(Object o) {
        try {
            if (o instanceof Event) {
                Event event = (Event) o;
                eventDAO.insert(event);
                eventList.add(event);
                System.out.println("✅ Evento agregado correctamente: ID -> " + event.getIdEvent());
            } else {
                System.err.println("❌ Objeto inválido. Se esperaba un Event.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar evento: " + e.getMessage());
        }
    }

    // ============================
    // UPDATE
    // ============================
    @Override
    public void update(Object o) {
        try {
            if (o instanceof Event) {
                Event event = (Event) o;
                eventDAO.update(event);
                System.out.println("✅ Evento actualizado correctamente: ID -> " + event.getIdEvent());
            } else {
                System.err.println("❌ Objeto inválido. Se esperaba un Event.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar evento: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error inesperado: ");
            e.printStackTrace();
        }
    }

    // ============================
    // DELETE
    // ============================
    @Override
    public void delete(Object o) {
        try {
            if (o instanceof Event) {
                Event event = (Event) o;
                eventDAO.delete(event.getIdEvent());
                eventList.removeIf(e -> e.getIdEvent() == event.getIdEvent());
                System.out.println("✅ Evento eliminado correctamente: ID -> " + event.getIdEvent());
            } else if (o instanceof Integer) {
                int id = (Integer) o;
                eventDAO.delete(id);
                eventList.removeIf(e -> e.getIdEvent() == id);
                System.out.println("✅ Evento eliminado correctamente: ID -> " + id);
            } else {
                System.err.println("❌ Objeto inválido. Debe ser Event o Integer (ID).");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar evento: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error inesperado: ");
            e.printStackTrace();
        }
    }

    // ============================
    // GET ALL
    // ============================
    public List<Event> getAll() {
        try {
            eventList = eventDAO.getAll();
            return eventList;
        } catch (SQLException e) {
            System.err.println("Error al obtener lista de eventos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ============================
    // MÉTODOS FUTUROS
    // ============================
    public void assignEmployee() {
        System.out.println("⚙️ Funcionalidad pendiente: asignar empleados a un evento.");
    }

    public void assignMenu() {
        System.out.println("⚙️ Funcionalidad pendiente: asignar menú a un evento.");
    }

    public void generateServicePaper() {
        System.out.println("⚙️ Funcionalidad pendiente: generar hoja de servicio del evento.");
    }
}
