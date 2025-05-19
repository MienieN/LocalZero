package src.server.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    }

    public void connect() {
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/myDb", "ao7503", databasePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
