package cscie97.asn4.housemate.entitlement;

import cscie97.asn4.housemate.entitlement.entitlement.exception.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

import static java.lang.System.exit;

/**
 *  HouseMateEntitlementService is to authenticate the user to check
 *  if they have access to an behavior in the House Mate Model Service and House Mate controller service.
 *  Created by ying on 10/29/15.
 */

public class HouseMateEntitlementImpl implements HouseMateEntitlementService {

    private static final HouseMateEntitlementImpl entitlement = new HouseMateEntitlementImpl();
    EntitlementDomainFactory entitlementDomainFactory = new ConcreteDomainFactory();
    HashMap<String,EntitlementComponent> entitlementMap = new HashMap<>();
    HashMap<String,User> userMap = new HashMap<>();
    HashMap<String,ResourceRole>  resourceRoleHashMap = new HashMap<>();
    HashMap<AccessToken,User> accessTokenMap = new HashMap<>();

    /**
     * The singleton to return the only instance of HouseMateEntitlementImpl service
     * @return the only instance of HouseMateEntitlementImpl itself
     */
    public static HouseMateEntitlementImpl getInstance() {
        return entitlement;
    }

    /**
     * The logIn method provides verify the username and
     * password or voice print and return the accesstoken if success
     * @param userId
     * @param credentialType
     * @param credentialValue
     * @return
     */
    public AccessToken logIn(String userId, String credentialType, String credentialValue) {
        User user;
        AccessToken accessToken = null;
        //String hashedCredentialValue;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            String text = credentialValue;
            md.update(text.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
        }
        catch (UnsupportedEncodingException e) {
        }
        byte[] digest = md.digest();

        String hashedCredentialValue = Base64.getEncoder().encodeToString( digest );

        try {
            if (userMap.containsKey(userId)) {
                user = userMap.get(userId);
                if (user.getCredential().getValue().equals(hashedCredentialValue)) {
                    accessToken = new AccessToken(UUID.randomUUID().toString());
                    System.out.println("****************************");
                    System.out.println(userId + " logged in successfully" );
                    accessTokenMap.put(accessToken, user);
                    return accessToken;
                } else if (credentialType.equals("password")) {
                    throw new WrongPasswordException(credentialValue);
                } else if (credentialType.equals("voice_print")) {
                    throw new WrongVoicePrintException(credentialValue);
                }
            } else {
                throw new UserNameNotFoundException(userId);
            }
        }catch (WrongPasswordException e){
            e.print();
            exit(1);
        }catch (WrongVoicePrintException e){
            e.print();
            exit(1);
        }catch (UserNameNotFoundException e){
            e.print();
            exit(1);
        }
        return accessToken;
    }

    /**
     * Check if the access token has the right permission or role
     * @param authToken
     * @param entitlementID
     * @return
     * @throws AuthenticationException
     */
    public boolean checkAccess(AccessToken authToken, String entitlementID) throws AuthenticationException {
        if(authToken != null && authToken.getState().equals("valid")  ) {
            if (accessTokenMap.containsKey(authToken)) {
                User theUser = accessTokenMap.get(authToken);
                EntitlementDomainClassVisitor visitor = new CheckAccessVisitor();
                return visitor.visitUser(theUser,entitlementID);
            } else
               throw new AuthenticationException("token not in table");
        }else
            throw new InvalidAccessTokenException("invalid access token");
    }

