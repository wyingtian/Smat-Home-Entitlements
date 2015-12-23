package cscie97.asn4.housemate.entitlement;

/**
 * VisitorAccepter is an interface that provides an
 * acceptVisitor method for Entitlement domain classes.
 * Created by ying on 11/1/15.
 */
public interface VisitorAccepter {
    /**
     * Accept the algorithm visitor  class to implement the visitor pattern.
     * @param visitor
     */
    public void acceptVisitor(EntitlementDomainClassVisitor visitor);
}
