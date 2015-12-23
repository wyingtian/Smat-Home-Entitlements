package cscie97.asn4.housemate.controller.command;

import cscie97.asn4.housemate.controller.command.Thermostat.RoomThermostatCoolerCommand;
import cscie97.asn4.housemate.controller.command.Thermostat.RoomThermostatWarmerCommand;
import cscie97.asn4.housemate.controller.command.light.RoomLightsDimmerCommand;
import cscie97.asn4.housemate.controller.command.light.RoomLightsOffCommand;
import cscie97.asn4.housemate.controller.command.light.RoomLightsOnCommand;
import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.model.HouseMateModelFactory;
import cscie97.asn4.housemate.model.ServiceInterface;
import cscie97.asn4.housemate.model.IOTDevices.Camera;


/**
 * CameraCommand is executed when camera status changes
 * @author ying
 */

public class CameraCommand implements Command {
    String occStatus;
    String occ;
    Camera cam;
    ServiceInterface model;

    public CameraCommand(String occStatus, String occ, Camera cam) {
        this.occStatus = occStatus;
        this.occ = occ;
        this.cam = cam;
        model = HouseMateModelFactory.getInstance();
    }

    @Override
    public void execute() {
        String location = cam.getLocationPair();
        String tripleString = occ + " is_in " + location;

        if (!model.getAllOccupantMap().containsKey(occ)) {
            System.out.println("Unknown person " + occ + " detected");
        } else {
            if (occStatus.equals("OCCUPANT_DETECTED")) {
                System.out.println(occ + " entered " + location);
                model.getImporter().importTripleLine(tripleString);
                detectedProcedure(location, null);
            } else if (occStatus.equals("OCCUPANT_LEAVING")) {
                System.out.println(occ + " left " + location);
                model.getKnowledgeGraph().removeTriples(tripleString);
                leavingProcedure(location, null);
            } else if (occStatus.equals("OCCUPANT_ACTIVE")) {
                System.out.println(occ + " is " + "active");
                model.getKnowledgeGraph().removeTriples(occ + " is " + "sleeping");
                model.getImporter().importTripleLine(occ + " is " + "active");
            } else if (occStatus.equals("OCCUPANT_INACTIVE")) {
                System.out.println(occ + " is " + "sleeping");
                model.getKnowledgeGraph().removeTriples(occ + " is " + "active");
                model.getImporter().importTripleLine(occ + " is " + "sleeping");
                sleepProcedure(location, null);
            }
        }
    }

    /**
     * actions when occupant is asleep
     * @param roomLocationPair
     * @param authToken
     */
    public void sleepProcedure(String roomLocationPair,AccessToken authToken){
        Command com1 = new RoomLightsDimmerCommand(model.findApplianceByType(roomLocationPair, "light", authToken));
        com1.execute();
    }

    /**
     * actions when occupant enters the room
     * @param roomLocationPair
     * @param authToken
     */
    public void detectedProcedure(String roomLocationPair,AccessToken authToken){
        Command com1 = new RoomLightsOnCommand(model.findApplianceByType(roomLocationPair, "light", authToken));
        Command com2 = new RoomThermostatWarmerCommand(model.findApplianceByType(roomLocationPair, "thermostat", authToken));
        com1.execute();
        com2.execute();
    }

    /**
     * actions when occupant leaves the room
     * @param roomLocationPair
     * @param authToken
     */
    public void leavingProcedure(String roomLocationPair,AccessToken authToken){
        Command com1 = new RoomLightsOffCommand(model.findApplianceByType(roomLocationPair, "light", authToken));
        Command com2 = new RoomThermostatCoolerCommand(model.findApplianceByType(roomLocationPair, "thermostat", authToken));
        com1.execute();
        com2.execute();
    }
}