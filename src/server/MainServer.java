package server;

import client.controller.ConnectionControllerClient;
import server.controller.ConnectionControllerServer;
import server.view.ClientConnection;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args){
        ConnectionControllerServer connectionControllerServer = new ConnectionControllerServer();
        try {
            new ClientConnection(connectionControllerServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
