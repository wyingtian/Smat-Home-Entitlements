package cscie97.asn4.housemate.entitlement;

/**
 * Factory for accessing the HouseMateController singleton instance
 * @author ying
 */
public class HouseMateEntitlementFactory {
    public static HouseMateEntitlementImpl getInstance(){
        return HouseMateEntitlementImpl.getInstance();
    }
}
