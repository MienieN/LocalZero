package shared;

import java.util.List;

public class Notification extends CommunityMessage {
    private NotificationType type;
    private String sender;
    private String originalPostTitle;
    public Notification(String text, List<User> recipients, NotificationType type, String sender, String originalPostTitle) {
        super(text, recipients);
        this.type = type;
        this.sender = sender;
        this.originalPostTitle = originalPostTitle;
    }

    public NotificationType getType() {
        return type;
    }

    public String getSender() {
        return sender;
    }

    public String getOriginalPostTitle() {
        return originalPostTitle;
    }

    @Override
    public void displayMessage() {
        switch (type) {
            case COMMENT -> System.out.println("--- New comment on " + originalPostTitle + " from " + sender + " ---");
            case LIKE -> {
                System.out.println("--- Your post " + originalPostTitle + " was liked by " + sender + " ---");
                return;
            }
            case NEW_INITIATIVE -> System.out.println("--- New Initiative ---");
        }
        System.out.println(super.getText());
    }
}
