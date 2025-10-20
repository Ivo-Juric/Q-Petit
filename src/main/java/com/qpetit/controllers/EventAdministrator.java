package com.qpetit.controllers;

import java.sql.SQLException;

public interface EventAdministrator {

    //void static createEvent() throws SQLException;
    void updateEvent();
    void deleteEvent();
    void assignEmployee();
    void assignMenu();
    void generateServicePaper();
}
