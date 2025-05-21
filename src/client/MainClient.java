package client;

import client.controller.ConnectionControllerClient;
import client.controller.Controller;
import client.view.ServerConnection;
import client.view.Terminal;

public class MainClient {
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        ServerConnection serverConnection = new ServerConnection("127.0.01", 741);
        ConnectionControllerClient connectionControllerClient = new ConnectionControllerClient(serverConnection);
        serverConnection.setConnectionControllerClient(connectionControllerClient);
        Controller controller = new Controller(terminal, connectionControllerClient);
    }
}
