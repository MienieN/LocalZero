package server.model.session;

import shared.User;

/**
 * Manages the current user session.
 * <p>
 * Provides static methods to log in, log out, and retrieve the current user.
 * The session is tracked using a static {@code User} field.
 * </p>
 */
public class UserSession {
    
    private static User currentUser = null; // The currently logged-in user. This is null if no user is logged in.
    
    /**
     * Logs in the specified user by setting the current session user.
     *
     * @param user the user to log in
     */
    public static void loginUser(User user) {
        currentUser = user;
    }
    
    /**
     * Logs out the current user and clears the session.
     * Prints a message indicating the logout status.
     */
    public static void logoutUser() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUsername() + " logged out.");
            currentUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
    
    /**
     * Returns the currently logged-in user, or {@code null} if no user is logged in.
     *
     * @return the current user or {@code null}
     */
    public static User getCurrentUser() {
        return currentUser;
    }
}
