package server.controller;

import server.model.ConnectionHandler;

import java.net.Socket;
import java.util.ArrayList;

public class ConnectionControllerServer {
    private ArrayList<ConnectionHandler> connectedUsers = new ArrayList<>();
    private UserLogInController userLoginController;
    public void createHandler(Socket socket) {
        new ConnectionHandler(socket, this, userLoginController);
    }

    public void endHandler(ConnectionHandler connectionHandler) {
        connectedUsers.remove(connectionHandler);
    }

    public void addHandler(ConnectionHandler connectionHandler) {
        connectedUsers.add(connectionHandler);
    }

    public void setUserLoginController(UserLogInController userLoginController) {
        this.userLoginController = userLoginController;
    }
}
