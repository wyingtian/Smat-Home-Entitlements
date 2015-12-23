package cscie97.asn4.housemate.model.IOTDevices;

import cscie97.asn4.housemate.model.Room;

/**
 * Door class 
 * @author ying
 */
public class Door extends Appliance{
	private String state;
	public Door(String name, String type, Room location) {
		super(name, type, location);
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		//System.out.println(this.getName()+" in "+this.getLocationPair()+" is "+state);
	}

	@Override
	public String showInfo() {
		return name + "in "
				+ location.roomInfo();
	}

	@Override
	public void showAllStatus() {
		System.out.print("The door: ");
		System.out.println(name);
		System.out.print("Door is now: ");
		System.out.println(getState());
		
		
	}
	@Override
	public void configMode() {
		
	}
	@Override
	public void changeStatus(String status, String value) {
		if(value.equals("open")){
			setState("OPEN");
		}else if(value.equals("close")){
			setState("CLOSE");
		}else if(value.equals("locked")){
			setState("LOCKED");
		}else{
			System.out.println("Invalid door state");
		}
	}
	@Override
	public String showStatus(String status) {
		return "The door is now " +getState();
	}
	@Override
	public void setDefault() {
		setState("close");
	}

}
