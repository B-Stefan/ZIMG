package ZIMG.exceptions;


public class UserNameConstrainsException extends Exception {

    public UserNameConstrainsException(final String userName, int minLength){
        super("The "+userName+" not fulfil the constraints, Minimum length " + minLength);
    }

}
