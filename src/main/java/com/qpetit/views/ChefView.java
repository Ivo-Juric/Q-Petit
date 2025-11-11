package com.qpetit.views;

import javax.swing.*;
import java.awt.*;

public class ChefView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtTarifa;
    private javax.swing.table.DefaultTableModel model;
    private com.qpetit.controllers.CentralController controller;

    public ChefView(com.qpetit.controllers.CentralController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Nombre del Chef:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Tarifa:"));
        txtTarifa = new JTextField();
        form.add(txtTarifa);

        JPanel buttons = new JPanel();
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarChef());
        buttons.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función Editar Chef"));
        buttons.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función Eliminar Chef"));
        buttons.add(btnEliminar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarChefs());
        buttons.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        // Inicializar la tabla con el modelo correcto
        model = new javax.swing.table.DefaultTableModel(
            new String[]{"Nombre", "Tarifa"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    private void agregarChef() {
        String nombre = txtNombre.getText().trim();
        String tarifa = txtTarifa.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del chef es obligatorio");
            return;
        }
        
        try {
            // Guardar en base de datos usando el controlador
            controller.addChef(nombre, tarifa);
            
            // También agregar a la tabla local para visualización inmediata
            model.addRow(new Object[]{nombre, tarifa});
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Chef agregado correctamente y guardado en BD");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar chef: " + ex.getMessage());
        }
    }

    private void listarChefs() {
        try {
            // Limpiar tabla y cargar datos reales de la base de datos
            model.setRowCount(0);
            
            java.util.List<java.util.Map<String, Object>> chefs = controller.getAllChefs();
            for (java.util.Map<String, Object> chef : chefs) {
                model.addRow(new Object[]{
                    chef.get("nombre"), 
                    chef.get("tarifa")
                });
            }
            
            JOptionPane.showMessageDialog(this, "Lista de chefs cargada desde BD (" + chefs.size() + " registros)");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar chefs: " + ex.getMessage());
            // En caso de error, mostrar datos de ejemplo
            model.setRowCount(0);
            model.addRow(new Object[]{"Chef Mario (Ejemplo)", "$100/hora"});
            model.addRow(new Object[]{"Chef Ana (Ejemplo)", "$120/hora"});
            model.addRow(new Object[]{"Chef Carlos (Ejemplo)", "$95/hora"});
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtTarifa.setText("");
    }

    // Método para que el controlador pueda cargar chefs en la tabla
    public void setChefs(java.util.List<java.util.Map<String, Object>> chefs) {
        model.setRowCount(0); // Limpiar tabla
        for (java.util.Map<String, Object> chef : chefs) {
            model.addRow(new Object[]{
                chef.get("nombre"), 
                chef.get("tarifa")
            });
        }
    }
}
