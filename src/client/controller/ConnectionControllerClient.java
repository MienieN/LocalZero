package client.controller;

import client.view.ServerConnection;
import shared.Login;

public class ConnectionControllerClient {
    ServerConnection serverConnection;

    public ConnectionControllerClient(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void inputReceived(Object object) {
    }

    public void sendObject(Object object) {
        serverConnection.sendObject(object);
    }
}
