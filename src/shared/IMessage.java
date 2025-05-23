package shared;

import java.io.Serializable;

public interface IMessage extends Serializable{
    String message = "";
    
    void displayMessage();
    void setMessage(String message);
    void setType (MessageType type);
    MessageType getType();
    
}
