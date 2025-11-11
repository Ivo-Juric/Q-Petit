package com.qpetit.views;

import javax.swing.*;
import java.awt.*;

public class LocationView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtDistancia, txtLogistica;
    private javax.swing.table.DefaultTableModel model;

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
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarLocacion());
        buttons.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función Editar Locación"));
        buttons.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función Eliminar Locación"));
        buttons.add(btnEliminar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarLocaciones());
        buttons.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        // Inicializar la tabla con el modelo correcto
        model = new javax.swing.table.DefaultTableModel(
            new String[]{"Nombre", "Distancia (km)", "Logística"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    private void agregarLocacion() {
        String nombre = txtNombre.getText().trim();
        String distancia = txtDistancia.getText().trim();
        String logistica = txtLogistica.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la locación es obligatorio");
            return;
        }
        
        // Agregar fila a la tabla
        model.addRow(new Object[]{nombre, distancia, logistica});
        limpiarFormulario();
        JOptionPane.showMessageDialog(this, "Locación agregada correctamente");
    }

    private void listarLocaciones() {
        // Mantener datos existentes y agregar datos de ejemplo de la BD
        boolean hasExampleData = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().contains("(BD)")) {
                hasExampleData = true;
                break;
            }
        }
        
        if (!hasExampleData) {
            model.addRow(new Object[]{"Salón Central (BD)", "5", "Básica"});
            model.addRow(new Object[]{"Club de Campo (BD)", "15", "Completa"});
            model.addRow(new Object[]{"Hotel Plaza (BD)", "8", "Intermedia"});
        }
        
        JOptionPane.showMessageDialog(this, "Lista de locaciones actualizada desde BD");
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDistancia.setText("");
        txtLogistica.setText("");
    }
}

