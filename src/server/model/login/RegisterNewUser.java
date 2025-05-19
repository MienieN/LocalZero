package server.model.login;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterNewUser implements Serializable {
    
    // A unique identifier used during the deserialization process to verify that the sender and receiver of a
    // serialized object have loaded classes that are compatible with respect to serialization.
    // If the serialVersionUID of the class does not match the serialVersionUID of the serialized object, a
    // InvalidClassException is thrown. Setting it explicitly (e.g., 1L) ensures compatibility across different
    // versions of the class.
    private static final long  serialVersionUID = 1L;
    private String username;
    private String password;
    private String email;
    private String location;
    
    public RegisterNewUser(String username, String password, String email, String location) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
    }
    
    public String passwordHash() throws NoSuchAlgorithmException {
        // This initializes a MessageDigest instance using the SHA-256 hashing algorithm.
        // (MessageDigest is a class used to perform cryptographic hash functions.)
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        
        // The digest method computes the hash of the password string.
        // The password is first converted to a byte array using getBytes().
        // The result is a byte array (hash) containing the hashed value.
        byte [] hash = messageDigest.digest(password.getBytes());
        
        // A StringBuilder is created to construct the final hashed string in hexadecimal format.
        StringBuilder hexString = new StringBuilder();
        
        // Converts the byte b into a hexadecimal string.
        // %x: Converts the byte to a hexadecimal string.
        // 02: Ensures the output is at least 2 characters long, padding with a leading 0 if necessary (e.g., 0a instead of a).
        // Appends the resulting hexadecimal string to the StringBuilder (hexString),
        // which is used to construct the final hashed string.
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        
        return hexString.toString();
    }
    
    
    
 
    
}
