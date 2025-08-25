package entitys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int idEvent;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private EventType eventType; // COMPLETAR
    private int numberOfGuests;
    private Location location;
    private List <Service> services;
    private List<Employee> employeesList = new ArrayList<>();
    private EventState eventState;
    private String servicePaper;

    public Event(List<Employee> employeesList, String servicePaper, EventState eventState, List <Service> services ,
                 int idEvent, LocalDate eventStartDate, LocalDate eventEndDate,
                 EventType eventType, int numberOfGuests, Location location) {
        this.employeesList = employeesList;
        this.servicePaper = servicePaper;
        this.eventState = eventState;
        this.services = services;
        this.idEvent = idEvent;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventType = eventType;
        this.numberOfGuests = numberOfGuests;
        this.location = location;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public LocalDate getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDate eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public LocalDate getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDate eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    public String getServicePaper() {
        return servicePaper;
    }

    public void setServicePaper(String servicePaper) {
        this.servicePaper = servicePaper;
    }

    
}

