package client.view;

import client.controller.Controller;
import shared.InitiativeCategory;
import java.util.Scanner;

public class ActionInitiativeTerminal {
    private Controller controller;
    private Scanner scanner;
    private Terminal terminal;

    public ActionInitiativeTerminal(Controller controller, Terminal terminal) {
        this.controller = controller;
        this.terminal = terminal;
        this.scanner = new Scanner(System.in);
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

        switch (choice) {
            case 1:
                System.out.println("How far did you bike? (km)");
                int kilometers = scanner.nextInt();
                scanner.nextLine();
                controller.goneBiking(kilometers);
                System.out.println("Thanks for biking!");
                break;
            case 2:
                System.out.println("How much food waste did you compost? (kg)");
                int foodwaste = scanner.nextInt();
                scanner.nextLine();
                controller.composting(foodwaste);
                System.out.println("Thanks for composting!");
                break;
            case 3:
                System.out.println("How far did you go? (km)");
                int km = scanner.nextInt();
                scanner.nextLine();
                controller.usedPublicTransport(km);
                System.out.println("Thanks for choosing public transport!");
                break;
            case 4:
                System.out.println("How many trees did you plant?");
                int treesPlanted = scanner.nextInt();
                scanner.nextLine();
                controller.plantedTrees(treesPlanted);
                System.out.println("Thanks for planting trees!");
                break;
            case 0:
                break;
        }
        terminal.showMenu();
    }

    public void createInitiative ( ) {
        System.out.println("Creating a new Initiative. Information required.");
        System.out.println("Title: ");
        String title = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Location: ");
        String location = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Duration: ");
        String duration = scanner.nextLine();
        scanner.nextLine();

        InitiativeCategory category = showInitiativeCategoryMenu();
        boolean isPublic = showVisibilityMenu();

        controller.createInitiative(title, description, location, duration, category, isPublic);

        terminal.showMenu();
    }

    private InitiativeCategory showInitiativeCategoryMenu ( ) {
        InitiativeCategory.printNumberedOptions();
        int choice = scanner.nextInt();
        scanner.nextLine();

        return switch (choice) {
            case 1 -> InitiativeCategory.SOCIAL_GATHERING;
            case 2 -> InitiativeCategory.FITNESS;
            case 3 -> InitiativeCategory.SELLING;
            case 4 -> InitiativeCategory.SHARING;
            case 5 -> InitiativeCategory.VOLUNTEERING;
            default -> InitiativeCategory.OTHER;
        };
    }

    private boolean showVisibilityMenu() {
        System.out.println("Visibility:");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Public");
        System.out.println("2. Neighbourhood");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
        int visibilityChoice = scanner.nextInt();
        scanner.nextLine();

        return switch (visibilityChoice) {
            case 1 -> true;
            case 2 -> false;
            default -> false;
        };
    }

    public void showFeedMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("1. View latest actions");
        System.out.println("2. View latest initiatives");
        System.out.println("3. View inbox");
        System.out.println("4. View notifications");
        System.out.println("0. Back");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                controller.viewFeed("actions");
                showActionsMenu();
                break;
            case 2:
                controller.viewFeed("initiatives");
                showInitiativeMenu();
                break;
            default:
                break;
        }
        terminal.showMenu();
    }

    private void showActionsMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("1. Like a random action!" );
        System.out.println("0. Back");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                controller.likeAction();
                System.out.println("Thanks for the like!");
                break;
            default:
                showFeedMenu();
        }

        terminal.showMenu();
    }

    private void showInitiativeMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("1. Join a random initiative!" );
        System.out.println("2. View the details of a random initiative");
        System.out.println("0. Back");
        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                controller.joinInitiative();
                System.out.println("Thanks for joining!");
                break;
            case 2:
                controller.viewInitiativeDetails();
                break;
            default:
                showFeedMenu();
        }

        terminal.showMenu();
    }
}