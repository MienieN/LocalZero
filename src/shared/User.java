package shared;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 Represents a user with authentication and profile information.
 <p>
 Stores username, password, email, location, roles, and admin status.
 </p>
 */
public class User implements Serializable {
    private final String username; // The user's unique username.
    private final String password;  // The user's password.
    private final String email; // The user's email address.
    private final String location; // The user's location.
    private final ArrayList <String> roles; // The list of roles assigned to the user.
    private final boolean isAdmin;  // Indicates if the user is an admin.
    
    /**
     Constructs a new {@code User} with the specified details.
     
     @param username the user's username
     @param password the user's hashed password
     @param email    the user's email address
     @param location the user's location
     @param roles    the user's roles
     @param isAdmin  whether the user is an admin
     */
    public User (String username, String password, String email, String location, ArrayList <String> roles, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
        this.roles = roles;
        this.isAdmin = isAdmin;
    }
    
    /**
     Constructs a new {@code User} from a database {@link ResultSet}.
     
     @param resultSet the result set containing user data
     
     @throws SQLException if a database access error occurs
     */
    public User (ResultSet resultSet) throws SQLException {
        this.username = resultSet.getString("username");
        this.password = resultSet.getString("password");
        this.email = resultSet.getString("email");
        this.location = resultSet.getString("location");
        this.roles = getRolesFromList(resultSet);
        this.isAdmin = resultSet.getBoolean("is_admin");
    }
    
    /**
     Extracts the user's roles from a comma-separated string in the {@link ResultSet}.
     
     @param resultSet the result set containing the roles string
     
     @return a list of roles
     
     @throws SQLException if a database access error occurs
     */
    public ArrayList <String> getRolesFromList (ResultSet resultSet) throws SQLException {
        String testGettingRoles = resultSet.getString("roles");
        String[] rolesArray = testGettingRoles.split(",");
        
        ArrayList <String> roles = new ArrayList <>();
        for (String role : rolesArray) {
            roles.add(role);
        }
        return roles;
    }
    
    // --- Getters ----------------------------------------------------------------------------------------------------
    
    /**
     @return the user's username
     */
    public String getUsername ( ) {
        return username;
    }
    
    /**
     @return the user's hashed password
     */
    public String getPassword ( ) {
        return password;
    }
    
    /**
     @return the user's email address
     */
    public String getEmail ( ) {
        return email;
    }
    
    /**
     @return the user's location
     */
    public String getLocation ( ) {
        return location;
    }
    
    /**
     @return {@code true} if the user is an admin, {@code false} otherwise
     */
    public boolean isAdmin ( ) {
        return isAdmin;
    }
    
    /**
     @return the list of roles assigned to the user
     */
    public ArrayList <String> getRoles ( ) {
        return roles;
    }
}
