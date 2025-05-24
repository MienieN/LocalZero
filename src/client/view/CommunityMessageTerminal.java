package client.view;

import client.controller.Controller;
import client.controller.FeedController;
import shared.ActionAbstract;
import shared.ActionInitiativeStorage;
import shared.IInitiative;
import shared.MessageType;

import java.util.ArrayList;
import java.util.List;
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
                sendDirectMessageMenu();
                break;
            default:
                break;
        }
        showMenu();
    }

    //TODO: Might need to handle scanner differently
    private void sendDirectMessageMenu() {
        System.out.println("Which users do you want to send a message to?");
        ArrayList<String> recipients = getRecipients();

        System.out.println("Thank you. Please type your message!");
        String message = scanner.nextLine();

        controller.createCommunityMessage(recipients, message, MessageType.DIRECT_MESSAGE, null);
    }

    private ArrayList<String> getRecipients() {
        System.out.println("Press enter after every name, enter 0 when done.\n" +
                "Warning: case sensitive!");
        ArrayList<String> recipients = new ArrayList<>();
        String recipient;
        do {
            recipient = scanner.nextLine();
            recipients.add(recipient);
        } while (!recipient.equals("0"));
        return recipients;
    }

    private void sendCommentMenu() {
        System.out.println("Which post do you want to comment on?");

        ActionInitiativeStorage storage = feedController.getStorage();
        List<ActionAbstract> actionList = storage.getLatestActions(10);
        List<IInitiative> initiativeList = storage.getLatestInitiatives(10);

        int[] originalPostArrayAndIndex = getOriginalPostArrayAndIndex(actionList, initiativeList);
        int chosenArray = originalPostArrayAndIndex[0];
        int index = originalPostArrayAndIndex[1];

        String originalPostTitle;
        String recipient;
        if (chosenArray == 1) {
            originalPostTitle = actionList.get(index).toString();
            recipient = actionList.get(index).getUsername();
        } else {
            originalPostTitle = initiativeList.get(index).toString();
            recipient = initiativeList.get(index).getUsername();
        }

        ArrayList<String> recipients = new ArrayList<>();
        recipients.add(recipient);

        System.out.println("Thank you. Please type your comment!");
        String message = scanner.nextLine();

        controller.createCommunityMessage(recipients, message, MessageType.COMMENT, originalPostTitle);
    }

    private String[] getRecipientAndOriginalPost() {

    }

    private int[] getOriginalPostArrayAndIndex(List<ActionAbstract> actionList, List<IInitiative> initiativeList) {
        int counter = 0; //for determining offset in next list
        for (int i = 0; i < actionList.size(); i++) {
            System.out.println(i+1 + actionList.get(i).toString());
            counter++;
        }
        for (int j = 0; j < initiativeList.size(); j++) {
            System.out.println(j+counter+1 + initiativeList.get(j).toString());
        }

        int[] arrayAndIndex = new int[2]; //to determine which array (initiative or action) and which index
        int index = scanner.nextInt();
        if (index > counter) {
            index = index-counter;
            arrayAndIndex[0] = 2; //array 2 - initiatives
        } else {
            arrayAndIndex[0] = 1; //array 1 - actions
        }
        arrayAndIndex[1] = index; //index in the chosen array
        return arrayAndIndex;
    }
}
