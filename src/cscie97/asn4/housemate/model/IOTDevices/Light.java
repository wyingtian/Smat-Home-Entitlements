package cscie97.asn4.housemate.model.IOTDevices;

import cscie97.asn4.housemate.model.Room;

/**
 * light class
 * 
 * @author ying
 */
public class Light extends Appliance {
	private String Power;
	private int intensity;

	public String getPower() {
		return Power;
	}

	public void setPower(String power) {
		Power = power;
	//	System.out.println(this.getName()+" in "+this.getLocationPair()+" is "+power);
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public Light(String name, String type, Room location) {
		super(name, type, location);
	}

	@Override
	public String showInfo() {
		return  name + "in"
				+ location.roomInfo();
	}

	@Override
	public void showAllStatus() {
		System.out.print("The light: ");
		System.out.println(name);
		System.out.println("Light Power: ");
		System.out.println(getPower());
		System.out.println("Light intensity: ");
		System.out.println(getIntensity());
	}

	@Override
	public void configMode() {
		// TODO Auto-generated method stub
	}

	@Override
	public void changeStatus(String status, String value) {
		switch (status) {
		case "power":
			setPower(value);
			break;
		case "intensity":
			if (value.equals("dimmer")) {
				setIntensity(getIntensity() - 10);
			} else if (value.equals("brighter")) {
				setIntensity(getIntensity() + 10);
			} else if (Integer.parseInt(value) < 100
					&& Integer.parseInt(value) > 0)
				setIntensity(Integer.parseInt(value));
			break;
		default:
			System.out.println("Wrong light status input");
			break;
		}

	}
	
	@Override
	public String showStatus(String status) {
		switch (status) {
		case "power":
			return "The power status of the light is now "
					+ getPower();

		case "intensity":
			return "The intensity of the light is now "
					+ getIntensity();

		default:
			return "Wrong light status input";

		}
	}

	@Override
	public void setDefault() {
		setIntensity(50);
		setPower("OFF");

	}

}
