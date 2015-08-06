package ZIMG.exceptions;


public class UserPasswordConstrainsException extends Exception {

    public UserPasswordConstrainsException(final String password, int minLength){
        super("The password not fulfil the constraints, Minimum length " + minLength);
    }

}
