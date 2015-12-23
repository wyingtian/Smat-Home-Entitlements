package cscie97.asn4.housemate.entitlement;

/**
 *
 *CheckAccessVisitor is an algorithm class that implements EntitlementDomainClassVisitor
 *to check access of a user, role, permission and resource role.
 * Created by ying on 11/1/15.
 */

public class CheckAccessVisitor implements EntitlementDomainClassVisitor {
    /**
     * Check access of an user instance
     * @param theUser
     * @param entitlementID
     * @return
     */
    @Override
    public boolean visitUser(User theUser,String entitlementID) {
        if (theUser.getRole().getEntitlementComponents().containsKey(entitlementID) ||
                theUser.getRole().getId().equals(entitlementID)) {
            return true;
        }else
            return false;
    }

    /**
     * Check access of a role instance
     * @param role
     * @return
     */
    @Override
    public boolean visitRole(Role role) {
        return true;
    }

    /**
     * Check access of a resource role instance
     * @param resourceRole
     * @return
     */
    @Override
    public boolean visitResourceRole(ResourceRole resourceRole) {
        return true;
    }

    @Override
    public boolean visitPermission(Permission permission) {
        return false;
    }
}
