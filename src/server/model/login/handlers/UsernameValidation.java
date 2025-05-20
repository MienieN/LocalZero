package server.model.login.handlers;

import server.model.login.classes.ValidateUsers;

public class UsernameValidation implements ValidateUsers {
    @Override
    public boolean validate (String inputText) {
        return true;
    }
    
}
