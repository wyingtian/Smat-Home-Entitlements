package cscie97.asn4.housemate.controller.exception;

public class CommandFormatException extends Exception{
	
	public CommandFormatException(String message,int LineNum) {
		super(message);
	}	
}
