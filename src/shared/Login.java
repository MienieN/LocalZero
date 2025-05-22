package shared;

/**
 * User login info stored in a serializable object for sending to the server
 */

public class Login implements UserInformation{
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
