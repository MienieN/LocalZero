package shared;

import java.io.Serializable;

public interface IServerMessage extends Serializable{
    String message = "";
    
    public void displayMessage();
    
    public void setMessage(String message);
    
    public void setType (ServerMessageType type);
    public ServerMessageType getType();
    
}
