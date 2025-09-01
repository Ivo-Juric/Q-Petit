package com.qpetit.entities;

public class Vehicle {
    private int idVehicle;
    private String patent;
    private int modelYear;
    private boolean available;
    private int loadCapacity;

    public Vehicle(int idVehicle, String patent, int modelYear, boolean available, int loadCapacity) {
        this.idVehicle = idVehicle;
        this.patent = patent;
        this.modelYear = modelYear;
        this.available = available;
        this.loadCapacity = loadCapacity;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
    
    
}
