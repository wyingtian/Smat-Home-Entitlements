package cscie97.asn4.housemate.entitlement;

/**
 * Resource refers to one of the concrete instance such as house and room.
 * Created by ying on 10/29/15.
 */
public class Resource {
    String resourceName;
    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }
}
