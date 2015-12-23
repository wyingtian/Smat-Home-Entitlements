package cscie97.asn4.housemate.model;
import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AccessDeniedException;
import cscie97.asn4.housemate.entitlement.entitlement.exception.AuthenticationException;
import cscie97.asn4.housemate.model.engine.Importer;
import cscie97.asn4.housemate.model.engine.KnowledgeGraph;
import cscie97.asn4.housemate.model.engine.QueryEngine;
import cscie97.asn4.housemate.model.IOTDevices.Appliance;
import cscie97.asn4.housemate.model.IOTDevices.Sensor;
import cscie97.asn4.housemate.model.Occupants.Occupant;

import java.util.HashMap;
import java.util.List;
/**
 * This is a service Interface implemented by HouseMateModel to provide methods
 * to make changes and check status.
 */
public interface  ServiceInterface   {
	/**
	 *Set Sensor Status based on sensor name
	 * @param sensorName
	 * @param statusName
	 * @param value
	 * @param tokens
	 * @param authToken
	 */
	public  void setSensor(String sensorName, String statusName, String value, String[] tokens, AccessToken authToken) throws AuthenticationException;

	/**
	 *This method return the importer for KG inside the HouseMateModel
	 * @return Importer
	 */
	public  Importer getImporter();

	/**
	 *This method return the QueryEngine for KG inside the HouseMateModel
	 * @return QueryEngine
	 */
	public  QueryEngine getQueryEngine();

	/**
	 *This method return the KG inside the HouseMateModel
	 * @return KnowledgeGraph
	 */
	public  KnowledgeGraph getKnowledgeGraph();

	/**
	 * define house method
	 * @param houseName
	 * @param authToken
	 */
	public  void defineHouse(String houseName, AccessToken authToken) throws AuthenticationException;

	/**
	 *define room method
	 * @param roomName
	 * @param floor
	 * @param type
	 * @param houseName
	 * @param authToken
	 */
	public  void defineRoom(String roomName, String floor,  String type, String houseName, AccessToken authToken) throws AuthenticationException;

	/**
	 *define occupant method
	 * @param occuName
	 * @param occuType
	 * @param authToken
	 */
	public abstract void defineOccupant(String occuName,String occuType, AccessToken authToken) throws AuthenticationException;

	/**
	 *add occupant to house
	 * @param occName
	 * @param houseName
	 * @param authToken
	 */
	public abstract void addOccupant2House(String occName,String houseName, AccessToken authToken) throws AuthenticationException;

	/**
	 * create sensor object
	 * Note: sensor is an abstract class, it create its subclass based on input,
	 * roomName is in the form of house:room1;
	 * @param sensorName
	 * @param sensorType
	 * @param roomName
	 * @param authToken
	 */
	public abstract void defineSensor(String sensorName,String sensorType,String roomName, AccessToken authToken) throws AuthenticationException;

	/**
	 * create appliance object
	 * Note: appliance is an abstract class, it create its subclass based on input
	 * @param appName
	 * @param appType
	 * @param roomName
	 * @param authToken
	 */
	public abstract void defineAppliance(String appName,String appType,String roomName, AccessToken authToken) throws AuthenticationException;


	/**
	 *  show the status of the sensor or appliance
	 * @param sensorName
	 * @param authToken
	 */
	public abstract void showSensor(String sensorName, AccessToken authToken);

	/**
	 *  show all the configuration of the house
	 * including all the rooms and devices and their status
	 * @param appName
	 * @param statusName
	 * @param authToken
	 */

	/**
	 *  show all the configuration of the room
	 * including all the  devices and their status
	 * @param roomName
	 * @param authToken
	 */
	public abstract void showConfigRoom(String roomName, AccessToken authToken);

	/**
	 *  show all the configuration of all the houses
	 * including all the rooms and devices and their status
	 * @param authToken
	 */

	public abstract void showConfigAllHouse(AccessToken authToken);


//	/**
//	 *This method find a type of sensor in a room based on the room name and the type of sensor
//	 * @param roomName
//	 * @param sensorType
//	 * @param authToken
//	 * @return
//	 */
	public abstract List<Sensor> findSensorInRoom(String roomName, String sensorType, AccessToken authToken);

//	/**
//	 *find the sensor based on the location
//	 * @param sensorName
//	 * @param authToken
//	 * @return
//	 */

	public abstract List<Sensor> findSensorInHouse(String houseName, String sensorType, AccessToken authToken) ;

	/**
	 * This method find a type of appliance based on the room name and the type of appliance
	 * @param location
	 * @param type
	 * @param authToken
	 * @return
	 */
	public abstract List<Appliance> findApplianceByType(String location, String type, AccessToken authToken);
	public abstract HashMap<String, Occupant> getAllOccupantMap();

	/**
	 *This method find a type of appliance based on the house name location and the type of appliance
	 * @param houseName
	 * @param applianceType
	 * @param authToken
	 * @return
	 */
	public abstract List<Appliance> findApplianceInHouse(String houseName, String applianceType, AccessToken authToken);

	/**
	 *
	 * @param appName
	 * @param statusName
	 * @param authToken
	 */
	public abstract void showApplianceStatus(String appName,String statusName,AccessToken authToken);

	/**
	 *
	 * @param appName
	 * @param authToken
	 */
	public abstract void showAllApplianceStatus(String appName,AccessToken authToken);

	/**
	 *
	 * @param houseName
	 * @param authToken
	 */
	public abstract void showConfigHouse(String houseName, AccessToken authToken) throws AuthenticationException;

	/**
	 *
	 * @param appName
	 * @param statusName
	 * @param value
	 * @param authToken
	 */
	public abstract void setApplianceStatus(String appName,String statusName,String value,AccessToken authToken) throws AuthenticationException;

}


//	/**
//	 * find a house based on name
//	 * @param houseName
//	 * @param authToken
//	 * @return
//	 */
//	public abstract House findHouse(String houseName,AccessToken authToken);
//
//	/**
//	 * find the room based on the location
//	 * <houseName>:<roomName>
//	 * @param roomName
//	 * @param authToken
//	 * @return
//	 */
//	public abstract Room findRoom(String roomName,AccessToken authToken);
//

//	public abstract Sensor findSensor(String sensorName,AccessToken authToken);

/**
 * This method find a type of sensor in a house based on the house name and the type of sensor
 * @param houseName
 * @param sensorType
 * @param authToken
 * @return
 */

/**
 //	 * find the appliance based on the location
 //	 * @param applianceName in the form of <houseName>:<roomName>:<applianceName>
 //	 * @param authToken
 //	 * @return
 //	 */
//	public abstract Appliance findAppliance(String applianceName,AccessToken authToken);

