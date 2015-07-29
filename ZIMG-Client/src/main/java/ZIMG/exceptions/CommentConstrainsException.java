package ZIMG.exceptions;


public class CommentConstrainsException extends Exception {

    public CommentConstrainsException(final String comment, final int minLength){
        super("Your comment " +comment+ " is not an valid comment , Min length " + minLength);
    }

}
