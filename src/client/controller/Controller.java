package client.controller;

import client.model.*;
import client.view.Terminal;
import shared.*;

import java.util.ArrayList;

public class Controller {
    private Terminal terminal;
    private ConnectionControllerClient connectionControllerClient;
    private User user;
    private ActionInitiativeStorage storage = new ActionInitiativeStorage();
    private ActionInitiativeTestValues actionInitiativeTestValues = new ActionInitiativeTestValues(storage, user);
    
    public void setTerminal (Terminal terminal) {
        this.terminal = terminal;
    }
    
    public void setConnectionController (ConnectionControllerClient connectionControllerClient) {
        this.connectionControllerClient = connectionControllerClient;
    }
    
    public void login (Login login) {
        UsersValidation validateUserName = new UsernameValidation();
        UsersValidation validateUserPassword = new PasswordValidation();
        
        // Chain of Responsibility
        validateUserName.setNext(validateUserPassword);
        
        while (! (validateUserName.validate(login))) {
            System.out.println("Login failed");
            login = (Login) terminal.startupMenu();
        }
        
        if (validateUserName.validate(login)) {
            connectionControllerClient.sendObject(login);
        }
    }
    
    public void register (Registration registration) {
        UsersValidation validateUserName = new UsernameValidation();
        UsersValidation validateUserPassword = new PasswordValidation();
        UsersValidation validateUserEmail = new EmailValidation();
        UsersValidation validateUserLocation = new LocationValidation();
        
        // Chain of Responsibility
        validateUserName.setNext(validateUserPassword);
        validateUserPassword.setNext(validateUserEmail);
        validateUserEmail.setNext(validateUserLocation);
        
        while (! (validateUserName.validate(registration))) {
            System.out.println("Could not create account");
            terminal.startupMenu();
        }
        
        if (validateUserName.validate(registration)) {
            connectionControllerClient.sendObject(registration);
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
        ActionAbstract biking = new Biking(kilometers, user);
        storage.addAction(biking);
        connectionControllerClient.sendObject(biking);
    }

    public void usedPublicTransport (int km) {
        ActionAbstract publicTransport = new PublicTransport(km, user);
        storage.addAction(publicTransport);
        connectionControllerClient.sendObject(publicTransport);
    }

    public void composting (int foodwaste) {
        ActionAbstract composting = new Composting(foodwaste, user);
        storage.addAction(composting);
        connectionControllerClient.sendObject(composting);
    }

    public void plantedTrees(int treesPlanted) {
        ActionAbstract plantTrees = new PlantTrees(treesPlanted, user);
        storage.addAction(plantTrees);
        connectionControllerClient.sendObject(plantTrees);
    }

    public void createInitiative (String title, String description, String location, String duration, InitiativeCategory category, boolean isPublic) {
        IInitiative initiative = new Initiative(user, isPublic);
        initiative.setTitle(title);
        initiative.setDescription(description);
        initiative.setLocation(location);
        initiative.setDuration(duration);
        initiative.setCategory(category);
        storage.addInitiative(initiative);
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

    public void alterUserRole (RoleStatus status) {
        connectionControllerClient.sendObject(status);
    }

    public void viewCO2StatusForLocation(CO2Status co2Status) {
        co2Status.setLocation(user.getLocation());
        connectionControllerClient.sendObject(co2Status);
    }
}

