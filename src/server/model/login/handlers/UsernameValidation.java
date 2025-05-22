package server.model.login.handlers;

import server.model.login.classes.ValidateUsers;

// TODO: this class should validate that the username is not already in use and that it exists in the database
public class UsernameValidation implements ValidateUsers {
    @Override
    public boolean validate (String inputText) {
        return true;
    }
    
}
