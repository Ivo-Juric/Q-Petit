package com.qpetit.views;

import javax.swing.*;
import java.awt.*;

// ---------- VISTA PRINCIPAL ----------
public class MainView extends JFrame {
    private EventView eventView;

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

        // Guardamos la instancia de EventView para poder rellenarla desde el controlador
        eventView = new EventView();
        tabbedPane.addTab("Eventos", eventView);

        tabbedPane.addTab("Locaciones", new LocationView());
        tabbedPane.addTab("Finanzas", new FinanceView());

        add(tabbedPane, BorderLayout.CENTER);
    }

    // Permite al controlador acceder al panel de eventos
    public EventView getEventView() {
        return eventView;
    }
}