package client.view;

import client.controller.ConnectionControllerClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection extends Thread{

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ConnectionControllerClient connectionControllerClient;
    
    public ServerConnection(String ipAddress, int port) {
        try {
            socket = new Socket(ipAddress, port);

            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        start();
    }

    public void run() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                Object object = ois.readObject();
                connectionControllerClient.inputReceived(object);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendObject(Object object){
        try {
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method to close the connection.
     */
    public void closeConnection() {
        this.interrupt();
        try {
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConnectionControllerClient(ConnectionControllerClient connectionControllerClient) {
        this.connectionControllerClient = connectionControllerClient;
    }
}
