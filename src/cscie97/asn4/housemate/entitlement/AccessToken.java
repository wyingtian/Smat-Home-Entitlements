package cscie97.asn4.housemate.entitlement;

/**
 * AccessToken class has an id, state and expiration time.
 * The state indicates if it is active or expired.
 * The access token can timeout due to inactivity.
 * Created by ying on 10/30/15.
 */
public class AccessToken {
    String id;
    String state;
    int expirationTime;
    public AccessToken(String id) {
        this.id = id;
        expirationTime = 100;  //set default expirationTime 100;
        state = "valid";     //default state is valid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
        if(expirationTime < 0){
            setState("false");
        }
    }
}
