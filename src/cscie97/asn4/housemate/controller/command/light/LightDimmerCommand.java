package cscie97.asn4.housemate.controller.command.light;

import cscie97.asn4.housemate.controller.command.Command;
import cscie97.asn4.housemate.model.IOTDevices.Light;

/**
 * Created by ying on 10/16/15.
 */
public class LightDimmerCommand implements Command {
    Light light;

    public LightDimmerCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.changeStatus("intensity","dimmer");
    }
}