package server.controller;

import server.model.ConnectionHandler;
import shared.CommunityMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class MessageController implements IMessageController{
    private ConnectionControllerServer server;

    public MessageController(ConnectionControllerServer server) {
        this.server = server;
    }
    @Override
    public synchronized void sendMessage(CommunityMessage message) {
        Map<String, ConnectionHandler> users = server.getConnectedUsers();
        List<String> recipients = message.getRecipients();

        if (recipients == null) { // null = public message
            for (Map.Entry<String, ConnectionHandler> user : users.entrySet()) {
                try {
                    user.getValue().serverSendsObject(message);
                } catch (IOException e) {
                    System.err.println("Failed to send to " + user.getKey() + ": " + e.getMessage());
                }
            }
        } else {
            for (String recipient : recipients) {
                ConnectionHandler handler = users.get(recipient);
                if (handler != null) {
                    try {
                        handler.serverSendsObject(message);
                    } catch (IOException e) {
                        System.err.println("Failed to send to " + recipient + ", " + e.getMessage());
                    }
                } else {
                    System.out.println("Recipient is not connected: " + recipient);
                }
            }
        }
    }

}
