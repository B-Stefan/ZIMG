package ZIMG.exceptions;

public class SpringRuntimeExceptionForUser extends RuntimeException {

    public enum TYPE {
        WARNING,
        ERROR,
    }
    public final static String DEFAULT_JSP_NAME = "error";
    private final TYPE type;
    private final String jspPageName;
    public SpringRuntimeExceptionForUser(Throwable e){
        super(e);
        this.type = TYPE.ERROR;
        this.jspPageName = DEFAULT_JSP_NAME;
    }
    public SpringRuntimeExceptionForUser(Throwable e, TYPE type){
        super(e);
        this.type = type;
        this.jspPageName = DEFAULT_JSP_NAME;
    }
    public SpringRuntimeExceptionForUser(Throwable e, TYPE type, String useJSPPage){
        super(e);
        this.type = type;
        this.jspPageName = useJSPPage;
    }

    public SpringRuntimeExceptionForUser(String e){
        super(e);
        this.type = TYPE.ERROR;
        this.jspPageName = DEFAULT_JSP_NAME;
    }

    public SpringRuntimeExceptionForUser(String e, TYPE type){
        super(e);
        this.type = type;
        this.jspPageName = DEFAULT_JSP_NAME;
    }
    public SpringRuntimeExceptionForUser(String e, TYPE type, String useJSPPage){
        super(e);
        this.type = type;
        this.jspPageName = useJSPPage;
    }
    public String getMsg(){
        return this.getMessage();
    }
    public TYPE getType(){
        return this.type;
    }
    public String getJspPageName(){
        return this.jspPageName;
    }
}
