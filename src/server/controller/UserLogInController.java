package server.controller;

import server.model.login.ValidatePassword;
import server.model.login.ValidateUsername;
import server.view.DatabaseConnection;
import shared.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogInController {
    
    private ValidateUsername validateUsername;
    private ValidatePassword validatePassword;
    private DatabaseConnection databaseConnection;
    
 
    public UserLogInController() {
    
    }
    
    // TODO no nested loops
    public User loginUser(String username, String password) throws SQLException {
        if (validateUsername.validate(username)) {
            if (validatePassword.validate(password)) {
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
    public UserLogInController setValidateUsername (ValidateUsername validateUsername) {
        this.validateUsername = validateUsername;
        return this;
    }
    
    public UserLogInController setValidatePassword (ValidatePassword validatePassword) {
        this.validatePassword = validatePassword;
        return this;
    }
    
    public UserLogInController setDatabaseConnection (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        return this;
    }
    
}
