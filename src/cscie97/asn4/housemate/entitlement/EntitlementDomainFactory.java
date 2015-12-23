package cscie97.asn4.housemate.entitlement;

/**
 * Created by Ying on 10/29/15.
 */
public abstract class EntitlementDomainFactory {
    /**
     * Method to create user instance
     * @param id
     * @param name
     * @return
     */
    public abstract User createUser(String id, String name);

    /**
     * Method to create Permission instance
     * @param id
     * @param name
     * @param description
     * @return
     */
    public abstract Permission createPermission(String id, String name, String description);

    /**
     * Method to create Role instance
     * @param id
     * @param name
     * @param description
     * @return
     */
    public abstract Role createRoles(String id, String name, String description);

    /**
     * Method to create resourceRole instance
     * @param roleId
     * @param resourceName
     * @return
     */
    public  abstract ResourceRole createResourceRole(String roleId, String resourceName);
}
