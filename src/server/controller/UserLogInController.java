package server.controller;

import server.model.login.handlers.PasswordValidation;
import server.model.login.handlers.UsernameValidation;
import server.view.DatabaseConnection;
import shared.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller for handling user login logic.
 * Validates username and password, then retrieves the user from the database.
 */
public class UserLogInController {
    private UsernameValidation usernameCheck;
    private PasswordValidation passwordCheck;
    private DatabaseConnection databaseConnection;
    
    /**
     * Constructs a new {@code UserLogInController}.
     */
    public UserLogInController ( ) {
    
    }
    
    /**
     * Attempts to log in a user by validating credentials and querying the database.
     *
     * @param username the username to validate and authenticate
     * @param password the password to validate and authenticate
     * @return a {@link User} object if authentication is successful; {@code null} otherwise
     * @throws SQLException if a database access error occurs
     */
    // TODO no nested loops
    public User loginUser (String username, String password) throws SQLException {
        if (! usernameCheck.validate(username)) {
            System.out.println("failed username");
            return null;
        }
        
        if (! passwordCheck.validate(password)) {
            System.out.println("failed password");
            return null;
        }
        
        // If the username and password are valid, query the database for the user
        ResultSet resultSet = databaseConnection.getUserForLogin(username, password);
        return new User(resultSet);
    }
    
    // --- Setters ----------------------------------------------------------------------------------------------------
    /**
     * Sets the username validation handler.
     *
     * @param usernameCheck the {@link UsernameValidation} handler
     * @return this controller instance
     */
    public UserLogInController setValidateUsername (UsernameValidation usernameCheck) {
        this.usernameCheck = usernameCheck;
        return this;
    }
    
    /**
     * Sets the password validation handler.
     *
     * @param passwordCheck the {@link PasswordValidation} handler
     * @return this controller instance
     */
    public UserLogInController setValidatePassword (PasswordValidation passwordCheck) {
        this.passwordCheck = passwordCheck;
        return this;
    }
    
    /**
     * Sets the database connection.
     *
     * @param databaseConnection the {@link DatabaseConnection} to use
     * @return this controller instance
     */
    public UserLogInController setDatabaseConnection (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        return this;
    }
    
}
