package views;

import javax.swing.*;
import java.awt.*;

public class FinanceView extends JPanel {
    private JTable table;
    private JTextField txtFecha, txtMonto, txtTipo;

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
