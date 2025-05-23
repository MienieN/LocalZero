package server.controller;

import server.model.ConnectionHandler;
import shared.IMessage;

import java.util.ArrayList;

public class MessageController implements IMessageController{
    private ConnectionControllerServer server;

    public MessageController(ConnectionControllerServer server) {
        this.server = server;
    }
    @Override
    public void sendMessage(IMessage message) {
        ArrayList<ConnectionHandler> users = server.getConnectedUsers();

    }
}
