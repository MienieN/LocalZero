package client.model;

import shared.Registration;
import shared.UserInformation;

public class EmailValidation implements UsersValidation {
    private UsersValidation next;
    @Override
    public boolean validate (UserInformation inputText) {
        String email = ((Registration)inputText).getEmail();
        
        if (email == null) {
            System.out.println("Invalid email address");
            return false;
        }
        if (email.isEmpty()) {
            System.out.println("Invalid email address");
            return false;
        }
        if (email.isBlank()) {
            System.out.println("Invalid email address");
            return false;
        }
        if (email.length() < 11 || email.length() > 28) {
            System.out.println("Invalid email address");
            return false;
        }
        if(!email.contains("@")){
            System.out.println("Invalid email address");
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
