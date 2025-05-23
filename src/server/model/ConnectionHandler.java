package server.model;

import server.controller.ConnectionControllerServer;
import server.controller.UserInformationController;
import shared.Action;
import shared.Login;
import shared.IMessage;
import shared.Registration;

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
    
    public ConnectionHandler(Socket socket, ConnectionControllerServer connectionControllerServer,
                             UserInformationController userInformationController) {
        this.connectionControllerServer = connectionControllerServer;
        this.userInformationController = userInformationController;
        this.socket = socket;
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
            // TODO: proper error handling if the username does not exist
            //  (need a message on the client side for "user not found")
            //  need to return to login screen if failure occurs
            userInformationController.loginUser((Login) object);
            connectionControllerServer.addHandler(this);
            
        }
        
        else if(object instanceof Registration) {
            IMessage message = userInformationController.registerUser(((Registration) object).getUsername(),
                    ((Registration) object).getPassword(), ((Registration) object).getEmail(),
                    ((Registration) object).getLocation(), ((Registration) object).getRole(),
                    ((Registration) object).isAdmin());
            
            //TODO: if registration is successful, we send client back to login in order to login (or auto login)
            connectionControllerServer.addHandler(this);
            serverSendsObject(message);
        }
        
        else if (object instanceof Action) {
            connectionControllerServer.doAction((Action) object);
        }
    }
    
    private void serverSendsObject(Object object) throws IOException {
        oos.writeObject(object);
        oos.flush();
    }
}
