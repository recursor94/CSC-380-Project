package jimmy.pack;

import apz.airplane.model.Flight;
import apz.airplane.model.User;
import apz.airplane.user.APZLauncher;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookingPaymentWindow {
	
	private User user;
	private GridPane mainPane; 
	private Text header;
	private ComboBox<String> paymentBox;
	
	private Flight flight;

	public BookingPaymentWindow(Flight flight) {
		this.flight = flight;
		intialize();
		content();
		actionEvents();
		properties();
	}

	private void intialize() {
		user = APZLauncher.getCurrentUser();
		
		mainPane = new GridPane();
		header = new Text("Process Booking");
		
		paymentBox = new ComboBox<>();
	}

	private void content() {
		header.setFont(new Font(32));
		
		for (int i = 0; i < user.getPaymentInformation().size(); i++) {
			Long ccNum = user.getPaymentInformation().get(i).getCardNum();
			paymentBox.getItems().add("Carding number ending with " + ccNum.toString().substring(11, 15));
		}
		
		mainPane.add(header, 0, 0);
		
		mainPane.add(new Label("Payment: "), 0, 2);
		mainPane.add(paymentBox, 1, 2);
		
		// disable box
		if (user.getPaymentInformation().isEmpty()) {
			
		}
	}

	private void actionEvents() {
		
	}

	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(600);
	}

}
