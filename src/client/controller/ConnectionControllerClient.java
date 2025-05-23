package client.controller;

import client.view.ServerConnection;
import shared.Message;
import shared.MessageType;

public class ConnectionControllerClient {
    private ServerConnection serverConnection;
    private Controller controller;
    
    public void inputReceived(Object object) {
        if (object instanceof Message) {
            if(((Message) object).getType()== MessageType.ERROR_MESSAGE){
                ((Message) object).displayMessage();
                controller.checkLoginScreenChoice();
            }
            
            else if (((Message) object).getType() == MessageType.SUCCESS_MESSAGE) {
                ((Message) object).displayMessage();
            }
        }
    }

    public void sendObject(Object object) {
        serverConnection.sendObject(object);
    }

    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }
    
    public void setController (Controller controller) {
        this.controller = controller;
    }
}
