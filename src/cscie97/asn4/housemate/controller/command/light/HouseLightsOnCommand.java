package cscie97.asn4.housemate.controller.command.light;

import cscie97.asn4.housemate.controller.command.Command;
import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.model.ServiceInterface;
import cscie97.asn4.housemate.model.IOTDevices.Appliance;

/**
 * Created by ying on 10/19/15.
 */
public class HouseLightsOnCommand implements Command {
    String houseName;
    ServiceInterface model;
    AccessToken authToken;
    public HouseLightsOnCommand(ServiceInterface model, String houseName, AccessToken authToken) {
        this.model = model;
        this.houseName = houseName;
        this.authToken = authToken;
    }

    @Override
    public void execute() {
        for(Appliance light :model.findApplianceInHouse(houseName, "light", authToken)){
            light.changeStatus("power","on");
        }
    }
}
