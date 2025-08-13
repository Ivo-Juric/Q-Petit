public class AdministrativeManager extends User{
        private final boolean isLimited = false;
    public AdministrativeManager(int userID, String username, String password, String email,
                                     String DNI, String name) {

            super(userID, username, password, email, DNI, name);
        }
}
