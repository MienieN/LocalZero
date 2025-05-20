package server.view;

import server.model.login.classes.HashPassword;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Handles the connection to the PostgreSQL database and user authentication queries.
 */
public class DatabaseConnection extends HashPassword {
    String databasePassword; // The password used for the database connection, read from a file.
    Connection connection; // The active database connection.
    
    /**
     * Constructs a new {@code DatabaseConnection} and establishes a connection
     * using credentials from the {@code passwords.env} file.
     *
     * @throws RuntimeException if the password file is not found or cannot be read
     */
    public DatabaseConnection() {
        try {
            databasePassword = new BufferedReader(new FileReader("passwords.env")).readLine().split("=")[1];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connect();
    }
    
    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @throws RuntimeException if a database access error occurs
     */
    public void connect() {
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://pgserver.mau.se:5432/localzero", "ao7503", databasePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Retrieves a user from the database matching the provided username and password.
     *
     * @param username the username to search for
     * @param rawPassword the password to match
     * @return a {@link ResultSet} containing the user data if found, or {@code null} if not found or an error occurs
     */
    public ResultSet getUserForLogin (String username, String rawPassword) {
        try {
            //String hashedPassword = hashPassword(rawPassword); // TODO: fix hashing algorithm
            
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE username = ? " +
                            "AND password = ?");
            
            statement.setString(1, username); // validated username
            statement.setString(2,rawPassword); // validated password TODO: hashPassword
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            } else {
                System.out.println("User not found");
            }
            return resultSet;
        }
        catch (SQLException e) {
            return null;
        }
    }
    
    public String getDatabasePassword ( ) {
        return databasePassword;
    }
    
    public Connection getConnection ( ) {
        return connection;
    }
}
