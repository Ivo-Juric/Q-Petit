package entitys;

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
    
}
