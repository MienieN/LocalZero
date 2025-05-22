package client.controller;

import client.view.ServerConnection;
import shared.ServerMessage;
import shared.ServerMessageType;

public class ConnectionControllerClient {
    private ServerConnection serverConnection;
    private Controller controller;
    
    public void inputReceived(Object object) {
        if (object instanceof ServerMessage) {
            if(((ServerMessage) object).getType()== ServerMessageType.ERROR_MESSAGE){
                ((ServerMessage) object).displayMessage();
                controller.checkLoginScreenChoice();
            }
            
            else if (((ServerMessage) object).getType() == ServerMessageType.SUCCESS_MESSAGE) {
                ((ServerMessage) object).displayMessage();
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
