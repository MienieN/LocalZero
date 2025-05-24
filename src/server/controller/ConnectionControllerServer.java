package server.controller;

import server.model.ConnectionHandler;
import shared.Action;
import shared.IsAdminStatus;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectionControllerServer {
    private Map<String, ConnectionHandler> connectedUsers = new HashMap<>();
    private UserInformationController userLoginController;
    private ActionController actionController;
    
    public void createHandler(Socket socket) {
        new ConnectionHandler(socket, this, userLoginController);
    }

    public void endHandler(ConnectionHandler connectionHandler) {
        connectedUsers.remove(connectionHandler.getUserName());
    }

    public void addHandler(ConnectionHandler connectionHandler) {
        connectedUsers.put(connectionHandler.getUserName(), connectionHandler);
    }

    public void setUserLoginController(UserInformationController userLoginController) {
        this.userLoginController = userLoginController;
    }

    public void setActionController(ActionController actionController) {
        this.actionController = actionController;
    }

    public void doAction(Action object) {
        try {
            actionController.doAction(object);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, ConnectionHandler> getConnectedUsers() {
        return connectedUsers;
    }
}