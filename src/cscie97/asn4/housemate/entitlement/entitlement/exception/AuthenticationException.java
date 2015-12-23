package cscie97.asn4.housemate.entitlement.entitlement.exception;

/**
 * Created by ying on 10/30/15.
 */
public class AuthenticationException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    int lineNum;
    public AuthenticationException(String message) {
        super(message);
    }

//    public AuthenticationException(String message,int lineNum) {
//        super(message);
//        this.lineNum = lineNum;
//    }
    public void print(){
        System.out.println( "AuthenticationException: "+super.getMessage());
    }
}
