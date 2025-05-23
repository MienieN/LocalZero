package shared;

import java.util.List;

public class DirectMessage extends CommunityMessage {
    private boolean read;
    public DirectMessage(String text, List<User> recipients, User sender) {
        super(text, sender, recipients);
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public void displayMessage() {
        System.out.println("--- Message from " + super.getSender() + "---");
        super.displayMessage();
    }
}
