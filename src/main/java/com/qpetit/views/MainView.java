package com.qpetit.views;

import javax.swing.*;

import com.qpetit.controllers.CentralController;

import java.awt.*;

// ---------- VISTA PRINCIPAL ----------
public class MainView extends JFrame {
    private EventView eventView;
    private MenuView menuView;
    private StaffView staffView;
    private ChefView chefView;

    public MainView(CentralController controller) {
        setTitle("Sistema de Gestión de Eventos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Contenedor de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Guardamos la instancia de MenuView para poder rellenarla desde el controlador
        menuView = new MenuView(controller);
        tabbedPane.addTab("Menús", menuView);
        
        // Guardamos la instancia de StaffView para poder rellenarla desde el controlador
        staffView = new StaffView(controller);
        tabbedPane.addTab("Staff", staffView);
        
        // Guardamos la instancia de ChefView para poder rellenarla desde el controlador
        chefView = new ChefView(controller);
        tabbedPane.addTab("Chefs", chefView);

        // Guardamos la instancia de EventView para poder rellenarla desde el controlador
        eventView = new EventView(controller);
        tabbedPane.addTab("Eventos", eventView);

        tabbedPane.addTab("Locaciones", new LocationView());
        tabbedPane.addTab("Finanzas", new FinanceView());

        add(tabbedPane, BorderLayout.CENTER);
    }

    // Permite al controlador acceder al panel de eventos
    public EventView getEventView() {
        return eventView;
    }

    // Permite al controlador acceder al panel de menús
    public MenuView getMenuView() {
        return menuView;
    }

    // Permite al controlador acceder al panel de staff
    public StaffView getStaffView() {
        return staffView;
    }

    // Permite al controlador acceder al panel de chefs
    public ChefView getChefView() {
        return chefView;
    }
}