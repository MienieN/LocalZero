package client.controller;

import client.view.ServerConnection;
import client.view.Terminal;
import shared.Message;
import shared.MessageType;
import shared.User;

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
        
        else if (object instanceof User){
            controller.setLoggedInUser((User) object);
        }
    }

    public void sendObject(Object object) {
        serverConnection.sendObject(object);
    }

    public void setServerConnection() {
        this.serverConnection = ServerConnection.getInstance();
    }
    
    public void setController (Controller controller) {
        this.controller = controller;
    }
}
