package server.model;

import server.controller.ConnectionControllerServer;
import server.controller.MessageController;
import server.controller.UserInformationController;
import shared.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket socket;

    private ConnectionControllerServer connectionControllerServer;
    private UserInformationController userInformationController;
    private ObjectInputStream ois;
    private  ObjectOutputStream oos;
    private String username;
    private MessageController messageController;
    
    public ConnectionHandler(Socket socket, ConnectionControllerServer connectionControllerServer,
                             UserInformationController userInformationController,
                             MessageController messageController) {
        this.connectionControllerServer = connectionControllerServer;
        this.userInformationController = userInformationController;
        this.socket = socket;
        this.messageController = messageController;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        start();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                clientSendsObject(ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                try {
                    ois.close();
                    oos.close();
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.interrupt();
            }
        }
        connectionControllerServer.endHandler(this);
    }

    private void clientSendsObject(Object object) throws IOException {
        System.out.println(object.getClass());
        
        if (object instanceof Login) {
            serverReceivesLogin((Login) object);
            this.username = ((Login) object).getUsername();
        }
        else if(object instanceof Registration) {
            serverReceivesRegistration((Registration) object);
        }
        // TODO: create a serverReceivesAction ... for future development
        else if (object instanceof ActionAbstract) {
            connectionControllerServer.doAction((ActionAbstract) object);
        }
        else if (object instanceof IsAdminStatus){
            userInformationController.alterAdminStatus((IsAdminStatus) object);
        }
        else if (object instanceof RoleStatus) {
            userInformationController.alterUserRole((RoleStatus) object);
        }
        else if (object instanceof CO2Status) {
            serverSendsObject(userInformationController.showCO2StatusForLocation((CO2Status) object));
        }
        else if (object instanceof CommunityMessage) {
            messageController.sendMessage((CommunityMessage) object);
        }
    }
    
    private void serverReceivesLogin(Login login) throws IOException {
        if (userInformationController.loginUser(login) != null){
            setUsername(login.getUsername());
            
            User user = userInformationController.loginUser(login);
            connectionControllerServer.addHandler(this);
            serverSendsObject(user);
        }
        
        else {
            IMessage message = new Message();
            message.setMessage("User does not exist"); // TODO update this to Login failed
            message.setType(MessageType.ERROR_MESSAGE);
            
            serverSendsObject(message);
        }
    }
    
    private void serverReceivesRegistration(Registration registration) throws IOException {
        IMessage message = userInformationController.registerUser(registration.getUsername(),
                registration.getPassword(), registration.getEmail(),
                registration.getLocation(), registration.getRole(),
                registration.isAdmin());
        
        connectionControllerServer.addHandler(this);
        if (message.getType() == MessageType.SUCCESS_MESSAGE){
            serverSendsObject(message);
            Login login = new Login(registration.getUsername(), registration.getPassword());
            serverSendsObject(userInformationController.loginUser(login));
        }
        else if (message.getType() == MessageType.ERROR_MESSAGE){
            serverSendsObject(message);
        }
    }
    
    public void serverSendsObject(Object object) throws IOException {
        oos.writeObject(object);
        oos.flush();
    }

    public String getUserName() {
        return this.username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
}