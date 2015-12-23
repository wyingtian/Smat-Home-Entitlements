package cscie97.asn4.housemate.entitlement.entitlement.exception;

/**
 * Created by ying on 10/30/15.
 */
public class InvalidAccessTokenException extends  AuthenticationException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidAccessTokenException(String message) {
        super(message);
    }
    public void print(){
        System.out.println("InvalidAccessTokenException "+super.getMessage());
    }
}
