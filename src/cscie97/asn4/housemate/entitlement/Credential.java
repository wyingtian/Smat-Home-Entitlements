package cscie97.asn4.housemate.entitlement;

/**
 * Credential is a class that contains username and password or voiceprint.
 * Created by ying on 10/29/15.
 */
public class Credential {
    String id;
    String value;
    String type;

    /**
     * t the value of the credential
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the type of the credential, voice print or password
     * @return
     */
    public String getType() {
        return type;
    }
}
