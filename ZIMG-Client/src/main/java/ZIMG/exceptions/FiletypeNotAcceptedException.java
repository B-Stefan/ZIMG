package ZIMG.exceptions;

public class FiletypeNotAcceptedException extends Exception {
    private String notAcceptedType;

    public FiletypeNotAcceptedException(String notAcceptedType) {
        super("No accepted Filetype");

        this.notAcceptedType = notAcceptedType;
    }

    public String getNotAcceptedType() {
        return notAcceptedType;
    }
}
