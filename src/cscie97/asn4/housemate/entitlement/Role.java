package cscie97.asn4.housemate.entitlement;

import java.util.HashMap;

/**
 * Role is a subclass of EntitlementComponent,
 * it has a hash map that can contain any role or permission inside itself.
 * Permission is a subclass of EntitlementComponent, it defines a permission to access an appliance.
 * Created by ying on 10/29/15.
 */
public class Role extends EntitlementComponent implements VisitorAccepter {

    /**
     * hash map that can contain any role or permission inside itself
     */
    HashMap<String,EntitlementComponent> entitlementComponents ;

    public Role(String entitlementId, String entitlementName, String entitlementDescription) {
        super(entitlementId, entitlementName, entitlementDescription);
        this.entitlementComponents = new HashMap<String,EntitlementComponent>();
    }

    public HashMap<String, EntitlementComponent> getEntitlementComponents() {
        return entitlementComponents;
    }

    /**
     * Accept the algorithm visitor  class to implement the visitor pattern.
     * @param visitor
     */
    @Override
    public void acceptVisitor(EntitlementDomainClassVisitor visitor) {
        visitor.visitRole(this);
    }

    /**
     * Add one entitlement component to this role
     * @param entitlementComponent
     */
    public void add(EntitlementComponent entitlementComponent){
        entitlementComponents.put(entitlementComponent.entitlementId,entitlementComponent);
    }

    /**
     * remove one entitlement component to this role
     * @param entitlementComponent
     */
    public void remove(EntitlementComponent entitlementComponent){
        entitlementComponents.remove(entitlementComponent);
    }

    /**
     * Check if a role has one particular permission
     * @param entitlementComponentName
     * @return
     */
    public boolean hasPermissionOrRole(String entitlementComponentName){
        return entitlementComponents.containsKey(entitlementComponentName);
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

    public void showInfo(){
        System.out.println("ROLE_ID:           " + this.getId());
        System.out.println("ROLE_DESCRIPTION: " + this.getDescription());

    }
    public void print(){

        System.out.println("ENTITLEMENTS ASSOCIATED: " );
        for(EntitlementComponent en : entitlementComponents.values()){
            System.out.println("                   "+en.getName());
        }
        System.out.println("-------------------------------------------");
    }


}