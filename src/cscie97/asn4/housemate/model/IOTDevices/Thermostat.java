package cscie97.asn4.housemate.model.IOTDevices;

import cscie97.asn4.housemate.model.Room;

/**
 * thermostat class
 * @author ying
 *
 */
public class Thermostat extends Appliance{
	private String Power;
	private int temperature;

	public Thermostat(String name, String type, Room location) {
		super(name, type, location);
		// TODO Auto-generated constructor stub
	}

	public String getPower() {
		return Power;
	}

	public void setPower(String power) {
		Power = power;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Override
	public String showInfo() {
		return name + "in "
				+ location.roomInfo();
	}

	@Override
	public void showAllStatus() {
		System.out.println("---------------------");
		System.out.print("The thermostat: ");
		System.out.println(name);
		System.out.println("thermostat Power: ");
		System.out.println(getPower());
		System.out.println("thermostat temperature: ");
		System.out.println(getTemperature());
		System.out.println("---------------------");
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
		case "temperature":
			if (value.equals("cooler")) {
				setTemperature(getTemperature() - 5);
			} else if (value.equals("warmer")) {
				setTemperature(getTemperature() + 5);
			} else if (Integer.parseInt(value) < 90
					&& Integer.parseInt(value) > 60)
				setTemperature(Integer.parseInt(value));
			else if (Integer.parseInt(value) > 90
					|| Integer.parseInt(value) < 60)
				System.out.println("Wrong temperature input");
			break;
		default:
			System.out.println("Wrong thermostat status input");
			break;
		}
		
	}

	@Override
	public String showStatus(String status) {
		switch (status) {
		case "power":
			return "The power status of the "+ this.getName()+" is now "
					+ getPower();

		case "temperature":
			return "The temperature of the "+ this.getName()+" is now "
					+ getTemperature();

		default:
			return "Wrong thermostat status input";
		}

		
	}

	@Override
	public void setDefault() {
		setPower("OFF");
		setTemperature(75);
		
	}

}
