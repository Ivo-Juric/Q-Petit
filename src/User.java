public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean isLimited;

    public User(int userID, String firstName, String lastName, String username, String password, String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
