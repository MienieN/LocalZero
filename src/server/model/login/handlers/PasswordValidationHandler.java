package server.model.login.handlers;

import server.model.login.classes.ValidateUsers;

public class PasswordValidationHandler implements ValidateUsers {
    
    @Override
    public boolean validate (String inputText) {
        if (inputText.length() < 3) {
            return false;
        } else {
            return true;
        }
    }
    
}
