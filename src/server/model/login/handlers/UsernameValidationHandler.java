package server.model.login.handlers;

import server.model.login.classes.ValidateUsers;

public class UsernameValidationHandler implements ValidateUsers {
    @Override
    public boolean validate (String inputText) {
        return true;
    }
    
}
