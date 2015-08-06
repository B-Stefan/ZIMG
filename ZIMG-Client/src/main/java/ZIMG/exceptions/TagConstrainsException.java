package ZIMG.exceptions;


public class TagConstrainsException extends Exception {

    public TagConstrainsException(final String tag, final int minLength){
        super("Your tag " +tag+ " is not an valid tag, Min length " + minLength);
    }

}
