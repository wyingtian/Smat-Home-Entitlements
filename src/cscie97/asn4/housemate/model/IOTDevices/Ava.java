package cscie97.asn4.housemate.model.IOTDevices;

import cscie97.asn4.housemate.model.Room;

public class Ava extends Sensor{
	private String status = "listening";
	public Ava(String name, String type, Room location) {
		super(name, type, location);
	}
	public void speak(String toSpeak){
		System.out.println("("+this.getName() +" in " +this.location +" is broadcasting)--" + toSpeak);
	}

	@Override
	public String showInfo(){
		return name +", in  "+ location.roomInfo();
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setStatus(String statusName, String value) {

	}

	@Override
	public String showStatus() {
		return "Ava is listening for command";
	}
}
