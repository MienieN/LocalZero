package client.model;

public class LocationValidation implements ValidateUsers {
    
    @Override
    public boolean validate (String inputText) {
        if (inputText == null) {
            System.out.println("Invalid Location");
            return false;
        }
        if (inputText.isEmpty()) {
            System.out.println("Invalid Location");
            return false;
        }
        if (inputText.isBlank()) {
            System.out.println("Invalid Location");
            return false;
        }
        if (inputText.length() < 3 || inputText.length() > 16) {
            System.out.println("Invalid Location");
            return false;
        }
        if (!inputText.matches("[a-zA-Z ]*")) {
            System.out.println("Invalid Location");
            return false;
        }
        return true;
    }
}
