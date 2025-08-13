import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int idEvent;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private EventTypes eventTypes; // COMPLETAR
    private int numberOfGuests;
    private Location location;
    private Menu assignedMenu;
    private List<Employee> employeesList = new ArrayList<>();
    private EventState eventState;
    private String servicePaper;

    public Event(List<Employee> employeesList, String servicePaper, EventState eventState, Menu assignedMenu,
                 int idEvent, LocalDate eventStartDate, LocalDate eventEndDate,
                 EventTypes eventTypes, int numberOfGuests, Location location) {
        this.employeesList = employeesList;
        this.servicePaper = servicePaper;
        this.eventState = eventState;
        this.assignedMenu = assignedMenu;
        this.idEvent = idEvent;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventTypes = eventTypes;
        this.numberOfGuests = numberOfGuests;
        this.location = location;
    }
}

