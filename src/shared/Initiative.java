package shared;

import java.util.ArrayList;

public class Initiative implements IInitiative {
    private User creator;
    private String title, description, location, duration;
    private InitiativeCategory category;
    private boolean isPublic;
    private ArrayList<User> participants = new ArrayList<>();

    public Initiative(User creator, boolean isPublic) {
        this.creator = creator;
        this.isPublic = isPublic;
        participants.add(creator);
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

    public void addParticipant(User participant) {
        participants.add(participant);
    }

    @Override
    public User getCreator() {
        return null;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getLocation() {
        return "";
    }

    @Override
    public String getDuration() {
        return "";
    }

    @Override
    public Enum getCategory() {
        return null;
    }

    @Override
    public boolean getIsPublic() {
        return false;
    }

    @Override
    public ArrayList<User> getParticipants() {
        return participants;
    }
}
