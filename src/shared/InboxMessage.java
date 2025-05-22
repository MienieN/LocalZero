package shared;

import java.util.List;

public class InboxMessage extends CommunityMessage {
    private User sender;
    private boolean read;
    public InboxMessage(String text, List<User> recipients, User sender) {
        super(text, recipients);
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public void displayMessage() {
        System.out.println("--- Message from " + sender + "---\n" + super.getText());
    }
}
