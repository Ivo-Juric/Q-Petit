package com.qpetit.controllers;

import com.qpetit.dao.EmployeeDAO;
import com.qpetit.entities.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements Administrator {

    private final EmployeeDAO employeeDAO;
    private List<Employee> employeeList;

    public EmployeeController(List<Employee> employees) {
        this.employeeList = (employees != null) ? employees : new ArrayList<>();
        this.employeeDAO = new EmployeeDAO();
    }

    public EmployeeController() {
        this.employeeList = new ArrayList<>();
        this.employeeDAO = new EmployeeDAO();
    }

    // ============================
    // CREATE
    // ============================
    @Override
    public void create(Object o) {
        try {
            if (o instanceof Employee) {
                Employee employee = (Employee) o;
                employeeDAO.insert(employee);
                employeeList.add(employee);
                System.out.println("Empleado agregado correctamente: ID -> " + employee.getIdEmployee());
            } else {
                System.err.println("Objeto inválido. Se esperaba un Employee.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    // ============================
    // UPDATE
    // ============================
    @Override
    public void update(Object o) {
        try {
            if (o instanceof Employee) {
                Employee employee = (Employee) o;
                employeeDAO.update(employee);
                System.out.println("Empleado actualizado correctamente: ID -> " + employee.getIdEmployee());
            } else {
                System.err.println("Objeto inválido. Se esperaba un Employee.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: ");
            e.printStackTrace();
        }
    }

    // ============================
    // DELETE
    // ============================
    @Override
    public void delete(Object o) {
        try {
            if (o instanceof Employee) {
                Employee employee = (Employee) o;
                employeeDAO.delete(employee.getIdEmployee());
                employeeList.removeIf(e -> e.getIdEmployee() == employee.getIdEmployee());
                System.out.println("Empleado eliminado correctamente: ID -> " + employee.getIdEmployee());
            } else if (o instanceof Integer) {
                int id = (Integer) o;
                employeeDAO.delete(id);
                employeeList.removeIf(e -> e.getIdEmployee() == id);
                System.out.println("Empleado eliminado correctamente: ID -> " + id);
            } else {
                System.err.println("Objeto inválido. Debe ser Employee o Integer (ID).");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: ");
            e.printStackTrace();
        }
    }

    // ============================
    // GET ALL
    // ============================
    public List<Employee> getAll() {
        try {
            employeeList = employeeDAO.getAll();
            return employeeList;
        } catch (SQLException e) {
            System.err.println("Error al obtener lista de empleados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ============================
    // GET AVAILABLE EMPLOYEES
    // ============================
    public List<Employee> getAvailableEmployees() {
        List<Employee> available = employeeDAO.getAvailableEmployees();
        System.out.println("Empleados disponibles obtenidos correctamente (" + available.size() + ")");
        return available;
    }
}
