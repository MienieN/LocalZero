package client;

import client.controller.ConnectionControllerClient;
import client.controller.Controller;
import client.view.ServerConnection;
import client.view.Terminal;

public class MainClient {
    public static void main(String[] args) {
        Terminal terminal = Terminal.getInstance();
        
        ServerConnection serverConnection = ServerConnection.getInstance();
        ConnectionControllerClient connectionControllerClient = ConnectionControllerClient.getInstance();
        
        Controller controller = new Controller();
        
        connectionControllerClient.setServerConnection();
        connectionControllerClient.setController(controller);
        
        serverConnection.setConnectionControllerClient(connectionControllerClient);
        controller.setTerminal();
        controller.setConnectionController(connectionControllerClient);
        controller.setFeedController();
        
        terminal.setController(controller);
        
        controller.checkLoginScreenChoice(); // This starts the very first menu
        
    }
}
