package com.qpetit.entities;

public class Employee {
    private int idEmployee;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isInteral;
    private Specialties specialties; // AGREGAR CUALES SON
    private int availability; // MODIFICAR EN DIAS Y HORARIOS


    public Employee (int idEmployee, String firstName, String lastName, String email, boolean isInteral, Specialties specialties, int availability) {
        this.idEmployee = idEmployee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isInteral = isInteral;
        this.specialties = specialties;
        this.availability = availability;
    }


    public int getIdEmployee() {
        return idEmployee;
    }


    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isInteral() {
        return isInteral;
    }


    public void setInteral(boolean isInteral) {
        this.isInteral = isInteral;
    }


    public Specialties getSpecialties() {
        return specialties;
    }


    public void setSpecialties(Specialties specialties) {
        this.specialties = specialties;
    }


    public int getAvailability() {
        return availability;
    }


    public void setAvailability(int availability) {
        this.availability = availability;
    }

    

}
