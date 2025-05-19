package src.server.controller;

import java.io.Serializable;

public class AuthenticateUser implements Serializable {
    
    private static final long  serialVersionUID = 1L;
    private String username;
    private String password;
    private String email;
    private String location;
    
    public void login(String username, String email, String location, String password) {
    
    
    }
    
    // TODO fix this
    public void AuthenticateLogInViaDatabase (){
        CREATE LOGIN NewAdminName WITH PASSWORD = 'ABCD'
        GO
        
        Use BEPAWI;
        GO
        
        IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'NewAdminName')
        BEGIN
        CREATE USER [NewAdminName] FOR LOGIN [NewAdminName]
        EXEC sp_addrolemember N'db_owner', N'NewAdminName'
        END;
    }
    
}
