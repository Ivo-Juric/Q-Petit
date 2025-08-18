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
}

