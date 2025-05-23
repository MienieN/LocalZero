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
        UsersValidation validateUserName = new UsernameValidation();
        UsersValidation validateUserPassword = new PasswordValidation();
        
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
        UsersValidation validateUserName = new UsernameValidation();
        UsersValidation validateUserPassword = new PasswordValidation();
        UsersValidation validateUserEmail = new EmailValidation();
        UsersValidation validateUserLocation = new LocationValidation();
        
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
    
    public void goneBiking (int kilometers){
        Action biking = new Biking(kilometers, user);
        connectionControllerClient.sendObject(biking);
    }

    public void usedPublicTransport (int km) {
        Action publicTransport = new PublicTransport(km, user);
        connectionControllerClient.sendObject(publicTransport);
    }

    public void composting (int foodwaste) {
        Action composting = new Composting(foodwaste, user);
        connectionControllerClient.sendObject(composting);
    }

    public void createInitiative (String title, String description, String location, String duration, InitiativeCategory category, boolean isPublic) {
        IInitiative initiative = new Initiative(user, isPublic);
        initiative.setTitle(title);
        initiative.setDescription(description);
        initiative.setLocation(location);
        initiative.setDuration(duration);
        initiative.setCategory(category);
        connectionControllerClient.sendObject(initiative);
    }
    
    public void setLoggedInUser (User user ) {
        this.user = user;
        terminal.showMenu();
    }
    
    public User getUser ( ) {
        return user;
    }
    
    public void alterAdminStatus (IsAdminStatus admin) {
        connectionControllerClient.sendObject(admin);
    }

}
