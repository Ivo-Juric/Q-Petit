package com.qpetit.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.qpetit.entities.Event;
import javax.swing.table.DefaultTableModel;

public class EventView extends JPanel {
    private JTable table;
    private JTextField txtTipo, txtInvitados, txtFecha, txtLugar;
    private com.qpetit.controllers.CentralController controller;

    public EventView(com.qpetit.controllers.CentralController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Tipo de Evento:"));
        txtTipo = new JTextField();
        form.add(txtTipo);

        form.add(new JLabel("Número de invitados:"));
        txtInvitados = new JTextField();
        form.add(txtInvitados);

        form.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        form.add(txtFecha);

        form.add(new JLabel("Lugar:"));
        txtLugar = new JTextField();
        form.add(txtLugar);

        JPanel buttons = new JPanel();
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarEvento());
        buttons.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarEvento());
        buttons.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarEvento());
        buttons.add(btnEliminar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarEventos());
        buttons.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    // Permite que el controlador cargue eventos en la tabla
    public void setEvents(List<Event> events) {
        String[] columns = {"ID", "Inicio", "Fin", "Invitados", "TipoEvento", "Cliente", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        if (events != null) {
            for (Event e : events) {
                model.addRow(new Object[]{
                        e.getEventId(),
                        e.getStartDate(),
                        e.getEndDate(),
                        e.getGuestCount(),
                        e.getEventTypeId(),
                        e.getCustomerId(),
                        e.getStatusId()
                });
            }
        }
        table.setModel(model);
    }

    private void agregarEvento() {
        try {
            // Crear un evento básico con los datos del formulario
            // Por simplicidad, usamos valores por defecto para algunos campos
            int guestCount = Integer.parseInt(txtInvitados.getText());
            int eventTypeId = Integer.parseInt(txtTipo.getText());
            
            // Crear evento con valores por defecto para los campos requeridos
            Event evento = new Event(
                0, // ID se genera automáticamente
                java.time.LocalDateTime.now(), // Fecha inicio por defecto
                java.time.LocalDateTime.now().plusHours(3), // Fecha fin por defecto
                guestCount,
                eventTypeId,
                1, // Menu type por defecto
                1, // Supplier ID por defecto
                "", // Service sheet link vacío
                "12345678", // Customer ID por defecto
                1, // Menu ID por defecto
                1  // Status ID por defecto
            );
            
            controller.addEvent(evento);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Evento agregado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar evento: " + ex.getMessage());
        }
    }

    private void editarEvento() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un evento para editar");
            return;
        }
        
        try {
            int eventId = (Integer) table.getValueAt(selectedRow, 0);
            int guestCount = Integer.parseInt(txtInvitados.getText());
            int eventTypeId = Integer.parseInt(txtTipo.getText());
            
            Event evento = new Event(
                eventId,
                java.time.LocalDateTime.now(),
                java.time.LocalDateTime.now().plusHours(3),
                guestCount,
                eventTypeId,
                1, 1, "", "12345678", 1, 1
            );
            
            controller.editEvent(evento);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Evento editado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al editar evento: " + ex.getMessage());
        }
    }

    private void eliminarEvento() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un evento para eliminar");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este evento?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int eventId = (Integer) table.getValueAt(selectedRow, 0);
                controller.deleteEvent(eventId);
                JOptionPane.showMessageDialog(this, "Evento eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar evento: " + ex.getMessage());
            }
        }
    }

    private void listarEventos() {
        try {
            List<Event> events = controller.getAllEvents();
            if (events == null || events.isEmpty()) {
                // Si no hay eventos en la base, mostrar datos de ejemplo
                String[] columns = {"ID", "Inicio", "Fin", "Invitados", "TipoEvento", "Cliente", "Estado"};
                javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columns, 0);
                model.addRow(new Object[]{1, "2025-10-25 18:00", "2025-10-25 23:00", 50, "Matrimonio", "12345678", "Confirmado"});
                model.addRow(new Object[]{2, "2025-10-30 15:00", "2025-10-30 20:00", 25, "Cumpleaños", "87654321", "Pendiente"});
                model.addRow(new Object[]{3, "2025-11-05 12:00", "2025-11-05 16:00", 80, "Corporativo", "11223344", "Confirmado"});
                table.setModel(model);
                JOptionPane.showMessageDialog(this, "Lista de eventos de ejemplo cargada (no hay datos en BD)");
            } else {
                setEvents(events);
                JOptionPane.showMessageDialog(this, "Lista de eventos actualizada desde BD");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar eventos: " + ex.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtTipo.setText("");
        txtInvitados.setText("");
        txtFecha.setText("");
        txtLugar.setText("");
    }
}