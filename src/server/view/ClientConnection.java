package server.view;


import server.controller.ConnectionControllerServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientConnection extends Thread{
    private ServerSocket serverSocket;              // The ServerSocket for accepting incoming connections
    private ConnectionControllerServer connectionControllerServer;      // The ClientController for handling client connections

    public ClientConnection(ConnectionControllerServer connectionControllerServer) throws IOException {
        serverSocket = new ServerSocket(741);
        this.connectionControllerServer = connectionControllerServer;
        start();
    }


    public void run() {
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                sendSocket(socket);
                socket = null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Sends the provided socket to the ClientController for further handling.
     *
     * @param socket The Socket representing the incoming client connection.
     * @throws IOException If an I/O error occurs while creating the client handler in the ClientController.
     */
    public void sendSocket(Socket socket) throws IOException {
        connectionControllerServer.createHandler(socket);
    }
}
