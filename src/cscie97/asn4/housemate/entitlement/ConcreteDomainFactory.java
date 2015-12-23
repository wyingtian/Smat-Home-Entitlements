package cscie97.asn4.housemate.entitlement;

/**
 * concrete domain factory implements EntitlementDomainFactory
 * Created by ying on 10/29/15.
 */

public class ConcreteDomainFactory extends EntitlementDomainFactory{
    /**
     * Method to create user instance
     * @param id
     * @param name
     * @return
     */
    public  User createUser(String id, String name) {
        return new User(id,name);
    }

    /**
     * Method to create Permission instance
     * @param id
     * @param name
     * @param description
     * @return
     */
    public  Permission createPermission(String id, String name, String description) {
        return new Permission(id,name,description);
    }

    /**
     * Method to create Role instance
     * @param id
     * @param name
     * @param description
     * @return
     */
    public  Role createRoles(String id , String name, String description) {
        return new Role(id,name,description);
    }

    /**
     * Method to create resourceRole instance
     * @param roleId
     * @param resourceName
     * @return
     */
    public  ResourceRole createResourceRole(String roleId, String resourceName){
        if(HouseMateEntitlementFactory.getInstance().entitlementMap.containsKey(roleId)){
            return new ResourceRole((Role)HouseMateEntitlementFactory.getInstance().entitlementMap.get(roleId),resourceName);
        }else return null;
    }
}
