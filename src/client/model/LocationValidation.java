package client.model;

import shared.Registration;
import shared.UserInformation;

public class LocationValidation implements UsersValidation {
    private UsersValidation next;
    
    @Override
    public boolean validate (UserInformation inputText) {
        String location = ((Registration)inputText).getLocation();
        
        if (location == null) {
            System.out.println("Invalid Location");
            return false;
        }
        if (location.isEmpty()) {
            System.out.println("Invalid Location");
            return false;
        }
        if (location.isBlank()) {
            System.out.println("Invalid Location");
            return false;
        }
        if (location.length() < 3 || location.length() > 16) {
            System.out.println("Invalid Location");
            return false;
        }
        if (!location.matches("[a-zA-Z ]*")) {
            System.out.println("Invalid Location");
            return false;
        }
        
        if (next == null) {
            return true;
        } else{
            return next.validate(inputText);
        }
    }
    
    public void setNext (UsersValidation next) {
        this.next = next;
    }
}
