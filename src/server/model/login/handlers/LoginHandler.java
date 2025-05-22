package server.model.login.handlers;

import server.controller.UserLogInController;
import server.view.DatabaseConnection;

import java.util.Scanner;

// TODO: Implement a "no user found" message and "invalid password" message
//  Implement a "user already exists" message in RegistrationHandler
//  Add functionality to go to the message board
public class LoginHandler {
    
    public static void handleLogin (Scanner scanner, DatabaseConnection dbConnection) {
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        
        UsernameValidation validateUsername = new UsernameValidation();
        PasswordValidation validatePassword = new PasswordValidation();
        UserLogInController userLogInController = new UserLogInController();
        
        // Chain --------------------------------------------------------------------------------------------
        userLogInController.setDatabaseConnection(dbConnection);
        userLogInController.setValidateUsername(validateUsername);
        userLogInController.setValidatePassword(validatePassword);
    }
}
