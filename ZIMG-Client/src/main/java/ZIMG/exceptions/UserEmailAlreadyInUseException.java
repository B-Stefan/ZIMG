package ZIMG.exceptions;

import ZIMG.models.User;

import java.util.List;


public class UserEmailAlreadyInUseException extends Exception {
    /**
     * User name that exist at least twice user
     */
    private final String userEmail;


    public UserEmailAlreadyInUseException(final String userEmail){
        super("For "  + userEmail + " exist a user ");
        this.userEmail = userEmail;
    }
    public String getEmail() {
        return userEmail;
    }

}
