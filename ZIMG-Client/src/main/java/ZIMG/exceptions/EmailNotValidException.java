package ZIMG.exceptions;


public class EmailNotValidException extends Exception {

    public EmailNotValidException(final String email){
        super("The email" +email + " is not an valid email address");
    }

}
