package src.server.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseConnection {
    String databasePassword;
    Connection connection;
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

    public void connect() {
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://pgserver.mau.se:5432/localzero", "ao7503", databasePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ResultSet getUserForLogin (String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE username = ? " +
                            "AND password = ?");
            
            statement.setString(1, username); // validated username
            statement.setString(2, password); // validated password
            
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
}
