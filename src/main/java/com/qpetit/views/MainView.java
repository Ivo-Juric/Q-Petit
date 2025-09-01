package com.qpetit.views;

import javax.swing.*;

// ---------- VISTA PRINCIPAL ----------
public class MainView extends JFrame {
    public MainView() {
        setTitle("Sistema de Gestión de Eventos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Contenedor de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Menús", new MenuView());
        tabbedPane.addTab("Staff", new StaffView());
        tabbedPane.addTab("Chefs", new ChefView());
        tabbedPane.addTab("Eventos", new EventView());
        tabbedPane.addTab("Locaciones", new LocationView());
        tabbedPane.addTab("Finanzas", new FinanceView());

        add(tabbedPane);
    }
}