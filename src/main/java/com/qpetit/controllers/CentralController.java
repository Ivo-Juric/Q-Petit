package com.qpetit.controllers;

import com.qpetit.views.MainView;
import com.qpetit.views.EventView;
import com.qpetit.entities.Event;
import javax.swing.SwingUtilities;
import java.util.List;

public class CentralController implements MoneyAdministration, LoginInterface {

    private final ClientController clientController;
    private final EmployeeController employeeController;
    private final EventController eventController;
    private final MenuController menuController;
    private MainView mainView;

    public CentralController() {
        this.clientController = new ClientController();
        this.employeeController = new EmployeeController();
        this.eventController = new EventController();
        this.menuController = new MenuController();

        // Mostrar la UI en el EDT
        SwingUtilities.invokeLater(() -> {
            mainView = new MainView();
            // cargar eventos desde la base y pasar a la vista
            EventView evView = mainView.getEventView();
            List<Event> events = eventController.getAll();
            evView.setEvents(events);
            mainView.setVisible(true);
        });
    }

    @Override
    public void newTransaction() {

    }

    @Override
    public void getTransactions() {

    }

    @Override
    public void getBalance() {

    }

    @Override
    public void logIn(String username, String password) {

    }

    @Override
    public void logOut() {

    }

    @Override
    public void testData() {

    }
}
