package shared;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// this class is responsible for all the user information.
public class User {
    private final String username;
    private final String password;
    private final String email;
    private final String location;
    private final ArrayList<String> roles;
    private final boolean isAdmin;
    
    public User(String username, String password, String email, String location, ArrayList<String> roles, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
        this.roles = roles;
        this.isAdmin = isAdmin;
    }
    
    public User (ResultSet resultSet) throws SQLException {
        this.username = resultSet.getString("username");
        this.password = resultSet.getString("password");
        this.email = resultSet.getString("email");
        this.location = resultSet.getString("location");
        this.roles = getRolesFromList(resultSet);
        this.isAdmin = resultSet.getBoolean("is_admin");
    }
    
    public ArrayList<String> getRolesFromList(ResultSet resultSet) throws SQLException {
        String testGettingRoles = resultSet.getString("roles");
        String[] rolesArray = testGettingRoles.split(",");
        
        ArrayList<String> roles = new ArrayList<>();
        for (String role : rolesArray) {
            roles.add(role);
        }
        return roles;
    }
    
    public String getUsername ( ) {
        return username;
    }
    
    public String getPassword ( ) {
        return password;
    }
    
    public String getEmail ( ) {
        return email;
    }
    
    public String getLocation ( ) {
        return location;
    }
    
    public boolean isAdmin ( ) {
        return isAdmin;
    }
    
    public ArrayList<String> getRoles ( ) {
        return roles;
    }
}
