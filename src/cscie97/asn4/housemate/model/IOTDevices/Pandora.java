package cscie97.asn4.housemate.model.IOTDevices;

import cscie97.asn4.housemate.model.Room;

/**
 * pandora class
 * @author ying
 *
 */
public class Pandora extends Appliance {
	private String channel;
	private String power;
	private String volume;

	public Pandora(String name, String type, Room location) {
		super(name, type, location);
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Override
	public String showInfo() {
		return "The name of the " + type + " is " + name + ", It is in Room "
				+ location.roomInfo();
	}

	@Override
	public void showAllStatus() {
		System.out.print("The pandora: ");
		System.out.println(name);
		System.out.println("Pandora Power: ");
		System.out.println(getPower());
		System.out.println("Pandora channel: ");
		System.out.println(getChannel());
		System.out.println("Pandora volume: ");
		System.out.println(getVolume());

	}

	@Override
	public void configMode() {

	}

	@Override
	public void changeStatus(String status, String value) {
		switch (status) {
		case "power":
			setPower(value);
			break;
		case "channel":
			setChannel(value);
			break;
		case "volume":
			setVolume(value);
			break;
		default:
			System.out.println("Wrong pandora status input");
			break;
		}

	}

	@Override
	public String showStatus(String status) {
		switch (status) {
		case "power":
			return "The power status of the pandora is now "
					+ getPower();

		case "channel":
			return "The temperature of the pandora is now "
					+ getChannel();


		case "volume":
			return "Volume of the pandora is now " + getVolume();


		default:
			return "Wrong pandora status input";

		}

	}

	@Override
	public void setDefault() {
		setChannel("0");
		setPower("OFF");
		setVolume("0");
		

	}

}
