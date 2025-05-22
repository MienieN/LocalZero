package client.controller;

import client.view.Terminal;
import shared.Biking;
import shared.Login;
import shared.User;

import java.util.ArrayList;

public class Controller {
    private Terminal terminal;
    private ConnectionControllerClient connectionControllerClient;
    private User user;
    public Controller() {
        //placeholder user for testing
        ArrayList<String> temp = new ArrayList<>();
        temp.add("roles");
        user = new User("test", "testpw", "test@email", "location", temp, false);
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void setConnectionController(ConnectionControllerClient connectionControllerClient) {
        this.connectionControllerClient = connectionControllerClient;
    }

    public void makeChoice(){
        int choice = terminal.printMenu();
        switch (choice) {
            case 1:
                int kilometers;
                kilometers = terminal.bike();
                Biking biking = new Biking(kilometers, user);
                connectionControllerClient.sendObject(biking);
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
