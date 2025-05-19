package src.server.model.login;

public class ValidatePassword implements ValidateUsers {
    
    @Override
    public boolean validate (String inputText) {
        if (inputText.length() < 3) {
            return false;
        } else {
            return true;
        }
    }
    
}
