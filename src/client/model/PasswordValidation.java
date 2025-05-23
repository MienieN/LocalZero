package client.model;

import shared.UserInformation;

public class PasswordValidation implements UsersValidation {
    private UsersValidation next;
    
    @Override
    public boolean validate (UserInformation inputText) {
        String password = inputText.getPassword();
        
        if (password == null) {
            System.out.println("Invalid Password");
            return false;
        }
        if (password.isEmpty()) {
            System.out.println("Invalid Password");
            return false;
        }
        if (password.isBlank()) {
            System.out.println("Invalid Password");
            return false;
        }
        if (password.length() < 6 || password.length() > 16) {
            System.out.println("Invalid Password");
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
    

