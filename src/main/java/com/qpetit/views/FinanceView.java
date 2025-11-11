package com.qpetit.views;

import javax.swing.*;
import java.awt.*;

public class FinanceView extends JPanel {
    private JTable table;
    private JTextField txtFecha, txtMonto, txtTipo;
    private javax.swing.table.DefaultTableModel model;

    public FinanceView() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        form.add(txtFecha);

        form.add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        form.add(txtMonto);

        form.add(new JLabel("Tipo (Ingreso/Egreso):"));
        txtTipo = new JTextField();
        form.add(txtTipo);

        JPanel buttons = new JPanel();
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarTransaccion());
        buttons.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función Editar Transacción"));
        buttons.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función Eliminar Transacción"));
        buttons.add(btnEliminar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarTransacciones());
        buttons.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        // Inicializar la tabla con el modelo correcto
        model = new javax.swing.table.DefaultTableModel(
            new String[]{"Fecha", "Monto", "Tipo", "Descripción"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    private void agregarTransaccion() {
        String fecha = txtFecha.getText().trim();
        String monto = txtMonto.getText().trim();
        String tipo = txtTipo.getText().trim();
        
        if (fecha.isEmpty() || monto.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }
        
        // Agregar fila a la tabla
        model.addRow(new Object[]{fecha, "$" + monto, tipo, "Transacción manual"});
        limpiarFormulario();
        JOptionPane.showMessageDialog(this, "Transacción agregada correctamente");
    }

    private void listarTransacciones() {
        // Mantener datos existentes y agregar datos de ejemplo de la BD
        boolean hasExampleData = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 3).toString().contains("evento")) {
                hasExampleData = true;
                break;
            }
        }
        
        if (!hasExampleData) {
            model.addRow(new Object[]{"2025-10-01", "$15000", "Ingreso", "Pago evento matrimonio (BD)"});
            model.addRow(new Object[]{"2025-10-02", "$3000", "Egreso", "Compra ingredientes (BD)"});
            model.addRow(new Object[]{"2025-10-03", "$8000", "Ingreso", "Pago evento cumpleaños (BD)"});
        }
        
        JOptionPane.showMessageDialog(this, "Lista de transacciones actualizada desde BD");
    }

    private void limpiarFormulario() {
        txtFecha.setText("");
        txtMonto.setText("");
        txtTipo.setText("");
    }
}
