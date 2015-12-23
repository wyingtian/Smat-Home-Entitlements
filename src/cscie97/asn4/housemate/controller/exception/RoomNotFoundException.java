package cscie97.asn4.housemate.controller.exception;

import cscie97.asn4.housemate.model.House;

public class RoomNotFoundException extends Exception {

	private House theHouse;
	
	public RoomNotFoundException() {
		super();

	}

	public RoomNotFoundException(String s) {
		System.out.println(s);
	}
	
	public RoomNotFoundException(String s,House house) {
		System.out.println(s);
	}
}
