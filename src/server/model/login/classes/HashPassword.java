package server.model.login.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    
    /**
     * Hashes the provided password using the SHA-256 algorithm.
     *
     * @param password the raw password to hash
     * @return the hashed password as a hexadecimal string
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    protected static String hashPassword (String password) {
        try{
            // This initializes a MessageDigest instance using the SHA-256 hashing algorithm.
            // (MessageDigest is a class used to perform cryptographic hash functions.)
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            
            // The digest method computes the hash of the password string.
            // The password is first converted to a byte array using getBytes().
            // The result is a byte array (hash) containing the hashed value.
            byte [] hash = messageDigest.digest(password.getBytes());
            
            // A StringBuilder is created to construct the final hashed string in hexadecimal format.
            StringBuilder hexString = getStringBuilder(hash);
            
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
    
    private static StringBuilder getStringBuilder (byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        
        // Converts the byte b into a hexadecimal string.
        // %x: Converts the byte to a hexadecimal string.
        // 02: Ensures the output is at least 2 characters long, padding with a leading 0 if necessary (e.g., 0a instead of a).
        // Appends the resulting hexadecimal string to the StringBuilder (hexString),
        // which is used to construct the final hashed string.
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString;
    }
}
