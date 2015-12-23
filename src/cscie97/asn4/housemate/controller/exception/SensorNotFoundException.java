package cscie97.asn4.housemate.controller.exception;

public class SensorNotFoundException extends Exception {

	public SensorNotFoundException() {
		super();	
	}
	public SensorNotFoundException(String message) {
		super(message);

	}
	public void print(){
		System.out.println(super.getMessage());
	}
	
}
