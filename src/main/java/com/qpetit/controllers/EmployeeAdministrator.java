package com.qpetit.controllers;

import com.qpetit.entities.Employee;

public interface EmployeeAdministrator {
    void createEmployee();
    void updateEmployee(Employee employee);
    void deleteEmployee();
    void getAvialableEmployees();
    void getAllEmployees();
}
