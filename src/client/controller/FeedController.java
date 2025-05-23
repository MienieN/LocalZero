package client.controller;

import shared.ActionInitiativeStorage;
import java.util.List;

public class FeedController {
    private ActionInitiativeStorage storage;
    
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
}