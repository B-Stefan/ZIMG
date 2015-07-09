package ZIMG.exceptions;

import ZIMG.models.User;

import java.util.List;


public class MultipleUserForUserNameExistException extends Exception {
    /**
     * User name that exist at least twice user
     */
    private final String userName;

    /**
     * List of users with the @userName prop
     */
    private final List<User> userList;

    public MultipleUserForUserNameExistException(final String userName, final List<User> userList){
        super("For "  + userName + " exist multiple users " + userList.toString());
        this.userName = userName;
        this.userList = userList;
    }
    public List<User> getUserList() {
        return userList;
    }

    public String getUserName() {
        return userName;
    }
}
