package client.controller;

import client.view.ServerConnection;
import client.view.Terminal;
import shared.CommunityMessage;
import shared.Message;
import shared.MessageType;
import shared.User;

import java.util.ArrayList;

public class ConnectionControllerClient {
    private ServerConnection serverConnection;
    private Controller controller;
    private static ConnectionControllerClient instance;

    public static ConnectionControllerClient getInstance() {
        if (instance == null) {
            instance = new ConnectionControllerClient();
        }
        return instance;
    }
    
    public void inputReceived(Object object) {
        if (object instanceof Message) {
            if(((Message) object).getType()== MessageType.ERROR_MESSAGE){
                ((Message) object).displayMessage();
                controller.checkLoginScreenChoice();
            }
            
            else if (((Message) object).getType() == MessageType.SUCCESS_MESSAGE) {
                ((Message) object).displayMessage();
            }

            else if (((Message) object).getType() == MessageType.CO2_MESSAGE) {
                System.out.print("Kg of CO2 saved: ");
                ((Message) object).displayMessage();
                Terminal.getInstance().showMenu();
            }
        }

        if (object instanceof CommunityMessage) {
            ArrayList<CommunityMessage> messages = controller.getFeedController().getMessages();
            messages.add((CommunityMessage) object);
            System.out.println("DEBUG: Added new message to feed: " + object);
        }
        
        else if (object instanceof User){
            controller.setLoggedInUser((User) object);
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
