package cscie97.asn4.housemate.model;

import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AccessDeniedException;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AuthenticationException;

/**
 * Created by ying on 10/30/15.
 */
public class CommandParser {
    /**
     * method for taking a command, execute if passed the general format test
     * @param lineNum
     * @param command
     * @param auth_token
     * @param lineNum
     */
    public static void executeCommand(String command, AccessToken auth_token, int lineNum)  {
        String checked[];
        command = command.trim();
        String[] tokens = null;
        tokens = command.split("\\s+");
        checked = tokens;
        if (checked != null) {
            try {
                exeCheckedCommand(checked, auth_token);
            }catch (AccessDeniedException e) {
                System.out.println("Exception #" +lineNum +" line: ");
                e.print();
            } catch (AuthenticationException e) {
                System.out.print("Exception #" +lineNum +" line: ");
                e.print();
            }
        } else {
            System.out.println("Exception #" +lineNum +" line: ");
        }
    }
    /**
     * execute the command
     *
     * @param tokens
     */
    public static void exeCheckedCommand(String[] tokens, AccessToken auth_token) throws AuthenticationException {

        if (tokens[0].equals("define") && tokens[1].equals("house")) {
            if (tokens.length < 3) {
                System.out.println("Please add the name of the house");
            } else if (tokens.length > 3) {
                System.out.println("The name should not contain space");
            } else {
                HouseMateModelFactory.getInstance().defineHouse(tokens[2], auth_token);
            }
        }

        // This is for the command
        // "define room <room_name> floor <floor> type<room_type> house <house_name>"

        else if (tokens[0].equals("define") && tokens[1].equals("room")) {

            if (tokens.length == 9 && tokens[3].equals("floor")
                    && tokens[5].equals("type") && tokens[7].equals("house")){
                HouseMateModel.getInstance().defineRoom(tokens[2],tokens[4],tokens[6],tokens[8], auth_token);
            }else {
                System.out
                        .println("invalid input, Please try to define the room again");
            }
        }
        // This is for the command
        // "define occupants <occupant_name> type <occupant_type>"
        else if (tokens[0].equals("define") && tokens[1].equals("occupant")) {
            // check the format of the input, the length has to be 3;
            if (tokens.length < 5) {
                System.out.println("Please add the all the info of the occupant");
            } else if (tokens.length > 5) {
                System.out
                        .println("Please define the name and type of the occupant");
            } else {
                HouseMateModel.getInstance().defineOccupant(tokens[2],tokens[4], auth_token);
            }
        }
        // This is for the command
        // "add occupant <occupant_name> to_house <house_name>"
        else if (tokens[0].equals("add") && tokens[1].equals("occupant")
                && tokens[3].equals("to_house")) {
            HouseMateModel.getInstance().addOccupant2House(tokens[2], tokens[4], auth_token);
        }

        // This is for the command
        // "define sensor <name> type <sensor_type> room <house_name>:<room_name>"
        else if (tokens[0].equals("define") && tokens[1].equals("sensor")) {
            // if the length is not valid
            if (tokens.length < 7) {
                System.out.println("Need more info to define sensor");
            } else if (tokens.length > 7) {
                System.out.println("Please check your define sensor format");
            }else {
                HouseMateModel.getInstance().defineSensor(tokens[2],tokens[4],tokens[6], auth_token);
            }
        }
        // This is for the command
        // "define appliance <name> type <appliance_type> room <house_name>:<room_name>"
        else if (tokens[0].equals("define") && tokens[1].equals("appliance")) {
            // if the length is not valid
            if (tokens.length < 7) {
                System.out.println("Need more info to define appliance");
            } else if (tokens.length > 7) {
                System.out
                        .println("Please check your define appliance statement format");
            } else{

                HouseMateModel.getInstance().defineAppliance(tokens[2],tokens[4],tokens[6], auth_token);
            }
        }
        // This is the command for set appliance house_name:room_name name

        else if (tokens[0].equals("set")
                && (tokens[1].equals("sensor")&& (tokens.length > 6) )
                ) {
            HouseMateModel.getInstance().setSensor(tokens[2],tokens[4],tokens[6],tokens, auth_token);
        } else if (tokens[0].equals("set")
                && (tokens[1].equals("appliance") && tokens.length > 6)
                ) {

            HouseMateModel.getInstance().setApplianceStatus(tokens[2],tokens[4],tokens[6], auth_token);
        }
        else if (tokens[0].equals("show")
                && (tokens[1].equals("sensor") )
                && (tokens.length > 2 && tokens.length < 6)) {
            HouseMateModel.getInstance().showSensor(tokens[2], auth_token);
        }else if (tokens[0].equals("show")
                && tokens[1].equals("appliance")
                && tokens.length == 5) {

            HouseMateModel.getInstance().showApplianceStatus(tokens[2], tokens[4], auth_token);
        }else if (tokens[0].equals("show")
                && tokens[1].equals("appliance")
                && (tokens.length == 3)) {
            HouseMateModel.getInstance().showAllApplianceStatus(tokens[2], auth_token);
        }
        else if (tokens.length == 4 && tokens[0].equals("show")
                && tokens[1].equals("configuration")
                && tokens[2].equals("house")) {
            HouseMateModel.getInstance().showConfigHouse(tokens[3], auth_token);
        } else if (tokens.length == 4 && tokens[0].equals("show")
                && tokens[1].equals("configuration")
                && tokens[2].equals("room")) {
            HouseMateModel.getInstance().showConfigRoom(tokens[3], auth_token);
        } else if (tokens.length == 2 && tokens[0].equals("show")
                && tokens[1].equals("configuration")) {
            HouseMateModel.getInstance().showConfigAllHouse(auth_token);
        } else {
            System.out.println("invalid command input");
        }

    }
}

//    /**
//     * method for basic format check of the command
//     *
//     * @param command
//     * @return tokenized command
//     */
//    public static String[] checkCommand(String command) {
//        // show the input String command
//       // System.out.println("");
//      //  System.out.println("input command: " + command);
//      //  System.out.println("*********************************************");
//
//        command = command.trim();
//        String[] tokens = null;
//        tokens = command.split("\\s+");
//
//        if (CommandKeyWord.contains(tokens[0]) ) {
//            return tokens;
//        } else
//            return null;
//    }

