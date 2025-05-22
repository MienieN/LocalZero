package shared;

import java.util.List;

public abstract class CommunityMessage {
    private String text;
    private List<User> recipients;

    public CommunityMessage (String text, List<User> recipients) {
        this.text = text;
        this.recipients = recipients;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }

    public abstract void displayMessage();
}
