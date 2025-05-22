package shared;

public class Registration implements UserInformation{
    private String username;
    private String password;
    private String email;
    private String location;
    private String role = "Resident";
    private boolean isAdmin = false;
    
    public Registration (String username, String password, String email, String location) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
    }
    
    @Override
    public String getUsername ( ) {
        return username;
    }
    
    @Override
    public String getPassword ( ) {
        return password;
    }
    
    public String getEmail ( ) {
        return email;
    }
    
    public String getLocation ( ) {
        return location;
    }
    
    public String getRole ( ) {
        return role;
    }
    
    public boolean isAdmin ( ) {
        return isAdmin;
    }
}
