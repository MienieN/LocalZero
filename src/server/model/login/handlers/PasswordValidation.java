package server.model.login.handlers;

import server.model.login.classes.ValidateUsers;

// TODO: remove the validate method, this class should validate that the password matches the username in the database
public class PasswordValidation implements ValidateUsers {
    
    @Override
    public boolean validate (String inputText) {
        if (inputText.length() < 3) {
            return false;
        } else {
            return true;
        }
    }
    
}
