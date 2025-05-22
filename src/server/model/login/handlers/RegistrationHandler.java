package server.model.login.handlers;

import server.model.login.classes.RegisterNewUser;
import server.view.DatabaseConnection;

import java.util.Scanner;

// TODO: check if user exists
//  Go to message board or whatever immediately after registration
//  Set default role to the given location
public class RegistrationHandler{
    
    public static void handleRegistration (Scanner scanner, DatabaseConnection dbConnection) {
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Location: ");
        String location = scanner.nextLine();
        
        RegisterNewUser registerNewUser = new RegisterNewUser(dbConnection);
        if(registerNewUser.registerUser(username, password, email, location)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }
}
