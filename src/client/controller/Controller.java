package client.controller;

import client.model.*;
import client.view.Terminal;
import shared.*;

import java.util.ArrayList;

public class Controller {
    private Terminal terminal;
    private ConnectionControllerClient connectionControllerClient;
    private User user;
    
    public Controller ( ) {
        //placeholder user for testing
        ArrayList <String> temp = new ArrayList <>();
        temp.add("roles");
        user = new User("test", "testpw", "test@email", "location", temp, false);
    }
    
    public void setTerminal (Terminal terminal) {
        this.terminal = terminal;
    }
    
    public void setConnectionController (ConnectionControllerClient connectionControllerClient) {
        this.connectionControllerClient = connectionControllerClient;
    }
    
    public void login (Login login) {
        ValidateUsers validateUserName = new UsernameValidation();
        ValidateUsers validateUserPassword = new PasswordValidation();
        
        while (! (validateUserName.validate(login.getUsername()) && validateUserPassword.validate(login.getPassword()))) {
            System.out.println("Login failed");
            terminal.startupMenu();
        }
        
        if (validateUserName.validate(login.getUsername()) && validateUserPassword.validate(login.getPassword())) {
            connectionControllerClient.sendObject(login);
            // TODO: this needs to lead to a message board or something
        }
    }
    
    public void register (Registration registration) {
        ValidateUsers validateUserName = new UsernameValidation();
        ValidateUsers validateUserPassword = new PasswordValidation();
        ValidateUsers validateUserEmail = new EmailValidation();
        ValidateUsers validateUserLocation = new LocationValidation();
        
        while (! (validateUserName.validate(registration.getUsername()) &&
                validateUserPassword.validate(registration.getPassword()) &&
                validateUserEmail.validate(registration.getEmail()) &&
                validateUserLocation.validate(registration.getLocation()))) {
            System.out.println("Could not create account");
            terminal.startupMenu();
        }
        
        if (validateUserName.validate(registration.getUsername()) &&
                validateUserPassword.validate(registration.getPassword()) &&
                validateUserEmail.validate(registration.getEmail()) &&
                validateUserLocation.validate(registration.getLocation())) {
            
            connectionControllerClient.sendObject(registration);
            // TODO: this needs to lead to instant login.... ie message board or something
        }
        
    }
    
    public void checkLoginScreenChoice ( ) {
        UserInformation userInformation = terminal.startupMenu();
        
        if (userInformation instanceof Login) {
            login((Login) userInformation);
        }
        else if (userInformation instanceof Registration) {
            register((Registration) userInformation);
            
        }
    }
    
}
