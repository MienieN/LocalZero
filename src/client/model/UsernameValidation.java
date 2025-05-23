package client.model;

import shared.UserInformation;

public class UsernameValidation implements UsersValidation {
    private UsersValidation next;
    
    @Override
    public boolean validate (UserInformation inputText) {
        String username = inputText.getUsername();
        
        if (username == null) {
            System.out.println("Invalid username");
            return false;
        }
        if (username.isEmpty()) {
            System.out.println("Invalid username");
            return false;
        }
        if (username.isBlank()) {
            System.out.println("Invalid username");
            return false;
        }
        if (username.length() < 3 || username.length() > 8) {
            System.out.println("Invalid username");
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
