package cscie97.asn4.housemate.controller.exception;

public class AppNotFoundException extends Exception {

	public AppNotFoundException() {
		super();

	}

	public AppNotFoundException(String message) {
		super(message);
		System.out.println(message);
	}
}
