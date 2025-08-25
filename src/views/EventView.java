package views;

import javax.swing.*;
import java.awt.*;

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
}