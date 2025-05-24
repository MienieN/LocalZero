package server;

import server.controller.ActionController;
import server.controller.ConnectionControllerServer;
import server.controller.MessageController;
import server.controller.UserInformationController;
import server.view.ClientConnection;
import server.view.DatabaseConnection;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args){
        ConnectionControllerServer connectionControllerServer = new ConnectionControllerServer();
        UserInformationController userInformationController = new UserInformationController();
        ActionController actionController = new ActionController();

        userInformationController.setDatabaseConnection();
        
        actionController.setDatabaseConnection();
        connectionControllerServer.setActionController(actionController);
        connectionControllerServer.setUserLoginController(userInformationController);
        
        try {
            new ClientConnection(connectionControllerServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("Server is running");
    }
}
