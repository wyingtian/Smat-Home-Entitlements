package cscie97.asn4.housemate.model.IOTDevices;

import cscie97.asn4.housemate.model.Room;

/**
 * oven class
 * @author ying
 *
 */
public class Oven extends Appliance {
	private String power;
	private String temperature;
	private String timeToCook;

	public Oven(String name, String type, Room location) {
		super(name, type, location);

	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTimeToCook() {
		return timeToCook;
	}

	public void setTimeToCook(String timeToCook) {
		this.timeToCook = timeToCook;
	}

	@Override
	public String showInfo() {
		return  name + " in "
				+ location.roomInfo();
	}

	@Override
	public void showAllStatus() {
		System.out.println("---------------------");
		System.out.print("The oven: ");
		System.out.println(name);
		System.out.println("Status Power: ");
		System.out.println(getPower());
		System.out.println("Status temperature: ");
		System.out.println(getTemperature());
		System.out.println("Status TimeToCook: ");
		System.out.println(getTimeToCook());
		System.out.println("---------------------");

	}
	
	@Override
	public void configMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(String status, String value) {
		switch (status){
		case "power":  setPower(value);break;
		case "temperature": setTemperature(value);break;
		case "timetocook":setTimeToCook(value);break;
		default:System.out.println("Wrong status input");break;
		}

	}

	@Override
	public String showStatus(String status) {
		switch (status){
		case "power":  return "The power status of the oven is now "+getPower();
		case "temperature": return "The temperature of the oven is now "+getTemperature();
		case "timetocook": return "Time to cook of the oven is now "+getTimeToCook();
		default:return "Wrong status input";
		}
		
	}

	@Override
	public void setDefault() {
		setTemperature("0");
		setPower("OFF");
		setTimeToCook("-1");
		
	}

}
