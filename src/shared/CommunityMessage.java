package shared;

import java.util.List;

public abstract class CommunityMessage extends Message {
    private User sender;
    private List<User> recipients;

    public CommunityMessage (String text, MessageType type, User sender, List<User> recipients) {
        super.setMessage(text);
        super.setType(type);
        this.sender = sender;
        this.recipients = recipients;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }

    public User getSender() {
        return sender;
    }
}
