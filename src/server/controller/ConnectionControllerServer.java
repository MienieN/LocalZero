package server.controller;

import server.model.ConnectionHandler;
import shared.Action;
import shared.ActionAbstract;
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
    private MessageController messageController = new MessageController(this);
    
    public void createHandler(Socket socket) {
        new ConnectionHandler(socket, this, userLoginController, messageController);
    }

    public synchronized void endHandler(ConnectionHandler connectionHandler) {
        connectedUsers.remove(connectionHandler.getUserName());
    }

    public synchronized void addHandler(ConnectionHandler connectionHandler) {
        connectedUsers.put(connectionHandler.getUserName(), connectionHandler);
        System.out.println(connectedUsers);
    }

    public void setUserLoginController(UserInformationController userLoginController) {
        this.userLoginController = userLoginController;
    }

    public void setActionController(ActionController actionController) {
        this.actionController = actionController;
    }

    public synchronized void doAction(ActionAbstract object) {
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