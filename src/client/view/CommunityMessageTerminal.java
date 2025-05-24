package client.view;

import client.controller.Controller;
import client.controller.FeedController;
import shared.MessageType;

import java.util.Scanner;

public class CommunityMessageTerminal {
    private Scanner scanner;
    private Controller controller;
    private FeedController feedController;

    public CommunityMessageTerminal(Controller controller, FeedController feedController) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
        this.feedController = feedController;
    }

    public void showMenu() {
        System.out.println("1. Show inbox");
        System.out.println("2. Show comments");
        System.out.println("3. Show likes");
        System.out.println("4. Show new initiatives");
        System.out.println("5. Send direct message");
        System.out.println("6. Send comment");
        System.out.println("0. Go back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice != 0) {
            makeChoice(choice);
        }
    }

    public void makeChoice(int choice) {
        switch (choice) {
            case 1:
                feedController.viewCommunityMessages(MessageType.DIRECT_MESSAGE);
                break;
            case 2:
                feedController.viewCommunityMessages(MessageType.COMMENT);
                break;
            case 3:
                feedController.viewCommunityMessages(MessageType.LIKE);
                break;
            case 4:
                feedController.viewCommunityMessages(MessageType.NEW_INITIATIVE);
                break;
            case 5:
                sendMessageMenu();
                break;
            default:
                break;
        }
        showMenu();
    }

    //TODO: Might need to handle scanner differently
    private void sendMessageMenu() {
        System.out.println("Which user do you want to send a message to?");
        String userName = scanner.nextLine();
        System.out.println("Type your message!");
        String message = scanner.nextLine();

        //controller.createCommunityMessage(userName, message);
    }
}
