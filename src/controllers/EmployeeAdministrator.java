package controllers;

import entitys.Employee;

public interface EmployeeAdministrator {
    void createEmployee();
    void updateEmployee(Employee employee);
    void deleteEmployee();
    void getAvialableEmployees();
    void getAllEmployees();
}
