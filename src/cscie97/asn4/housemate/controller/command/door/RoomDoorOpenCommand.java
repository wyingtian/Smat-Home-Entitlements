package cscie97.asn4.housemate.controller.command.door;

import cscie97.asn4.housemate.controller.command.Command;
import cscie97.asn4.housemate.model.IOTDevices.Appliance;
import cscie97.asn4.housemate.model.IOTDevices.Door;

import java.util.List;

/**
 * Created by ying on 10/19/15.
 */
public class RoomDoorOpenCommand implements Command {
    List<Appliance> list;
    public RoomDoorOpenCommand(List<Appliance> list){
        this.list = list;
    }
    @Override
    public void execute() {

        if(list.isEmpty()){
            System.out.println("no door in this room");
            return;
        }
        for(Appliance app: list){
            DoorOpenCommand com = new DoorOpenCommand((Door)app);
            com.execute();
        }
    }

}
