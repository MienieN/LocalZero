package server;

import server.model.login.handlers.LoginHandler;
import server.model.login.handlers.RegistrationHandler;
import server.view.DatabaseConnection;

import java.util.Scanner;

public class LoginMainServer{
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
                LoginHandler.handleLogin(scanner, dbConnection);
                break;
            case 2:
                RegistrationHandler.handleRegistration(scanner, dbConnection);
                break;
            default:
                System.out.println("See you next time!");
                break;
        }
    }
    
}
