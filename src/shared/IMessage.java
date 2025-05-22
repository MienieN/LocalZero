package shared;

import java.io.Serializable;

public interface IMessage extends Serializable{
    String message = "";
    
    public void displayMessage();
    
    public void setMessage(String message);
    
    public void setType (MessageType type);
    public MessageType getType();
    
}
