package cscie97.asn4.housemate.controller.command;

import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.model.HouseMateModelFactory;
import cscie97.asn4.housemate.model.ServiceInterface;
import cscie97.asn4.housemate.model.IOTDevices.Ava;
import cscie97.asn4.housemate.model.IOTDevices.Refrigerator;
import cscie97.asn4.housemate.model.IOTDevices.Sensor;

import java.util.Scanner;

/**
 * the command for when the beer count is low
 * @author ying
 */
public class BeerCountLowCommand implements Command {
    Refrigerator refrigerator;
    ServiceInterface model;

    public BeerCountLowCommand(Refrigerator refrigerator){
        this.refrigerator = refrigerator;
        model = HouseMateModelFactory.getInstance();
    }
    @Override
    public void execute() {
        System.out.println("beer Count has changed");
        avaInRoomSpeak(refrigerator.getLocationPair(), "Would you like more beer?", null);
        beerRequestPrompt();
    }

    /**
     * use the ava in the room that same as the room the fridge is in to speak
     * @param avaLocation
     * @param broadCastMessage
     * @param authToken
     */
    public void avaInRoomSpeak(String avaLocation,String broadCastMessage,AccessToken authToken){
        for(Sensor sen :model.findSensorInRoom(avaLocation, "Ava", authToken)) {
            ((Ava)sen).speak(broadCastMessage);
        };

    }

    /**
     * ask if the occupant want more beer
     */
    public void beerRequestPrompt(){
        Scanner in = new Scanner(System.in);
        String s;
        System.out.println("Enter yes or no");
        s = in.nextLine();
        if(s.equals("yes")){
            System.out.println("Order email has been sent");

        }else if(s.equals("no")){
            System.out.println("Ok, no beer for you :(");
        }else beerRequestPrompt();
    }
}
