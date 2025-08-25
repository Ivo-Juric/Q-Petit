package views;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtPrecio, txtTipo;

    public MenuView() {
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("Nombre del Menú:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Tipo (vegano, celiaco, etc.):"));
        txtTipo = new JTextField();
        form.add(txtTipo);

        form.add(new JLabel("Precio base por persona:"));
        txtPrecio = new JTextField();
        form.add(txtPrecio);

        JPanel buttons = new JPanel();
        buttons.add(new JButton("Agregar"));
        buttons.add(new JButton("Editar"));
        buttons.add(new JButton("Eliminar"));
        buttons.add(new JButton("Listar"));

        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        // Tabla
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }
}