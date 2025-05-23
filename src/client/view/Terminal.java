package client.view;

import client.controller.Controller;
import shared.IsAdminStatus;
import shared.Login;
import shared.Registration;
import shared.UserInformation;

import java.util.Scanner;

public class Terminal {
    Scanner scanner = new Scanner(System.in);
    
    private Controller controller;
    
    public Terminal ( ) {
    
    }
    
    public UserInformation startupMenu ( ) {
        System.out.println("-----------------------------------------------");
        System.out.println("== Welcome to the LocalZero Server! ==");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("0. Logout");
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
            
            case 0:
                System.out.println("See you next time!");
                System.exit(0);
                return null;
        }
        
        return null;
    }
    
    
    public void showSustainabilityMenu ( ) {
        System.out.println("-----------------------------------------------");
        System.out.println("1. Biking");
        System.out.println("2. Composting");
        System.out.println("3. Public Transport");
        System.out.println("4. Planting Trees");
        System.out.println("0. Back");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
        
        int choice = scanner.nextInt();
        //scanner.nextInt(); //newline char bullshit
        
        switch (choice){
            case 1:
                System.out.println("How far did you bike? (km)");
                int kilometers = scanner.nextInt();
                scanner.nextInt(); //newline char bullshit
                controller.goneBiking(kilometers);
                System.out.println("Thanks for biking!");
                break;
            case 2:
                System.out.println("How much food waste did you compost? (kg)");
                int foodwaste = scanner.nextInt();
                scanner.nextInt(); //newline char bullshit
                controller.composting(foodwaste);
                System.out.println("Thanks for composting!");
                break;
            case 3:
                System.out.println("How far did you go? (km)");
                int km = scanner.nextInt();
                scanner.nextInt();
                controller.usedPublicTransport(km);
                System.out.println("Thanks for choosing public transport!");
                break;
            case 0:
                showMenu();
                break;
        }
        showMenu();
    }
    
    public void showMenu ( ) {
        System.out.println("-----------------------------------------------");
        System.out.println("1. Log Sustainability action");
        System.out.println("2. View forum");
        System.out.println("3. Check neighbourhood achievements");
        
        if (controller.getUser().getIsAdmin()){
            System.out.println("4. Set user roles");
            System.out.println("5. Set user admin status");
        }
        
        System.out.println("0. Logout");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); //scanner newline char bullshit
        
        switch (choice){
            case 1:
                showSustainabilityMenu();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                if (controller.getUser().getIsAdmin()){
                
                }
                break;
            case 5:
                if (controller.getUser().getIsAdmin()){
                    alterAdminStatus();
                }
                break;
            case 0:
                System.exit(0);
                break;
        }
    }
    
    private void alterAdminStatus ( ) {
        IsAdminStatus admin = new IsAdminStatus();
        boolean isAdmin = false;
        
        System.out.println("Which user would you like to change: ");
        String username = scanner.nextLine();
        String adminStatus = "";
        
        do {
            System.out.println("Type 'True' update user to admin status");
            System.out.println("Type 'False' to remove admin status");
            adminStatus = scanner.nextLine();
        } while (!adminStatus.equals("True") && !adminStatus.equals("False"));
        
        if (adminStatus.equals("True")){
            isAdmin = true;
        }
        
        admin.setUsername(username);
        admin.setAdmin(isAdmin);
        controller.alterAdminStatus(admin);
        
        showMenu();
    }
    
    public void setController(Controller controller){
        this.controller = controller;
    }
}
