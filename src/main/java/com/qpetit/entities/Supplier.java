package com.qpetit.entities;

public class Supplier {
    private int idSupplier;
    private String name;
    private String contactNumber;

    public Supplier(int idSupplier, String name, String contactNumber) {
        this.idSupplier = idSupplier;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    
}
