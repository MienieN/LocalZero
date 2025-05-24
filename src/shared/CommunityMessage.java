package shared;

import java.util.ArrayList;
import java.util.List;

public abstract class CommunityMessage extends Message {
    private String sender;
    private ArrayList<String> recipients;

    public CommunityMessage (String text, MessageType type, String sender, ArrayList<String> recipients) {
        super.setMessage(text);
        super.setType(type);
        this.sender = sender;
        this.recipients = recipients;
    }

    public ArrayList<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<String> recipients) {
        this.recipients = recipients;
    }

    public String getSender() {
        return sender;
    }
}
