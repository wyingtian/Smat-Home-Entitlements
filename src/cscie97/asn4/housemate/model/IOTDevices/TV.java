package cscie97.asn4.housemate.model.IOTDevices;

import cscie97.asn4.housemate.model.Room;

/**
 * TV class
 * @author ying
 *
 */
public class TV extends Appliance {

	
	private String channel;
	private String powerStatus;
	private int volume;	
	public TV(String name, String type, Room location) {
		super(name, type, location);
	}

	// show the basic info of the TV
	public String showInfo() {
		return "The name of the " + type + " is " + name + ", It is in Room "
				+ location.roomInfo();
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPowerStatus() {
		return powerStatus;
	}
	public void setPowerStatus(String powerStatus) {
		this.powerStatus = powerStatus;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	// print out the TV status
	public void showAllStatus() {
		System.out.println("---------------------");
		System.out.print("The TV: ");
		System.out.println(name);
		System.out.println("Status Power: ");
		System.out.println(getPowerStatus());
		System.out.println("Status Channel: ");
		System.out.println(getChannel());
		System.out.println("Status Volume: ");
		System.out.println(getVolume());
		System.out.println("---------------------");
	}
	public void changeStatus(String status, String value){
		switch (status){
		case "power":  setPowerStatus(value);break;
		case "volume": setVolume(Integer.parseInt(value));break;
		case "channel":setChannel(value);break;
		default:System.out.println("Wrong status input");break;
		}
	}

	// turn on the TV
	public void turnOnTV() {
		setPowerStatus("ON");
		System.out.println("The TV has been turned on");
	}

	// turn off the TV
	public void turnOffTV() {
		setPowerStatus("OFF");
		System.out.println("The TV has been turned off");
	}

	// increase the Volume
	public void volumeUP() {
		setVolume(getVolume() + 5);
		System.out.println("volume increase 5, " + " Now the Volume is "
				+ getVolume());
	}

	// decrease the Volume
	public void volumeDown() {
		setVolume(getVolume() - 5);
		System.out.println("volume decrease 5, " + " Now the Volume is "
				+ getVolume());
	}

	// config mode
	public void configMode() {
		System.out.println("**************");
		System.out.println("You entered the configuration mode for " + name);
		System.out.println("Please enter the number for the following options");
		System.out.println("For TV power switch please enter 1");
		System.out.println("For chanel please enter  2 ");
		System.out.println("For volume please enter  3 ");
		int input = scan.nextInt();
		switch (input) {
		case 1:
			changeTVSwitch();
			break;
		case 2:
			System.out.println("Please enter the channel number");
			this.setChannel(scan.nextLine());
			System.out
					.println("the chanel now is " + this.getChannel());
			break;
		case 3:
			 changeVolume();
			break;
		default:
			System.out.println("Invalid option");
			configMode();
			break;

		}
	}

	// get an input from command line  and then switch the TV on and off
	public void changeTVSwitch() {
		System.out.println("Please enter 0 or 1, 0 is off, 1 is on");
		int input = scan.nextInt();
		switch (input) {
		case 0:
			this.turnOffTV();
			break;
		case 1:
			this.turnOnTV();
			break;
		default:
			System.out.println("You can only enter 0 or 1");
			changeTVSwitch();
			break;
		}
	}
	
	//get an int input from command line and then change the volume of the TV
	public void  changeVolume(){
		System.out.println("Please enter 0 or 1, ");
		System.out.println("0 is volume down by 5, 1 is volume down by 5");
		int input = scan.nextInt();
		switch (input) {
		case 0:
			this.volumeDown();
			break;
		case 1:
			this.volumeUP();
			break;
		default:
			System.out.println("You can only enter 0 or 1");
			changeVolume();
		    break;
		}
	}

	@Override
	public String showStatus(String status) {
		switch (status){
		case "power":  return "The power status of the tv is now "+getPowerStatus();
		case "volume": return "The volume status of the tv is now "+getVolume();
		case "channel":return "The channel status of the tv is now "+getChannel();
		default:return "Wrong status input";
		}
	}

	@Override
	public void setDefault() {
		setPowerStatus("OFF");
		setVolume(30);
		setChannel("0");
		
	}
}
