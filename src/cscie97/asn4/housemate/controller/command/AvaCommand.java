package cscie97.asn4.housemate.controller.command;

import cscie97.asn4.housemate.controller.command.door.RoomDoorCloseCommand;
import cscie97.asn4.housemate.controller.command.door.RoomDoorOpenCommand;
import cscie97.asn4.housemate.controller.command.light.RoomLightsOffCommand;
import cscie97.asn4.housemate.controller.command.light.RoomLightsOnCommand;
import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.entitlement.HouseMateEntitlementImpl;
import cscie97.asn4.housemate.entitlement.HouseMateEntitlementFactory;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AccessDeniedException;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AuthenticationException;
import cscie97.asn4.housemate.model.HouseMateModel;
import cscie97.asn4.housemate.model.HouseMateModelFactory;
import cscie97.asn4.housemate.model.ServiceInterface;
import cscie97.asn4.housemate.model.IOTDevices.Appliance;
import cscie97.asn4.housemate.model.IOTDevices.Ava;

import java.util.List;

/**
 * AvaCommand is executed when ava status changes
 * @author ying
 */
public class AvaCommand implements Command {
    HouseMateEntitlementImpl ENTITLEMENT = HouseMateEntitlementFactory.getInstance();
    String stimulus;
    Ava ava;
    ServiceInterface model;
    AccessToken accessToken;
    String person;
    public AvaCommand(String stimulus, Ava ava, AccessToken accessToken, String person) {
        this.stimulus = stimulus;
        this.ava = ava;
        model = HouseMateModelFactory.getInstance();
        this.person = person;
        this.accessToken = newToken();

    }

   private AccessToken newToken(){
       String voicePrint = "--"+person+"--";
       return ENTITLEMENT.logIn(person,"voice_print",voicePrint);
   }

    @Override
    public void execute() {
            String[] tokens = stimulus.split(" ");
            if(stimulus.equals("lights on")){
                ava.getLocationPair();
                boolean hasAccess = false;
                String entitlementID = "control_light";
                try {
                    hasAccess = ENTITLEMENT.checkAccess(accessToken,entitlementID);
                } catch (AuthenticationException e) {
                    e.print();
                }
                if (hasAccess) {
                    Command com = new RoomLightsOnCommand(model.findApplianceByType(ava.getLocationPair(), "light", accessToken));
                    com.execute();
                }else{
                    try {
                        throw new AccessDeniedException("need " + entitlementID + " role to access"+" lights on " );
                    } catch (AccessDeniedException e) {
                        e.print();
                    }
                }
            }else if(stimulus.equals("lights off")){
                ava.getLocationPair();
                boolean hasAccess = false;
                String entitlementID = "control_light";
                try {
                    hasAccess = ENTITLEMENT.checkAccess(accessToken,entitlementID);
                } catch (AuthenticationException e) {
                    e.print();
                }
                if (hasAccess) {
                Command com = new RoomLightsOffCommand(model.findApplianceByType(ava.getLocationPair(), "light", accessToken));
                com.execute();
                }else {
                    try {
                        throw new AccessDeniedException("need " + entitlementID + " role to access" + " lights off ");
                    } catch (AccessDeniedException e) {
                        e.print();
                    }
                }
            }else if(stimulus.equals("open door")){
                boolean hasAccess = false;
                String entitlementID = "control_door";
                try {
                    hasAccess = ENTITLEMENT.checkAccess(accessToken,entitlementID);
                } catch (AuthenticationException e) {
                    e.print();
                }
                if (hasAccess) {
                Command com = new RoomDoorOpenCommand(model.findApplianceByType(ava.getLocationPair(), "door", accessToken));
                com.execute();
                }else {
                    try {
                        throw new AccessDeniedException("need " + entitlementID + " role to access" + " open door ");
                        } catch (AccessDeniedException e) {
                             e.print();
                }
            }
            }else if(stimulus.equals("close door")){
                boolean hasAccess = false;
                String entitlementID = "control_door";
                try {
                    hasAccess = ENTITLEMENT.checkAccess(accessToken,entitlementID);
                } catch (AuthenticationException e) {
                    e.print();
                }
                if (hasAccess) {
                Command com = new RoomDoorCloseCommand(model.findApplianceByType(ava.getLocationPair(), "door", accessToken));
                com.execute();
                }else {
                    try {
                        throw new AccessDeniedException("need " + entitlementID + " role to access" + " open door ");
                    } catch (AccessDeniedException e) {
                        e.print();
                    }
                }
            }else if(tokens.length == 3 && tokens[0].equals("where") && tokens[1].equals("is")){
                model.getQueryEngine().executeQuery(tokens[2] + " is_in " + "?");
            }



            else if(tokens.length == 3 && HouseMateModel.isApplianceType(tokens[0])){
                List<Appliance> list = model.findApplianceByType(ava.getLocationPair(),tokens[0] , accessToken);
                for(Appliance app:list){
                    app.changeStatus(tokens[1],tokens[2]);
                    app.showStatus(tokens[1]);
                }
            }

            else if(tokens.length == 3 && (tokens[0].equals("?")||tokens[1].equals("?")||tokens[2].equals("?"))){
                model.getQueryEngine().executeQuery(stimulus);
            }
    }
}
