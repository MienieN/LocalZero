package shared;

import java.util.ArrayList;
import java.util.List;

public class DirectMessage extends CommunityMessage {
    private boolean read;
    public DirectMessage(String text, MessageType type, String sender, ArrayList<String> recipients) {
        super(text, type, sender, recipients);
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
