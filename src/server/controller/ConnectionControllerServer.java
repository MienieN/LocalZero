package server.controller;

import server.model.ConnectionHandler;
import shared.Action;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionControllerServer {
    private ArrayList<ConnectionHandler> connectedUsers = new ArrayList<>();
    private UserInformationController userLoginController;
    private ActionController actionController;
    
    public void createHandler(Socket socket) {
        new ConnectionHandler(socket, this, userLoginController);
    }

    public void endHandler(ConnectionHandler connectionHandler) {
        connectedUsers.remove(connectionHandler);
    }

    public void addHandler(ConnectionHandler connectionHandler) {
        connectedUsers.add(connectionHandler);
    }

    public void setUserLoginController(UserInformationController userLoginController) {
        this.userLoginController = userLoginController;
    }

    public void setActionController(ActionController actionController) {
        this.actionController = actionController;
    }

    //TODO rename this
    public void doAction(Action object) {
        try {
            actionController.doAction(object);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
