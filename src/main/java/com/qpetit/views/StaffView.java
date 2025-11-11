package com.qpetit.views;

import javax.swing.*;
import java.awt.*;
import com.qpetit.entities.Specialties;

public class StaffView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtApellido, txtDni, txtEspecialidad;
    private com.qpetit.controllers.CentralController controller;

    public StaffView(com.qpetit.controllers.CentralController controller) {
        this.controller = controller;
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
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarStaff());
        buttons.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarStaff());
        buttons.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarStaff());
        buttons.add(btnEliminar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarStaff());
        buttons.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    public void setStaff(java.util.List<com.qpetit.entities.Employee> staff) {
        String[] columns = {"ID", "Nombre", "Apellido", "Email", "Interno", "Disponibilidad"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columns, 0);
        if (staff != null) {
            for (com.qpetit.entities.Employee e : staff) {
                model.addRow(new Object[]{
                        e.getIdEmployee(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getEmail(),
                        e.getAvailability()
                });
            }
        }
        table.setModel(model);
    }

    private void agregarStaff() {
        try {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String dni = txtDni.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre, apellido y DNI son obligatorios");
                return;
            }
            
            // Crear empleado con valores por defecto
            com.qpetit.entities.Employee employee = new com.qpetit.entities.Employee(
                Integer.parseInt(dni), nombre, apellido, "email@ejemplo.com", Specialties.SERVER, 1
            );
            
            controller.addEmployee(employee);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Staff agregado correctamente");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "DNI debe ser un número válido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar staff: " + ex.getMessage());
        }
    }

    private void editarStaff() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para editar");
            return;
        }
        
        try {
            int dni = (Integer) table.getValueAt(selectedRow, 0);
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y apellido son obligatorios");
                return;
            }
            
            com.qpetit.entities.Employee employee = new com.qpetit.entities.Employee(
                dni, nombre, apellido, "email@ejemplo.com", Specialties.SERVER, 1
            );
            
            controller.editEmployee(employee);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Staff editado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al editar staff: " + ex.getMessage());
        }
    }

    private void eliminarStaff() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para eliminar");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este empleado?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int dni = (Integer) table.getValueAt(selectedRow, 0);
                controller.deleteEmployee(dni);
                JOptionPane.showMessageDialog(this, "Staff eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar staff: " + ex.getMessage());
            }
        }
    }

    private void listarStaff() {
        java.util.List<com.qpetit.entities.Employee> staff = controller.getAllEmployees();
        setStaff(staff);
        JOptionPane.showMessageDialog(this, "Lista de staff actualizada");
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtEspecialidad.setText("");
    }
}
