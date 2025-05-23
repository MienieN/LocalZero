package shared;

import java.util.ArrayList;

public interface IInitiative {
    User creator = null;
    String title = "";
    String description = "";
    String location = "";
    String duration = "";
    InitiativeCategory category = null;
    boolean isPublic = false;
    ArrayList<User> participants = new ArrayList<>();

    void setTitle(String title);
    void setDescription(String description);
    void setLocation(String location);
    void setDuration(String duration);
    void setCategory(InitiativeCategory category);
    void setIsPublic(boolean isPublic);
    void addParticipant(User participant);

    User getCreator();
    String getTitle();
    String getDescription();
    String getLocation();
    String getDuration();
    Enum getCategory();
    boolean getIsPublic();
    ArrayList<User> getParticipants();
}
