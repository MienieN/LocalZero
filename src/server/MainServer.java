package server;


import server.controller.UserLogInController;
import server.model.login.handlers.PasswordValidation;
import server.model.login.handlers.UsernameValidation;
import server.view.DatabaseConnection;
import shared.User;

import java.sql.SQLException;

public class MainServer {

    public static void main(String[] args) {
        // Validate------------------------------------------------------------------------------------------
        UserLogInController userLogInController = new UserLogInController();
        DatabaseConnection dbConnection = new DatabaseConnection();
        UsernameValidation validateUsername = new UsernameValidation();
        PasswordValidation validatePassword = new PasswordValidation();
        
        // Chain --------------------------------------------------------------------------------------------
        userLogInController.setDatabaseConnection(dbConnection)
                .setValidateUsername(validateUsername)
                .setValidatePassword(validatePassword);
        //userLogInController.setValidateUsername(usernameValidationHandler);
        //userLogInController.setValidatePassword(passwordValidationHandler);
        
        try {
            User user = userLogInController.loginUser("Abba", "Abba");
            System.out.println(user.getLocation());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
