package client.view;

import server.view.DatabaseConnection;
import shared.Login;
import shared.Registration;
import shared.User;
import shared.UserInformation;

import java.util.Scanner;

public class Terminal {
    Scanner scanner = new Scanner(System.in);
    
    public Terminal ( ) {
    
    }
    
    
    public UserInformation startupMenu ( ) {
        System.out.println("-----------------------------------------------");
        System.out.println("== Welcome to the LocalZero Server! ==");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
        
        //scanner = new Scanner(System.in);     //TODO keep this for safety sake, scanners be dumb
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        String username;
        String password;
        
        switch (choice) {
            case 1:
                System.out.println("Username: ");
                username = scanner.nextLine();
                System.out.println("Password: ");
                password = scanner.nextLine();
                
                return new Login(username, password); // creates a new login object
            
            case 2:
                System.out.println("Username: ");
                username = scanner.nextLine();
                System.out.println("Password: ");
                password = scanner.nextLine();
                System.out.println("Email: ");
                String email = scanner.nextLine();
                System.out.println("Location: ");
                String location = scanner.nextLine();
                
                return new Registration(username, password, email, location);
            
            default:
                System.out.println("See you next time!");
                return null; // TODO safely logout
        }
    }
    
}
