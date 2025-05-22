package client.controller;

import client.view.ServerConnection;
import shared.Login;

public class ConnectionControllerClient {
    ServerConnection serverConnection;


    public void inputReceived(Object object) {
    }

    public void sendObject(Object object) {
        serverConnection.sendObject(object);
    }

    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }
}
