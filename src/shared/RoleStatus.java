package shared;

import java.io.Serializable;

public class RoleStatus implements Serializable {
    
    String username;
    String role;
    
    public void setRole (String role) {
        this.role = role;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
    
    public String getUsername ( ) {
        return username;
    }
    
    public String getRole ( ) {
        return role;
    }
}
