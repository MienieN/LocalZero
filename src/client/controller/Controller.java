package client.controller;

import client.model.*;
import client.view.Terminal;
import shared.*;

import java.util.ArrayList;

public class Controller {
    private Terminal terminal;
    private ConnectionControllerClient connectionControllerClient;
    private FeedController feedController;
    private User user;
    private String username;
    private String location;

    // Test purpose variables
    private ActionInitiativeStorage storage = new ActionInitiativeStorage();
    private ActionInitiativeTestValues actionInitiativeTestValues = new ActionInitiativeTestValues(storage, user);
    
    public void setTerminal (Terminal terminal) {
        this.terminal = terminal;
    }
    
    public void setConnectionController (ConnectionControllerClient connectionControllerClient) {
        this.connectionControllerClient = connectionControllerClient;
    }

    public void setFeedController () {
        this.feedController = new FeedController(storage);
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
        ActionAbstract biking = new Biking(kilometers, username, location);
        storage.addAction(biking);
        connectionControllerClient.sendObject(biking);
    }

    public void usedPublicTransport (int km) {
        ActionAbstract publicTransport = new PublicTransport(km, username, location);
        storage.addAction(publicTransport);
        connectionControllerClient.sendObject(publicTransport);
    }

    public void composting (int foodwaste) {
        ActionAbstract composting = new Composting(foodwaste, username, location);
        storage.addAction(composting);
        connectionControllerClient.sendObject(composting);
    }

    public void plantedTrees(int treesPlanted) {
        ActionAbstract plantTrees = new PlantTrees(treesPlanted, username, location);
        storage.addAction(plantTrees);
        connectionControllerClient.sendObject(plantTrees);
    }

    public void createInitiative (String title, String description, String location, String duration, InitiativeCategory category, boolean isPublic) {
        IInitiative initiative = new Initiative(username, isPublic);
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
        this.username = user.getUsername();
        this.location = user.getLocation();
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

    public void viewFeed(String feedType) {
        switch (feedType) {
            case "actions":
                feedController.viewActionsFeed();
                break;
            case "initiatives":
                feedController.viewInitiativesFeed();
                break;
            default:
                terminal.showMenu();
        }
    }

    public void joinInitiative() {
        feedController.joinInitiative(username);
    }

    public void likeAction() {
        feedController.likeAction();
    }

    public void viewInitiativeDetails() {
        feedController.viewInitiativeDetails();
    }

    public FeedController getFeedController() {
        return feedController;
    }

    public void createCommunityMessage(ArrayList<String> recipients, String message, MessageType type, String originalPostTitle) {
        CommunityMessage m;
        switch (type) {
            case LIKE -> m = new Notification(null, MessageType.LIKE, username, recipients, originalPostTitle);
        }
    }
}