    /**
     * import auth file
     * @param fileName
     * @throws IOException
     */
    public  void importConfigFile(String fileName) throws IOException {
        int commandCount = 1;
        BufferedReader br = null;
        String line;
        String modifiedLine;
        String auth_token = "";
        try {
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.indexOf('#') != -1) {
                    continue;
                }
                // replace "." with whitespace and then get rid of all the
                // whitespace in the end of the line.
                modifiedLine = line.trim();
                if (modifiedLine.length() == 0)
                    continue;

            //    System.out.print(commandCount + ".  ");
            //    System.out.println(modifiedLine);
                processAuthData(modifiedLine);
                commandCount++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to read file: " + fileName);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Unable to close file: " + fileName);
            } catch (NullPointerException e) {
                System.out.println("NullPointException");
            }
        }
    }

    /**
     * This method process the authentication data file and
     * use domain factory to generate Entitlement domain object
     * @param authData
     */
    public void  processAuthData(String  authData){
        String[] tokens = authData.split(",");
        String firstToken = tokens[0];
        if(firstToken.equals("define_permission")){
          Permission per = entitlementDomainFactory.createPermission(tokens[1], tokens[2], tokens[3]);
            entitlementMap.put(per.getId(), per);
        }else if(firstToken.equals("define_role")){
          Role role = entitlementDomainFactory.createRoles(tokens[1], tokens[2], tokens[3]);
            entitlementMap.put(role.getId(), role);
        }else if(firstToken.equals("add_entitlement_to_role")){
            if(entitlementMap.containsKey(tokens[1]) && entitlementMap.containsKey(tokens[2])){
                entitlementMap.get(tokens[1]).add(entitlementMap.get(tokens[2]));
            }
        }else if(firstToken.equals("create_user")){
            User user = entitlementDomainFactory.createUser(tokens[1],tokens[2]);
            userMap.put(user.getId(),user);
        }else if(firstToken.equals("add_user_credential")){
            if(userMap.containsKey(tokens[1])){
                userMap.get(tokens[1]).addUserCredential(tokens[2],tokens[3]);
            }
        }else if(firstToken.equals("add_role_to_user")){
            if(entitlementMap.containsKey(tokens[2]) && userMap.containsKey(tokens[1])){
                userMap.get(tokens[1]).addRole((Role)entitlementMap.get(tokens[2]));
            }
        }else if(firstToken.equals("create_resource_role")){
            ResourceRole resourceRole = entitlementDomainFactory.createResourceRole(tokens[2],tokens[3]);
            resourceRoleHashMap.put(resourceRole.getDescription(), resourceRole);
        }else if(firstToken.equals("add_resource_role_to_user")){
            if(userMap.containsKey(tokens[1])){
                userMap.get(tokens[1]).addUserResourceRole(tokens[2]);
            }
        }
    }

    public void showAuthFileResult(){
        InventoryPrintVisitor invPrinter = new InventoryPrintVisitor();
        System.out.println("********************Permission List*********************");

        String format = "%-25s%s%n";
        System.out.printf(format,"PERMISSION_ID","PERMISSION_DESCRIPTION");
        System.out.println();
        for(String s:entitlementMap.keySet()){
            if( entitlementMap.get(s) instanceof Permission) {
                entitlementMap.get(s).acceptVisitor(invPrinter);
            }
       }

        System.out.println();
        System.out.println("********************Role List**************************");
        System.out.println();
        for(String s:entitlementMap.keySet()){
            if( entitlementMap.get(s) instanceof Role) {
                entitlementMap.get(s).acceptVisitor(invPrinter);
            }
        }

        System.out.println();
        System.out.println("********************User List**************************");
        System.out.println();
        for(String s:userMap.keySet()){
                userMap.get(s).acceptVisitor(invPrinter);

        }

//        System.out.println();
//        System.out.println("********************Entitlements In Role List**************************");
//        System.out.println();
//        System.out.printf(format,"ROLE_ID","ROLE_DESCRIPTION");
//        for(String s:entitlementMap.keySet()){
//            if( entitlementMap.get(s) instanceof Role) {
//                entitlementMap.get(s).acceptVisitor(invPrinter);
//            }
//        }
    }



}
//HashMap<String,Role> roleMap = new HashMap<>();
//HashMap<String,Permission> permissionMap = new HashMap<>();
//    public HashMap<AccessToken, User> getAccessTokenMap() {
//        return accessTokenMap;
//    }


//    public void parseVoicePrint(String voicePrint){
//        String occName = voicePrint.substring(2, voicePrint.length() - 2);
//
//    }


//    public static void main(String args[]) throws IOException {
//
//       HouseMateEntitlementImpl en = HouseMateEntitlementFactory.getInstance();
//        en.importConfigFile(args[0]);
//
//        EntitlementDomainClassVisitor v = new InventoryPrintVisitor();
//        EntitlementComponent role;
//        for(String e :en.entitlementMap.keySet() ){
//            if((role = en.entitlementMap.get(e)) instanceof Role) {
//                v.visitRole((Role)role);
//            }
//        }
//
//        for(String e :en.userMap.keySet() ){
//            v.visitUser(en.userMap.get(e));
//        }
//
//        for(String e :en.resourceRoleHashMap.keySet()){
//            v.visitResourceRole(en.resourceRoleHashMap.get(e));
//        }
//
//
//    }
//
//    public String firstToken(String authData){
//        String tokens[] = authData.split(",");
//        return tokens[1];
//    }