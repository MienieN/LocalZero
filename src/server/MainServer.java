package server;

import server.controller.UserLogInController;
import server.model.login.handlers.PasswordValidationHandler;
import server.model.login.handlers.UsernameValidationHandler;
import server.view.DatabaseConnection;
import shared.User;

import java.sql.SQLException;

public class MainServer {

    public static void main(String[] args) {
        
        
        UserLogInController userLogInController = new UserLogInController();
        DatabaseConnection dbConnection = new DatabaseConnection();
        UsernameValidationHandler usernameValidationHandler = new UsernameValidationHandler();
        PasswordValidationHandler passwordValidationHandler = new PasswordValidationHandler();
        
        userLogInController.setDatabaseConnection(dbConnection)
                .setValidateUsername(usernameValidationHandler)
                        .setValidatePassword(passwordValidationHandler);
        
        try {
            User user = userLogInController.loginUser("Abba", "Abba");
            System.out.println(user.getLocation());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
