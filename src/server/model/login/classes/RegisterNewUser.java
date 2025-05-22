package server.model.login.classes;

import server.view.DatabaseConnection;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles user registration by hashing the password and inserting the new user into the database.
 * Extends {@link HashPassword} to securely hash passwords before storing them.
 */
public class RegisterNewUser extends HashPassword implements Serializable {
    private final DatabaseConnection databaseConnection;
    
    /**
     * Constructs a new {@code RegisterNewUser} handler with the specified database connection.
     *
     * @param databaseConnection the database connection to use for user registration
     */
    public RegisterNewUser (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    /**
     * Registers a new user in the database.
     *
     * @param username the username of the new user
     * @param password the raw password to be hashed and stored
     * @param email the user's email address
     * @param location the user's location
     * @return true if the user was registered successfully, false otherwise
     */
    public boolean registerUser (String username, String password, String email, String location) {
        try{
            //String hashedPassword = hashPassword(password); // TODO: hashPassword
            
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO users (username, password, email, location, roles, is_admin)" +
                            " VALUES (?, ?, ?, ?, ?, ?)");
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password); // TODO: hashPassword
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, "user"); // default role
            preparedStatement.setBoolean(6, false); // default is_admin
            
            return preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
        
    }
}