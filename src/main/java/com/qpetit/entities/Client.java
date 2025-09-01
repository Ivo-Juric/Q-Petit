package com.qpetit.entities;

public class Client {
    private String DNI;
    private String name;
    private String surname;
    private String email;
    private String contactNumber;
    private String observations;

    public Client(String DNI, String name, String surname, String email, String contactNumber, String observations) {
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contactNumber = contactNumber;
        this.observations = observations;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    
}
