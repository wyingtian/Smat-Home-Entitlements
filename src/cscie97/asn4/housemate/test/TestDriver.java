package cscie97.asn4.housemate.test;

import cscie97.asn4.housemate.entitlement.HouseMateEntitlementFactory;
import cscie97.asn4.housemate.model.HouseMateCLI;
import cscie97.asn4.housemate.model.HouseMateModelFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ying on 10/25/15.
 */
public class TestDriver {

    public static void main(String args[]){
        String houseSetupFile;
        String authFile;
        if (args.length == 0){
            System.out.println("Please add Auth_data first and HouseSetup_Data second");
        }else if (args.length == 1){
            System.out.println("Please add HouseSetup_Data");
        }else {

            authFile = args[0];
            houseSetupFile = args[1];
            try {
                HouseMateEntitlementFactory.getInstance().importConfigFile(authFile);
                HouseMateEntitlementFactory.getInstance().showAuthFileResult();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + authFile);
            } catch (IOException e) {
                System.out.println("Unable to read file: " + authFile);
            }
            try {
                HouseMateCLI.importConfigFile(houseSetupFile);
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + houseSetupFile);
            } catch (IOException e) {
                System.out.println("Unable to read file: " + houseSetupFile);
            }
        }
    }
}
