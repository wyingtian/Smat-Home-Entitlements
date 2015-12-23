package cscie97.asn4.housemate.controller;
import cscie97.asn4.housemate.controller.command.CommandFactory;
import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.entitlement.HouseMateEntitlementImpl;
import cscie97.asn4.housemate.entitlement.HouseMateEntitlementFactory;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AccessDeniedException;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AuthenticationException;
import cscie97.asn4.housemate.model.IOTDevices.Sensor;

import java.util.Observable;

/**
 *The House Mate Controller Service use the interface of the House Mate Model Service
 *to monitor the status the IOT devices.
 * @author ying
 */
public class HouseMateController implements ModelObserverInterface {
    HouseMateEntitlementImpl ENTITLEMENT = HouseMateEntitlementFactory.getInstance();

    private  static HouseMateController theController = null;
    private HouseMateController(){

    }
    /**
     * HouseMateController is a singleton, this method
     * return the instance of itself
     * @return  the instance of itself
     */
    public static  HouseMateController getInstance(){
        if (theController == null) {
            theController = new HouseMateController();
        }
        return theController;
    }

    /**
     * House Mate Controller is an observer implements ModelObserverInterface,
     * this is to update a sensor settings when it observed changes.
     * @param theSensor
     * @param statusName
     * @param value
     * @param tokens
     * @param authToken
     */
    public void updateSensor(Sensor theSensor, String statusName, String value, String[] tokens, AccessToken authToken) throws AuthenticationException {
        boolean hasAccess = false;
        String entitlementID = "user_admin";
        hasAccess = ENTITLEMENT.checkAccess(authToken,entitlementID);
        if (hasAccess) {
            String sensorType;
            sensorType = theSensor.getType();

            CommandFactory.createSensorCommand(theSensor, sensorType, statusName, value, tokens, authToken).execute();
        }else{
            throw new AccessDeniedException("need " + entitlementID + " role to access"+" update sensor status " );
        }
    }

    /**
     * House Mate Controller is an observer implements ModelObserverInterface,
     * this is to update a appliance settings and take actions
     * based on the trigger when it observed changes.
     * @param obs
     * @param arg
     */
    public void updateAppliance(Observable obs, String arg ) {
           CommandFactory.createApplianceCommand(obs,arg).execute();
    }

}

