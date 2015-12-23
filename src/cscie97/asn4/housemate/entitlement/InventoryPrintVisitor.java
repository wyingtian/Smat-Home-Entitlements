package cscie97.asn4.housemate.entitlement;

/**
 * InventoryPrintVisitor is an algorithm class that implements
 * EntitlementDomainClassVisitor to traverse a user, role, permission and resource role.
 * Created by ying on 11/1/15.
 */

public class InventoryPrintVisitor implements EntitlementDomainClassVisitor {
    /**
     * traverse an user instance
     * @param user
     */
    @Override
    public boolean visitUser(User user,String entitlementID) {
        user.showInfo();
        return true;
    }
    public boolean visitUser(User user) {
        user.showInfo();
        return true;
    }
    /**
     * traverse a role instance
     * @param role
     */
    @Override
    public boolean visitRole(Role role) {
        role.showInfo();
        role.print();
        return true;
    }

    /**
     * traverse a resource role instance
     * @param resourceRole
     */
    @Override
    public boolean visitResourceRole(ResourceRole resourceRole) {
        System.out.println(resourceRole.getId());
        return true;
    }

    @Override
    public boolean visitPermission(Permission permission) {
        permission.print();
        return false;
    }

}
