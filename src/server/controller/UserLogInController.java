package server.controller;

import server.model.login.handlers.PasswordValidationHandler;
import server.model.login.handlers.UsernameValidationHandler;
import server.view.DatabaseConnection;
import shared.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogInController {
    
    private UsernameValidationHandler usernameValidationHandler;
    private PasswordValidationHandler passwordValidationHandler;
    private DatabaseConnection databaseConnection;
    
 
    public UserLogInController() {
    
    }
    
    // TODO no nested loops
    public User loginUser(String username, String password) throws SQLException {
        if (usernameValidationHandler.validate(username)) {
            if (passwordValidationHandler.validate(password)) {
                ResultSet resultSet = databaseConnection.getUserForLogin(username, password);
                return new User(resultSet);
                
            }
            else {
                System.out.println("failed password");
            }
        }
        else {
            System.out.println("failed username");
        }
        return null;
    }
    
    // --- Setters ----------------------------------------------------------------------------------------------------
    public UserLogInController setValidateUsername (UsernameValidationHandler usernameValidationHandler) {
        this.usernameValidationHandler = usernameValidationHandler;
        return this;
    }
    
    public UserLogInController setValidatePassword (PasswordValidationHandler passwordValidationHandler) {
        this.passwordValidationHandler = passwordValidationHandler;
        return this;
    }
    
    public UserLogInController setDatabaseConnection (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        return this;
    }
    
}
