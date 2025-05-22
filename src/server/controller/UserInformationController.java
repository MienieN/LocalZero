package server.controller;

import server.view.DatabaseConnection;
import shared.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 Controller for handling user login logic.
 Validates username and password, then retrieves the user from the database.
 */
public class UserInformationController {
    private DatabaseConnection databaseConnection;
    
    /**
     Attempts to log in a user by validating credentials and querying the database.
     
     @param login a serializable object sent from the client containing username and password
     
     @return a {@link User} object if authentication is successful; {@code null} otherwise
     
     @throws SQLException if a database access error occurs
     */
    public User loginUser (Login login){
        String username = login.getUsername();
        String password = login.getPassword();
        
        // If the username and password are valid, query the database for the user
        ResultSet resultSet = getUserForLogin(username, password);
        if (resultSet != null) {
            try {
                return new User(resultSet);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    
    /**
     Retrieves a user from the database matching the provided username and password.
     
     @param username    the username to search for
     @param rawPassword the password to match
     
     @return a {@link ResultSet} containing the user data if found, or {@code null} if not found or an error occurs
     */
    public ResultSet getUserForLogin (String username, String rawPassword) {
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE username = ? " +
                            "AND password = ?");
            
            statement.setString(1, username); // validated username
            statement.setString(2, rawPassword); // validate password
            
            ResultSet resultSet = databaseConnection.sendQuery(statement);
            
            if (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
                return resultSet;
            }
            else {
                System.out.println("User not found");
                return null;
            }
            
        }
        catch (SQLException e) {
            return null;
        }
    }
    
    public IMessage registerUser (String username, String password, String email, String location, String role, boolean isAdmin) {
        IMessage message = new Message();
        
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO users " +
                            "Values (?, ?, ?, ?, ?, ?)");
            
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, location);
            statement.setString(5, role);
            statement.setBoolean(6, isAdmin);
            
            databaseConnection.sendUpdate(statement);
            
            message.setMessage("Registration Successful");
            message.setType(MessageType.SUCCESS_MESSAGE);
        }
        
        catch (SQLException e) {
            System.out.println("Username already exists"); // server side
            message.setMessage("Username already exists"); // client side
            message.setType(MessageType.ERROR_MESSAGE);
        }
        
        return message;
    }
    
    // --- Setters ----------------------------------------------------------------------------------------------------
    /**
     Sets the database connection.
     
     @param databaseConnection the {@link DatabaseConnection} to use
     
     @return this controller instance
     */
    public UserInformationController setDatabaseConnection (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        return this;
    }
    
}
