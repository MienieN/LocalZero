package server;


import server.controller.UserLogInController;
import server.model.login.handlers.PasswordValidation;
import server.model.login.handlers.RegisterNewUser;
import server.model.login.handlers.UsernameValidation;
import server.view.DatabaseConnection;

import java.util.Scanner;

public class LoginMainServer {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseConnection dbConnection = new DatabaseConnection();
        
        
        System.out.println("== Welcome to the LocalZero Server! ==");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        switch (choice) {
            case 1:
                handleLogin(scanner, dbConnection);
                break;
            case 2:
                handleRegistration(scanner, dbConnection);
                break;
            default:
                System.out.println("See you next time!");
                break;
        }
    }
    
    private static void handleLogin (Scanner scanner, DatabaseConnection dbConnection) {
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
    
    private static void handleRegistration (Scanner scanner, DatabaseConnection dbConnection) {
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
