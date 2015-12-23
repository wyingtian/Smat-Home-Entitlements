package cscie97.asn4.housemate.entitlement;

/**
 * EntitlementDomainClassVisitor is an interface provides
 * the methods to visit user, role, permission,and resource role.
 * Created by ying on 11/1/15.
 */
public interface EntitlementDomainClassVisitor {
    /**
     * Visit an user instance
     * @param user
     * @param entitlementID
     * @return
     */
    public boolean visitUser(User user,String entitlementID);

    /**
     * Visit a role instance
     * @param role
     * @return
     */
    public boolean visitRole(Role role);

    /**
     * Visit a resource role instance
     * @param resourceRole
     * @return
     */
    public boolean visitResourceRole(ResourceRole resourceRole);
    public boolean visitPermission(Permission permission);
}
