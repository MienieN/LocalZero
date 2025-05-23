package shared;

public class Initiative implements IInitiative {
    private User creator;
    private String title, description, location, duration;
    private InitiativeCategory category;
    private boolean isPublic;

    public Initiative(User creator, boolean isPublic) {
        this.creator = creator;
        this.isPublic = isPublic;
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

    public void setIsPublic(boolean visibility) {
        this.isPublic = visibility;
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
}
