package client;

import client.controller.ConnectionControllerClient;
import client.controller.Controller;
import client.view.ServerConnection;
import client.view.Terminal;

public class MainClient {
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        
        ServerConnection serverConnection = new ServerConnection("127.0.01", 741);
        ConnectionControllerClient connectionControllerClient = new ConnectionControllerClient();
        
        Controller controller = new Controller();
        
        connectionControllerClient.setServerConnection(serverConnection);
        connectionControllerClient.setController(controller);
        
        serverConnection.setConnectionControllerClient(connectionControllerClient);
        controller.setTerminal(terminal);
        controller.setConnectionController(connectionControllerClient);
        
        terminal.setController(controller);
        
        controller.checkLoginScreenChoice(); // This starts the very first menu
        
    }
}
