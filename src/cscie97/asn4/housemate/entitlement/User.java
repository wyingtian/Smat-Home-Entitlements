package cscie97.asn4.housemate.entitlement;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

/**
 * User is one of the Entitlement domain classes.
 * It has the info of the credential and the role and resource role that is associate with the user.
 * Created by ying on 10/29/15.
 */

public class User implements VisitorAccepter {
    String id;
    String name;
    Credential credential;
    Role role;
    HashMap <String,ResourceRole> userResourceRoleMap = new HashMap<>();

    public User(String id ,String name) {
        this.id = id;
        this.name = name;
        this.credential = new Credential();
    }

    /**
     *Accept the algorithm visitor  class to implement the visitor pattern.
     * @param visitor
     */
    @Override
    public void acceptVisitor(EntitlementDomainClassVisitor visitor) {
        visitor.visitUser(this,"");
    }

    /**
     * Add a type of credential(password or voiceprint) to the user.
     * @param type
     * @param value
     */
    public void addUserCredential(String type, String value){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            String text = value;
            md.update(text.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {

        }
            catch (UnsupportedEncodingException e) {

        }
        byte[] digest = md.digest();

        String outEncoded = Base64.getEncoder().encodeToString( digest );

        this.credential.value = outEncoded;
        this.credential.type = type;
    }

    /**
     * Add a resource role to user
     * @param resourceRoleId
     */
    public void addUserResourceRole(String resourceRoleId){
        if(HouseMateEntitlementFactory.getInstance().resourceRoleHashMap.containsKey(resourceRoleId)){
            userResourceRoleMap.put(resourceRoleId, HouseMateEntitlementFactory.getInstance().resourceRoleHashMap.get(resourceRoleId));
        }
    }
    public String getId() {
        return id;
    }
    public String getName() {return name;}
    public void addRole(Role role){
        this.role = role;
    }
    public void showInfo(){
        System.out.println("ID:                "+this.id);
        System.out.println("NAME:              "+this.name);
        System.out.println("CREDENTIAL TYPE:   "+this.credential.type);
        System.out.println("CREDENTIAL VALUE:  "+this.credential.value);
        System.out.println("RESOURCE ROLE: ");
        for(String e : userResourceRoleMap.keySet()){
            System.out.println("                   "+e);
        }
        this.role.print();
    }
    public Role getRole() {
        return role;
    }
    public Credential getCredential() {
        return credential;
    }
    public HashMap<String, ResourceRole> getUserResourceRoleMap() {
        return userResourceRoleMap;
    }

}
