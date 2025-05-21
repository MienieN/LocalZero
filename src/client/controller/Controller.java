package client.controller;

import client.view.Terminal;
import shared.Login;

public class Controller {
    private Terminal terminal;
    private ConnectionControllerClient connectionControllerClient;

    public Controller(Terminal terminal, ConnectionControllerClient connectionControllerClient) {
        this.connectionControllerClient = connectionControllerClient;
        this.terminal = terminal;
        Login test = terminal.loginUser();
        connectionControllerClient.sendObject(test);
    }
}
