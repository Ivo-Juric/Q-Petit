package com.qpetit.controllers;

public class CentralController implements MoneyAdministration, LoginInterface {

    public CentralController() {
        ClientController clientController = new ClientController();
        EmployeeController employeeController = new EmployeeController();
        EventController eventController = new EventController();
        MenuController menuController = new MenuController();

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
