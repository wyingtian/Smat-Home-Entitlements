package cscie97.asn4.housemate.entitlement;

/**
 * Resource Role is an associated class that associate Resource and Role.
 * Created by ying on 10/30/15.
 */
public class ResourceRole implements VisitorAccepter {
    String id;
    String description;
    Role role;
    Resource resource;

    public ResourceRole(Role role, String theResource) {
        this.role = role;
        this.resource = new Resource(theResource);
        this.id = resource.getResourceName() + "_"+role.getId();
        this.description = resource.getResourceName() + "_"+role.getId();
    }
    /**
     * Accept the algorithm visitor  class to implement the visitor pattern.
     * @param visitor
     */
    @Override
    public void acceptVisitor(EntitlementDomainClassVisitor visitor) {
        visitor.visitResourceRole(this);
    }
    public String getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

}
