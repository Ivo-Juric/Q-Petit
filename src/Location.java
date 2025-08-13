import java.*;
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
    private List<LogisticalElement> logisticalRequirments;

    public Location(int idLocation, String streetName, int streetNumber, String city, String state, String contactNumber,
                    String email, List<LogisticalElement> logisticalRequirments, double distanceFromHeadquarters, double estimatedMinutesFromHeadquarters) {
        this.idLocation = idLocation;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.contactNumber = contactNumber;
        this.email = email;
        this.logisticalRequirments = logisticalRequirments;
        this.distanceFromHeadquarters = distanceFromHeadquarters;
        this.estimatedMinutesFromHeadquarters = estimatedMinutesFromHeadquarters;
    }
}
