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
        System.out.println("7. Give a like");
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
            case 6:
                sendCommentMenu();
                break;
            case 7:
                giveLikeMenu();
                break;
            default:
                break;
        }
        showMenu();
    }

    //TODO: Might need to handle scanner differently
    private void sendDirectMessageMenu() {
        System.out.println("Which user do you want to send a message to?");
        ArrayList<String> recipients = getRecipients();

        System.out.println("Thank you. Please type your message!");
        String message = scanner.nextLine();

        controller.createCommunityMessage(recipients, message, MessageType.DIRECT_MESSAGE, null);
    }

    private void sendCommentMenu() {
        try {
            System.out.println("Which post do you want to comment on?");

            String[] postInfo = getRecipientAndOriginalPost();
            if (postInfo == null) {
                showMenu();
                return;
            }

            ArrayList<String> recipients = new ArrayList<>();
            recipients.add(postInfo[0]);
            String originalPostTitle = postInfo[1];

            System.out.println("Thank you. Please type your comment!");
            String message = scanner.nextLine();

            controller.createCommunityMessage(recipients, message, MessageType.COMMENT, originalPostTitle);
        } catch (Exception e) {
            System.out.println("Error processing comment: " + e.getMessage());
            scanner.nextLine(); // Clear any remaining input
            showMenu();
        }
    }

    private String[] getRecipientAndOriginalPost() {
        try {
            ActionInitiativeStorage storage = feedController.getStorage();
            List<ActionAbstract> actionList = storage.getLatestActions(10);
            List<IInitiative> initiativeList = storage.getLatestInitiatives(10);

            if (actionList == null || initiativeList == null) {
                System.out.println("Could not load posts.");
                return null;
            }

            int[] arrayAndIndex = getOriginalPostArrayAndIndex(actionList, initiativeList);
            if (arrayAndIndex == null) {
                return null;
            }

            int chosenArray = arrayAndIndex[0];
            int index = arrayAndIndex[1];

            String originalPostTitle;
            String recipient;
            if (chosenArray == 1) {
                if (index < 0 || index >= actionList.size()) {
                    System.out.println("Invalid action selection.");
                    return null;
                }
                originalPostTitle = actionList.get(index).toString();
                recipient = actionList.get(index).getUsername();
            } else {
                if (index < 0 || index >= initiativeList.size()) {
                    System.out.println("Invalid initiative selection.");
                    return null;
                }
                originalPostTitle = initiativeList.get(index).toString();
                recipient = initiativeList.get(index).getUsername();
            }

            return new String[]{recipient, originalPostTitle};
        } catch (Exception e) {
            System.out.println("Error getting post information: " + e.getMessage());
            return null;
        }
    }

    private int[] getOriginalPostArrayAndIndex(List<ActionAbstract> actionList, List<IInitiative> initiativeList) {
        try {
            int counter = 0;
            for (int i = 0; i < actionList.size(); i++) {
                System.out.println((i+1) + ". " + actionList.get(i).toString());
                counter++;
            }
            for (int j = 0; j < initiativeList.size(); j++) {
                System.out.println((j+counter+1) + ". " + initiativeList.get(j).toString());
            }

            System.out.println("Enter the number of the post (0 to cancel):");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                return null;
            }

            int totalItems = actionList.size() + initiativeList.size();
            if (choice < 1 || choice > totalItems) {
                System.out.println("Invalid selection. Please choose a number between 1 and " + totalItems);
                return null;
            }

            int[] arrayAndIndex = new int[2];
            if (choice > counter) {
                arrayAndIndex[0] = 2; // initiatives
                arrayAndIndex[1] = choice - counter - 1;
            } else {
                arrayAndIndex[0] = 1; // actions
                arrayAndIndex[1] = choice - 1;
            }
            return arrayAndIndex;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            return null;
        }
    }

    private void giveLikeMenu() {
        System.out.println("Which post do you want to like?");

        String[] postInfo = getRecipientAndOriginalPost();

        ArrayList<String> recipients = new ArrayList<>();
        recipients.add(postInfo[0]);
        String originalPostTitle = postInfo[1];

        System.out.println("Thank you for the like!");

        controller.createCommunityMessage(recipients, null, MessageType.LIKE, originalPostTitle);
    }

    private ArrayList<String> getRecipients() {
        System.out.println("Warning: case sensitive!");
        ArrayList<String> recipients = new ArrayList<>();
        String recipient;
        
            recipient = scanner.nextLine();
            recipients.add(recipient);
       
        return recipients;
    }
}
