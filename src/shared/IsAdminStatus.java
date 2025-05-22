package shared;

import java.io.Serializable;

public class IsAdminStatus implements Serializable {
    private String username;
    private boolean isAdmin;
    
    public boolean isAdmin ( ) {
        return isAdmin;
    }
    
    public String getUsername ( ) {
        return username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
    
    public void setAdmin (boolean admin) {
        isAdmin = admin;
    }
}
