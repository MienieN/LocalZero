package client.model;

import shared.UserInformation;

/**
 Interface for user input validation.
 <p>
 Implementing classes should provide logic to validate user input,
 such as usernames or passwords.
 </p>
 */
public interface UsersValidation {
    /**
     Validates the given input text.
     
     @param inputText the input string to validate (e.g., username or password)
     
     @return {@code true} if the input is valid, {@code false} otherwise
     */
    boolean validate (UserInformation inputText);
    
    void setNext(UsersValidation next);
}
