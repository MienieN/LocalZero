package shared;

public class ServerMessage implements IServerMessage {
    private String message;
    private ServerMessageType type;
    
    @Override
    public void displayMessage () {
        System.out.println(message);
    }
    
    @Override
    public void setMessage (String message) {
        this.message = message;
    }
    
    @Override
    public void setType (ServerMessageType type) {
        this.type = type;
    }
    
    public ServerMessageType getType ( ) {
        return type;
    }
}
