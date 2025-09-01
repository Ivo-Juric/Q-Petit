package com.qpetit.views;

import javax.swing.*;
import java.awt.*;

public class ChefView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtTarifa;

    public ChefView() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Nombre del Chef:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Tarifa:"));
        txtTarifa = new JTextField();
        form.add(txtTarifa);

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
