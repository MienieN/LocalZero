package client.model;

public class UsernameValidation implements UsersValidation {
    @Override
    public boolean validate (String inputText) {
        if (inputText == null) {
            System.out.println("Invalid username");
            return false;
        }
        if (inputText.isEmpty()) {
            System.out.println("Invalid username");
            return false;
        }
        if (inputText.isBlank()) {
            System.out.println("Invalid username");
            return false;
        }
        if (inputText.length() < 3 || inputText.length() > 8) {
            System.out.println("Invalid username");
            return false;
        }
        return true;
    }
    
}
