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
}