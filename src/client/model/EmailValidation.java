package client.model;

public class EmailValidation implements UsersValidation {
    @Override
    public boolean validate (String inputText) {
        if (inputText == null) {
            System.out.println("Invalid email address");
            return false;
        }
        if (inputText.isEmpty()) {
            System.out.println("Invalid email address");
            return false;
        }
        if (inputText.isBlank()) {
            System.out.println("Invalid email address");
            return false;
        }
        if (inputText.length() < 11 || inputText.length() > 28) {
            System.out.println("Invalid email address");
            return false;
        }
        if(!inputText.contains("@")){
            System.out.println("Invalid email address");
            return false;
        }
        return true;
    }
}
