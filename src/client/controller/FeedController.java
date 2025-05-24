package client.controller;

import shared.*;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class FeedController {
    private ActionInitiativeStorage storage;
    private ArrayList<CommunityMessage> messages = new ArrayList<>();
    
    public FeedController(ActionInitiativeStorage storage) {
        this.storage = storage;
    }

    public void viewActionsFeed() {
        List actions = storage.getLatestActions(10);

        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i + 1) + ". " + actions.get(i).toString());
        }
    }

    public void viewInitiativesFeed() {
        List initiatives = storage.getLatestInitiatives(10);

        for (int i = 0; i < initiatives.size(); i++) {
            System.out.println((i + 1) + ". " + initiatives.get(i).toString());
        }
    }

    public void joinInitiative(String username) {
        List initiatives = storage.getLatestInitiatives(10);
        int randomNumber = RandomGenerator.getDefault().nextInt(initiatives.size());

        storage.joinInitiative(randomNumber, username);
    }

    public void likeAction() {
        List actions = storage.getLatestActions(10);
        int randomNumber = RandomGenerator.getDefault().nextInt(actions.size());

        storage.likeAction(randomNumber);
    }

    public void viewInitiativeDetails() {
        List actions = storage.getLatestActions(10);
        int randomNumber = RandomGenerator.getDefault().nextInt(actions.size());

        storage.viewInitiativeDetails(randomNumber);
    }

    /**
     * For viewing DMs, comments, likes, new initiatives
     **/
    public void viewCommunityMessages(MessageType type) {
        for (CommunityMessage m : messages) {
            if (m.getType() == type) {
                m.displayMessage();
            }
        }
    }

    public ArrayList<CommunityMessage> getMessages() {
        return messages;
    }

    public ActionInitiativeStorage getStorage() {
        return storage;
    }
}