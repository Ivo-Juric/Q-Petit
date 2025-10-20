package com.qpetit.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.qpetit.entities.Event;
import javax.swing.table.DefaultTableModel;

public class EventView extends JPanel {
    private JTable table;
    private JTextField txtTipo, txtInvitados, txtFecha, txtLugar;

    public EventView() {
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
        buttons.add(new JButton("Agregar"));
        buttons.add(new JButton("Editar"));
        buttons.add(new JButton("Eliminar"));
        buttons.add(new JButton("Listar"));

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
}