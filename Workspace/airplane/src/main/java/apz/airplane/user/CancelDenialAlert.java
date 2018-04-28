package apz.airplane.user;

import apz.airplane.util.MessageBox;
import javafx.scene.control.Alert.AlertType;

public class CancelDenialAlert {
	
	private final static String TITLE = "Error Cancelling Flight";
	private final static String HEADER = "Cancellation Period Expired";
	private final static String MESSAGE = "You may not cancel a flight more than one 24 hours after booking.\n"
			                            +"Please contact Customer Service for Assitance.";
	
	public static void cancelFlightError() {
		MessageBox.message(AlertType.ERROR, TITLE, MESSAGE, HEADER);
	}

}
