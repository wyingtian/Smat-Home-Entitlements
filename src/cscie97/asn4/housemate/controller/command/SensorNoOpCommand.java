package cscie97.asn4.housemate.controller.command;

import cscie97.asn4.housemate.model.IOTDevices.Sensor;
/**
 * when the sensor status changes but no action is performed
 * it only show the message of status changes
 * @author ying
 */


public class SensorNoOpCommand implements Command{
    Sensor theSensor;

    public SensorNoOpCommand(Sensor theSensor){
        this.theSensor = theSensor;
    }
    @Override
    public void execute() {
        System.out.println(theSensor.showStatus());
    }
}
