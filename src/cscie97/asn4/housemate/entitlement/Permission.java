package cscie97.asn4.housemate.entitlement;

/**
 * Permission is a subclass of EntitlementComponent,
 * it defines a permission to access an appliance.
 * Created by ying on 10/29/15.
 */
public class Permission extends EntitlementComponent{

    public Permission(String entitlementId, String entitlementName, String entitlementDescription) {
        super(entitlementId, entitlementName, entitlementDescription);
    }
    public void acceptVisitor(EntitlementDomainClassVisitor visitor) {
        visitor.visitPermission(this);
    }
    public String getDescription(){
        return entitlementDescription;
    }
    public String getName(){
        return entitlementName;
    }
    public String getId(){
        return entitlementId;
    }
    public void print(){
        String format = "%-25s%s%n";
        System.out.printf(format,this.getId(),this.getDescription());
    }
}
