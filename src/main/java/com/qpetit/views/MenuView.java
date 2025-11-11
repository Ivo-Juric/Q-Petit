package com.qpetit.views;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MenuView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtPrecio, txtTipo;
    private com.qpetit.controllers.CentralController controller;

    public MenuView(com.qpetit.controllers.CentralController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("Nombre del Menú:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Tipo (vegano, celiaco, etc.):"));
        txtTipo = new JTextField();
        form.add(txtTipo);

        form.add(new JLabel("Precio base por persona:"));
        txtPrecio = new JTextField();
        soloDecimal(txtPrecio); //  activar filtro de decimales y solo números
        form.add(txtPrecio);

        JPanel buttons = new JPanel();
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarMenu());
        buttons.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarMenu());
        buttons.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarMenu());
        buttons.add(btnEliminar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarMenus());
        buttons.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    private static void soloDecimal(JTextField field) {
        ((AbstractDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
            private boolean valido(String s) {
                if (s.isEmpty()) return true;                 // permite borrar todo el textfield y q no tengas q tener algo si o si
                s = s.replace(',', '.');                      // normalizar a punto
                return s.matches("\\d*(\\.\\d*)?");           // dígitos + 0/1 punto + más dígitos
            }
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String nuevo = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                        .insert(offset, string).toString();
                nuevo = nuevo.replace(',', '.');
                if (valido(nuevo)) super.insertString(fb, offset, string.replace(',', '.'), attr);
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String actual = fb.getDocument().getText(0, fb.getDocument().getLength());
                String nuevo = new StringBuilder(actual).replace(offset, offset + length, text == null ? "" : text).toString();
                nuevo = nuevo.replace(',', '.');
                if (valido(nuevo)) super.replace(fb, offset, length, text == null ? null : text.replace(',', '.'), attrs);
            }
        });
    }

    // Permite que el controlador cargue menús en la tabla
    public void setMenus(java.util.List<com.qpetit.entities.Menu> menus) {
        String[] columns = {"ID", "Nombre", "Categoría", "Precio", "Min Comensales", "Observaciones"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columns, 0);
        if (menus != null) {
            for (com.qpetit.entities.Menu m : menus) {
                model.addRow(new Object[]{
                        m.getIdMenu(),
                        m.getMenuName(),
                        m.getCategory() != null ? m.getCategory().toString() : "N/A",
                        com.qpetit.entities.Menu.getBasePricexPerson(),
                        m.getMinNumberDiners(),
                        m.getObservations()
                });
            }
        }
        table.setModel(model);
    }

    private void agregarMenu() {
        try {
            String nombre = txtNombre.getText().trim();
            String tipo = txtTipo.getText().trim();
            double precio = txtPrecio.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrecio.getText().trim());
            
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del menú es obligatorio");
                return;
            }
            
            // Crear menú con valores por defecto para campos requeridos
            com.qpetit.entities.Menu menu = new com.qpetit.entities.Menu(
                0, // ID se genera automáticamente
                nombre,
                null, // Category por defecto
                false, // isOwned por defecto
                null, // Supplier por defecto
                1, // minNumberDiners por defecto
                true, // includeEntree por defecto
                true, // includeMainDish por defecto
                true, // includeDessert por defecto
                tipo // observations (usamos el tipo como observación)
            );
            
            com.qpetit.entities.Menu.setBasePricexPerson(precio);
            
            controller.addMenu(menu);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Menú agregado correctamente");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar menú: " + ex.getMessage());
        }
    }

    private void editarMenu() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un menú para editar");
            return;
        }
        
        try {
            int menuId = (Integer) table.getValueAt(selectedRow, 0);
            String nombre = txtNombre.getText().trim();
            String tipo = txtTipo.getText().trim();
            double precio = txtPrecio.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrecio.getText().trim());
            
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del menú es obligatorio");
                return;
            }
            
            com.qpetit.entities.Menu menu = new com.qpetit.entities.Menu(
                menuId,
                nombre,
                null, false, null, 1, true, true, true, tipo
            );
            
            com.qpetit.entities.Menu.setBasePricexPerson(precio);
            
            controller.editMenu(menu);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Menú editado correctamente");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al editar menú: " + ex.getMessage());
        }
    }

    private void eliminarMenu() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un menú para eliminar");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este menú?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int menuId = (Integer) table.getValueAt(selectedRow, 0);
                controller.deleteMenu(menuId);
                JOptionPane.showMessageDialog(this, "Menú eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar menú: " + ex.getMessage());
            }
        }
    }

    private void listarMenus() {
        java.util.List<com.qpetit.entities.Menu> menus = controller.getAllMenus();
        setMenus(menus);
        JOptionPane.showMessageDialog(this, "Lista de menús actualizada");
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtTipo.setText("");
        txtPrecio.setText("");
    }
}
