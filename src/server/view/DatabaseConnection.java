package server.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 Handles the connection to the PostgreSQL database and user authentication queries.
 */
public class DatabaseConnection {
    private final String databasePassword; // The password used for the database connection, read from a file.
    private Connection connection; // The active database connection.
    private static DatabaseConnection instance = null;

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    /**
     Constructs a new {@code DatabaseConnection} and establishes a connection
     using credentials from the {@code passwords.env} file.
     
     @throws RuntimeException if the password file is not found or cannot be read
     */
    public DatabaseConnection ( ) {
        try {
            databasePassword = new BufferedReader(new FileReader("passwords.env")).readLine().split("=")[1];
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        connect();
    }
    
    /**
     Establishes a connection to the PostgreSQL database.
     
     @throws RuntimeException if a database access error occurs
     */
    public void connect ( ) {
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://pgserver.mau.se:5432/localzero", "ao7503", databasePassword);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ResultSet sendQuery (PreparedStatement query) {
        try {
            ResultSet resultSet = query.executeQuery();
            return resultSet;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void sendUpdate (PreparedStatement query) throws SQLException {
        query.execute();
    }
    
    public String getDatabasePassword ( ) {
        return databasePassword;
    }
    
    public Connection getConnection ( ) {
        return connection;
    }
}
