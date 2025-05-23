package shared;

public class Message implements IMessage {
    private String message;
    private MessageType type;
    
    @Override
    public void displayMessage () {
        System.out.println(message);
    }
    
    @Override
    public void setMessage (String message) {
        this.message = message;
    }
    
    @Override
    public void setType (MessageType type) {
        this.type = type;
    }
    
    @Override
    public MessageType getType ( ) {
        return type;
    }
}
