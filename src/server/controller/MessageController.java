package server.controller;

import server.model.ConnectionHandler;
import shared.CommunityMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MessageController implements IMessageController{
    private ConnectionControllerServer server;

    public MessageController(ConnectionControllerServer server) {
        this.server = server;
    }
    @Override
    public void sendMessage(CommunityMessage message) {
        ArrayList<ConnectionHandler> users = server.getConnectedUsers();
        ArrayList<String> recipients = message.getRecipients();

        for (int i = 0; i < recipients.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                String recipient = recipients.get(i);
                ConnectionHandler currentUser = users.get(j);
                if (recipient.equals(currentUser.getUserName())) {
                    try {
                        currentUser.serverSendsObject(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
