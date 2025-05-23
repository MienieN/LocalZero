package shared;

import java.util.ArrayList;

public class Initiative implements IInitiative {
    private String username;
    private String title, description, location, duration;
    private InitiativeCategory category;
    private boolean isPublic;
    private ArrayList<String> participants = new ArrayList<>();

    public Initiative(String username, boolean isPublic) {
        this.username = username;
        this.isPublic = isPublic;
        participants.add(username);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setCategory(InitiativeCategory category) {
        this.category = category;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void addParticipant(String participant) {
        participants.add(participant);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getDuration() {
        return duration;
    }

    @Override
    public Enum getCategory() {
        return category;
    }

    @Override
    public boolean getIsPublic() {
        return isPublic;
    }

    @Override
    public ArrayList<String> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return title + " - " + username;
    }
}
