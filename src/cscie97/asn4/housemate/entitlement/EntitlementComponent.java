package cscie97.asn4.housemate.entitlement;

/**
 * EntitlementComponent is an abstract class
 * that has two subclasses: Permission and Role. This is part of composite pattern.
 * Created by ying on 10/30/15.
 */
public abstract class EntitlementComponent {
    String entitlementId;
    String entitlementName;
    String entitlementDescription;

    public EntitlementComponent(String entitlementId, String entitlementName, String entitlementDescription) {
        this.entitlementId = entitlementId;
        this.entitlementName = entitlementName;
        this.entitlementDescription = entitlementDescription;
    }

    /**
     * If not overridden, throw unsupportedOperationException.
     * @param entitlementComponent
     */
    public  void add(EntitlementComponent entitlementComponent){
        throw new UnsupportedOperationException();
    }
    public  void acceptVisitor(EntitlementDomainClassVisitor visitor){throw new UnsupportedOperationException();}
    /**
     * If not overridden, throw unsupportedOperationException.
     * @param entitlementComponent
     */
    public void remove(EntitlementComponent entitlementComponent){
        throw new UnsupportedOperationException();
    }

    /**
     * If not overridden, throw unsupportedOperationException.
     * @param entitlementComponentName
     * @return
     */
    public boolean hasPermissionOrRole(String entitlementComponentName){
        throw new UnsupportedOperationException();
    }

    /**
     * If not overridden, throw unsupportedOperationException.
     * @return
     */
    public String getDescription(){
        throw new UnsupportedOperationException();
    }

    /**
     * If not overridden, throw unsupportedOperationException.
     * @return
     */
    public String getName(){
        throw new UnsupportedOperationException();
    }

    /**
     * If not overridden, throw unsupportedOperationException.
     */
    public void print(){
        throw new UnsupportedOperationException();
    }

}

