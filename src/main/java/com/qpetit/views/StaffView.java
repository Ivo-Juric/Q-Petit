package com.qpetit.views;

import javax.swing.*;
import java.awt.*;

public class StaffView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtApellido, txtDni, txtEspecialidad;

    public StaffView() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        form.add(txtApellido);

        form.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        form.add(txtDni);

        form.add(new JLabel("Especialidad:"));
        txtEspecialidad = new JTextField();
        form.add(txtEspecialidad);

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
