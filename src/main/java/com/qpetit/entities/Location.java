package com.qpetit.entities;

import java.util.List;

public class Location {
    private int idLocation;
    private String streetName;
    private int streetNumber;
    private String city;
    private String state;
    private String contactNumber;
    private String email;
    private double distanceFromHeadquarters;
    private double estimatedMinutesFromHeadquarters;
    private List<LogisticalElement> logisticalRequirements;

    public Location(int idLocation, String streetName, int streetNumber, String city, String state, String contactNumber,
                    String email, List<LogisticalElement> logisticalRequirements, double distanceFromHeadquarters, double estimatedMinutesFromHeadquarters) {
        this.idLocation = idLocation;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.contactNumber = contactNumber;
        this.email = email;
        this.logisticalRequirements = logisticalRequirements;
        this.distanceFromHeadquarters = distanceFromHeadquarters;
        this.estimatedMinutesFromHeadquarters = estimatedMinutesFromHeadquarters;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getDistanceFromHeadquarters() {
        return distanceFromHeadquarters;
    }

    public void setDistanceFromHeadquarters(double distanceFromHeadquarters) {
        this.distanceFromHeadquarters = distanceFromHeadquarters;
    }

    public double getEstimatedMinutesFromHeadquarters() {
        return estimatedMinutesFromHeadquarters;
    }

    public void setEstimatedMinutesFromHeadquarters(double estimatedMinutesFromHeadquarters) {
        this.estimatedMinutesFromHeadquarters = estimatedMinutesFromHeadquarters;
    }
    
    public List<LogisticalElement> getLogisticalRequirements() {
        return logisticalRequirements;
    }

    public void setLogisticalRequirements(List<LogisticalElement> logisticalRequirements) {
        this.logisticalRequirements = logisticalRequirements;
    }
}
