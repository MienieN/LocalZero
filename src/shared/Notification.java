package shared;

import java.util.List;

public class Notification extends CommunityMessage {
    private String originalPostTitle;
    public Notification(String text, MessageType type, User sender, List<User> recipients, String originalPostTitle) {
        super(text, type, sender, recipients);
        this.originalPostTitle = originalPostTitle;
    }

    public String getOriginalPostTitle() {
        return originalPostTitle;
    }

    @Override
    public void displayMessage() {
        switch (super.getType()) {
            case COMMENT -> System.out.println("--- New comment on " + originalPostTitle + " from " + super.getSender() + " ---");
            case LIKE -> {
                System.out.println("--- Your post " + originalPostTitle + " was liked by " + super.getSender() + " ---");
                return;
            }
            case NEW_INITIATIVE -> System.out.println("--- New Initiative ---");
        }
        super.displayMessage();
    }
}
