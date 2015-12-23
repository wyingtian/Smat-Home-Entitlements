package cscie97.asn4.housemate.controller.command.Thermostat;

import cscie97.asn4.housemate.controller.command.Command;
import cscie97.asn4.housemate.model.IOTDevices.Thermostat;

/**
 * Created by ying on 10/16/15.
 */
public class ThermostatWarmerCommand implements Command {
    Thermostat theThermostat;

    public ThermostatWarmerCommand(Thermostat thermostat) {
        this.theThermostat = thermostat;
    }

    @Override
    public void execute() {
        theThermostat.changeStatus("temperature","warmer");
    }
}