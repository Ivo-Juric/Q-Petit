package com.qpetit.views;

import javax.swing.*;
import java.awt.*;

public class LocationView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtDistancia, txtLogistica;

    public LocationView() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("Nombre de la locación:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Distancia (km):"));
        txtDistancia = new JTextField();
        form.add(txtDistancia);

        form.add(new JLabel("Necesidades logísticas:"));
        txtLogistica = new JTextField();
        form.add(txtLogistica);

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

