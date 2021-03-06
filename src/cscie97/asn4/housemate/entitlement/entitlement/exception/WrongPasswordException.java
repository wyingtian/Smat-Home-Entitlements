package cscie97.asn4.housemate.entitlement.entitlement.exception;

/**
 * Created by ying on 10/31/15.
 */
public class WrongPasswordException extends WrongCredentialException {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public WrongPasswordException(String message) {
        super(message);
    }

    public void print(){
        System.out.println("WrongPasswordException: "+super.getMessage() + " does not match ");
    }
}
