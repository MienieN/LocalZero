package server.model;

import server.controller.ConnectionControllerServer;
import server.controller.UserLogInController;
import shared.Login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket socket;

    private ConnectionControllerServer connectionControllerServer;
    private UserLogInController userLogInController;
    private ObjectInputStream ois;
    private  ObjectOutputStream oos;
    public ConnectionHandler(Socket socket, ConnectionControllerServer connectionControllerServer,
                             UserLogInController userLogInController) {
        this.connectionControllerServer = connectionControllerServer;
        this.userLogInController = userLogInController;
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

    private void clientSendsObject(Object object) {
        System.out.println(object.getClass());
        if (object instanceof Login) {
            userLogInController.loginUser((Login) object);
            connectionControllerServer.addHandler(this);
//        } else if (object instanceof Message) {
//            messageControllerServer.sendMessage((Message) object);
//        }
        }
    }
}
