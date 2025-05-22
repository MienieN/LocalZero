package client.model;

public class PasswordValidation implements ValidateUsers {
    
    @Override
    public boolean validate (String inputText) {
        if (inputText == null) {
            System.out.println("Invalid Password");
            return false;
        }
        if (inputText.isEmpty()) {
            System.out.println("Invalid Password");
            return false;
        }
        if (inputText.isBlank()) {
            System.out.println("Invalid Password");
            return false;
        }
        if (inputText.length() < 6 || inputText.length() > 16) {
            System.out.println("Invalid Password");
            return false;
        }
        return true;
    }
}
    

