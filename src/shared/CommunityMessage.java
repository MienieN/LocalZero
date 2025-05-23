package shared;

import java.util.List;

public abstract class CommunityMessage extends Message {
    private String sender;
    private List<String> recipients;

    public CommunityMessage (String text, MessageType type, String sender, List<String> recipients) {
        super.setMessage(text);
        super.setType(type);
        this.sender = sender;
        this.recipients = recipients;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSender() {
        return sender;
    }
}
