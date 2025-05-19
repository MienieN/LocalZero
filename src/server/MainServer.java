package server;

import server.controller.UserLogInController;
import server.model.login.ValidatePassword;
import server.model.login.ValidateUsername;
import server.view.DatabaseConnection;
import shared.User;

import java.sql.SQLException;

public class MainServer {

    public static void main(String[] args) {
        UserLogInController userLogInController = new UserLogInController();
        DatabaseConnection dbConnection = new DatabaseConnection();
        ValidateUsername validateUsername = new ValidateUsername();
        ValidatePassword validatePassword = new ValidatePassword();
        
        userLogInController.setDatabaseConnection(dbConnection)
                .setValidateUsername(validateUsername)
                        .setValidatePassword(validatePassword);
        
        try {
            User user = userLogInController.loginUser("Abba", "Abba");
            System.out.println(user.getLocation());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
