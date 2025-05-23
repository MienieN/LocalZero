package shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionInitiativeStorage {
    private List<ActionAbstract> actions = new ArrayList<>();
    private List<IInitiative> initiatives = new ArrayList<>();

    public void addAction(ActionAbstract action) {
        actions.add(action);
    }

    public void addInitiative(IInitiative initiative) {
        initiatives.add(initiative);
    }

    // Method to return the last X actions
    public List<ActionAbstract> getLatestActions(int maxCount) {
        return getLastN(actions, maxCount);
    }

    // Method to return the last X actions
    public List<IInitiative> getLatestInitiatives(int maxCount) {
        return getLastN(initiatives, maxCount);
    }

    public List<ActionAbstract> getAllActions() {
        return new ArrayList<>(actions);
    }

    public List<IInitiative> getAllInitiatives() {
        return new ArrayList<>(initiatives);
    }

    private <T> List<T> getLastN(List<T> list, int maxCount) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        int startIndex = Math.max(0, list.size() - maxCount);
        return new ArrayList<>(list.subList(startIndex, list.size()));
    }

    public void joinInitiative(int index, String name) {
        initiatives.get(index).addParticipant(name);
    }

    public void likeAction(int randomNumber) {
        actions.get(randomNumber).likeAction();
    }

    public void viewInitiativeDetails(int randomNumber) {
        IInitiative initiative = initiatives.get(randomNumber);
        System.out.println("Creator: " + initiative.getUsername());
        System.out.println("Title: " + initiative.getTitle());
        System.out.println("Description: " + initiative.getDescription());
        System.out.println("Location: " + initiative.getLocation());
        System.out.println("Duration: " + initiative.getDuration());
        System.out.println("Category: " + initiative.getCategory());
        System.out.println("Visibility: " + initiative.getIsPublic());
        System.out.println("Participants: ");

        ArrayList<String> participants = initiative.getParticipants();
        for (String participant : participants) {
            System.out.println(participant);
        }
    }
}