package com.qpetit.views;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MenuView extends JPanel {
    private JTable table;
    private JTextField txtNombre, txtPrecio, txtTipo;

    public MenuView() {
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
        buttons.add(new JButton("Agregar"));
        buttons.add(new JButton("Editar"));
        buttons.add(new JButton("Eliminar"));
        buttons.add(new JButton("Listar"));

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
}
