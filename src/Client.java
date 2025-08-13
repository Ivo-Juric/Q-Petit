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
}
