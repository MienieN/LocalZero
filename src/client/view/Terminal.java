package client.view;

import client.controller.Controller;
import shared.*;

import java.util.Scanner;

public class Terminal {
    Scanner scanner = new Scanner(System.in);
    
    private Controller controller;
    private ActionInitiativeTerminal actionInitiativeTerminal;
    private static Terminal instance;

    public static Terminal getInstance() {
        if (instance == null) {
            instance = new Terminal();
        }
        return instance;
    }
    
    public UserInformation startupMenu ( ) {
        System.out.println("-----------------------------------------------");
        System.out.println("== Welcome to the LocalZero Server! ==");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        String username, password;
        
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


    public void showMenu ( ) {
        System.out.println("-----------------------------------------------");
        System.out.println("1. Log Sustainability action");
        System.out.println("2. Create initiative");
        System.out.println("3. View feed");
        System.out.println("4. Check neighbourhood CO2 saved");


        if (controller.getUser().getIsAdmin()) {
            System.out.println("5. Set user roles");
            System.out.println("6. Set user admin status");
        }

        System.out.println("0. Logout");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");

        int choice = scanner.nextInt();
        scanner.nextLine(); //scanner newline char bullshit

        switch (choice) {
            case 1:
                actionInitiativeTerminal.showSustainabilityMenu();
                break;
            case 2:
                actionInitiativeTerminal.createInitiative();
                break;
            case 3:
                actionInitiativeTerminal.showFeedMenu();
                break;
            case 4:
                getNeighbourhoodCO2Stats();
                break;
            case 5:
                if (controller.getUser().getIsAdmin()) {
                    alterUserRoles();
                }
                else {
                    System.out.println("Invalid choice");
                    showMenu();
                }
                break;

            case 6:
                if (controller.getUser().getIsAdmin()) {
                    alterAdminStatus();
                }
                else {
                    System.out.println("Invalid choice");
                    showMenu();
                }
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    private void getNeighbourhoodCO2Stats () {
        CO2Status co2Status = new CO2Status();
        controller.viewCO2StatusForLocation(co2Status);
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
        } while (! adminStatus.equals("True") && ! adminStatus.equals("False"));
        
        if (adminStatus.equals("True")) {
            isAdmin = true;
        }
        
        admin.setUsername(username);
        admin.setAdmin(isAdmin);
        controller.alterAdminStatus(admin);
        
        showMenu();
    }
    
    private void alterUserRoles ( ) {
        RoleStatus status = new RoleStatus();
        
        System.out.println("Which user would you like to change: ");
        String username = scanner.nextLine();
        
        System.out.println("Type the role the user should have: ");
        String role = scanner.nextLine();
        
        status.setUsername(username);
        status.setRole(role);
        
        controller.alterUserRole(status);
        
        showMenu();
    }

    public void setController (Controller controller) {
        this.controller = controller;
        this.actionInitiativeTerminal = new ActionInitiativeTerminal(controller, this);
    }
}