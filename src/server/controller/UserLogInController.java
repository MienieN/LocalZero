package server.controller;

import server.model.login.handlers.PasswordValidation;
import server.model.login.handlers.UsernameValidation;
import server.view.DatabaseConnection;
import shared.Login;
import shared.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 Controller for handling user login logic.
 Validates username and password, then retrieves the user from the database.
 */
public class UserLogInController extends DatabaseConnection {
    private UsernameValidation usernameCheck;
    private PasswordValidation passwordCheck;
    private DatabaseConnection databaseConnection;
    
    /**
     Constructs a new {@code UserLogInController}.
     */
    public UserLogInController ( ) {
    
    }
    
    /**
     Attempts to log in a user by validating credentials and querying the database.
     
     @param login a serializable object sent from the client containing username and password
     
     @return a {@link User} object if authentication is successful; {@code null} otherwise
     
     @throws SQLException if a database access error occurs
     */
    public User loginUser (Login login){
        String username = login.getUsername();
        String password = login.getPassword();

        if (! usernameCheck.validate(username)) {
            System.out.println("failed username");
            return null;
        }
        
        if (! passwordCheck.validate(password)) {
            System.out.println("failed password");
            return null;
        }
        
        // If the username and password are valid, query the database for the user
        ResultSet resultSet = getUserForLogin(username, password);
        try {
            return new User(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     Retrieves a user from the database matching the provided username and password.
     
     @param username    the username to search for
     @param rawPassword the password to match
     
     @return a {@link ResultSet} containing the user data if found, or {@code null} if not found or an error occurs
     */
   //this should porbably be moved into databaseconnection
    public ResultSet getUserForLogin (String username, String rawPassword) {
        try {
            //String hashedPassword = hashPassword(rawPassword); // TODO: fix hashing algorithm
            
            PreparedStatement statement = getConnection().prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE username = ? " +
                            "AND password = ?");
            
            statement.setString(1, username); // validated username
            statement.setString(2, rawPassword); // validated password TODO: hashPassword
            //this should porbably be moved into databaseconnection (sendQuery method) due to this not being a view class
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }
            else {
                System.out.println("User not found");
            }
            return resultSet;
        }
        catch (SQLException e) {
            return null;
        }
    }
    
    // --- Setters ----------------------------------------------------------------------------------------------------
    
    /**
     Sets the username validation handler.
     
     @param usernameCheck the {@link UsernameValidation} handler
     
     @return this controller instance
     */
    public UserLogInController setValidateUsername (UsernameValidation usernameCheck) {
        this.usernameCheck = usernameCheck;
        return this;
    }
    
    /**
     Sets the password validation handler.
     
     @param passwordCheck the {@link PasswordValidation} handler
     
     @return this controller instance
     */
    public UserLogInController setValidatePassword (PasswordValidation passwordCheck) {
        this.passwordCheck = passwordCheck;
        return this;
    }
    
    /**
     Sets the database connection.
     
     @param databaseConnection the {@link DatabaseConnection} to use
     
     @return this controller instance
     */
    public UserLogInController setDatabaseConnection (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        return this;
    }
    
}
