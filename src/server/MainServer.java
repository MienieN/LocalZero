package server;

import client.controller.ConnectionControllerClient;
import server.controller.ActionController;
import server.controller.ConnectionControllerServer;
import server.controller.UserLogInController;
import server.view.ClientConnection;
import server.view.DatabaseConnection;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args){
        ConnectionControllerServer connectionControllerServer = new ConnectionControllerServer();
        UserLogInController userLogInController = new UserLogInController();
        ActionController actionController = new ActionController();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        actionController.setDatabaseConnection(databaseConnection);
        connectionControllerServer.setActionController(actionController);
        connectionControllerServer.setUserLoginController(userLogInController);
        try {
            new ClientConnection(connectionControllerServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Server is running");
    }
}
