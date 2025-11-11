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
    private final ChefController chefController;
    private MainView mainView;

    public CentralController() {
        this.clientController = new ClientController();
        this.employeeController = new EmployeeController();
        this.eventController = new EventController();
        this.menuController = new MenuController();
        this.chefController = new ChefController();

        // Mostrar la UI en el EDT
        SwingUtilities.invokeLater(() -> {
            mainView = new MainView(this); // Pasamos referencia del controlador
            // cargar eventos desde la base y pasar a la vista
            EventView evView = mainView.getEventView();
            List<Event> events = eventController.getAll();
            evView.setEvents(events);
            mainView.setVisible(true);
        });
    }

    // Métodos para manejar eventos
    public void addEvent(Event event) {
        eventController.create(event);
        refreshEventView();
    }

    public void editEvent(Event event) {
        eventController.update(event);
        refreshEventView();
    }

    public void deleteEvent(int eventId) {
        eventController.delete(eventId);
        refreshEventView();
    }

    public List<Event> getAllEvents() {
        return eventController.getAll();
    }

    private void refreshEventView() {
        if (mainView != null && mainView.getEventView() != null) {
            List<Event> events = eventController.getAll();
            mainView.getEventView().setEvents(events);
        }
    }

    // Métodos para manejar menús
    public void addMenu(com.qpetit.entities.Menu menu) {
        menuController.create(menu);
        refreshMenuView();
    }

    public void editMenu(com.qpetit.entities.Menu menu) {
        menuController.update(menu);
        refreshMenuView();
    }

    public void deleteMenu(int menuId) {
        menuController.delete(menuId);
        refreshMenuView();
    }

    public java.util.List<com.qpetit.entities.Menu> getAllMenus() {
        return menuController.getAll();
    }

    private void refreshMenuView() {
        if (mainView != null && mainView.getMenuView() != null) {
            java.util.List<com.qpetit.entities.Menu> menus = menuController.getAll();
            mainView.getMenuView().setMenus(menus);
        }
    }

    // Métodos para manejar empleados
    public void addEmployee(com.qpetit.entities.Employee employee) {
        employeeController.create(employee);
        refreshStaffView();
    }

    public void editEmployee(com.qpetit.entities.Employee employee) {
        employeeController.update(employee);
        refreshStaffView();
    }

    public void deleteEmployee(int employeeId) {
        employeeController.delete(employeeId);
        refreshStaffView();
    }

    public java.util.List<com.qpetit.entities.Employee> getAllEmployees() {
        return employeeController.getAll();
    }

    private void refreshStaffView() {
        if (mainView != null && mainView.getStaffView() != null) {
            java.util.List<com.qpetit.entities.Employee> employees = employeeController.getAll();
            mainView.getStaffView().setStaff(employees);
        }
    }

    // Métodos para manejar chefs
    public void addChef(String nombre, String tarifa) {
        try {
            chefController.create(nombre, tarifa);
            refreshChefView();
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar chef: " + ex.getMessage());
        }
    }

    public java.util.List<java.util.Map<String, Object>> getAllChefs() {
        try {
            return chefController.getAll();
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener chefs: " + ex.getMessage());
        }
    }

    public void deleteChef(String nombre) {
        try {
            chefController.delete(nombre);
            refreshChefView();
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar chef: " + ex.getMessage());
        }
    }

    private void refreshChefView() {
        if (mainView != null && mainView.getChefView() != null) {
            java.util.List<java.util.Map<String, Object>> chefs = getAllChefs();
            mainView.getChefView().setChefs(chefs);
        }
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
