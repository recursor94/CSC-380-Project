package jimmy.pack;

import java.time.LocalDate;

import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.User;
import apz.airplane.user.APZLauncher;
import apz.airplane.util.APZMath;
import apz.airplane.util.APZState;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookingPaymentWindow {
	
	private User user;
	private VBox mainPane;
	private GridPane paymentPane; 
	private Text header;
	private ComboBox<String> paymentBox;
	private Button confirmButton;
	
	private Flight flight;
	private double cost;

	public BookingPaymentWindow(Flight flight) {
		this.flight = flight;
		intialize();
		content();
		actionEvents();
		properties();
	}

	private void intialize() {
		user = APZLauncher.getCurrentUser();
		
		cost = APZMath.getPrice(flight.getDepartureAirport().getCity(), flight.getDestinationAirport().getCity());
		
		mainPane = new VBox(10);
		paymentPane = new GridPane();
		header = new Text("\nProcess Booking");
		
		paymentBox = new ComboBox<>();
		confirmButton = new Button("Confirm Booking");
	}

	private void content() {
		header.setFont(new Font(32));
		
		for (int i = 0; i < user.getPaymentInformation().size(); i++) {
			Long ccNum = user.getPaymentInformation().get(i).getCardNum();
			paymentBox.getItems().add("Card number ending in " + ccNum.toString().substring(11, 15));
		}
		
		paymentPane.add(new Label("Flight Date: "), 0, 2);
		paymentPane.add(new Label(flight.getDepartureDate().toString()), 1, 2);
		paymentPane.add(new Label("Airline: "), 0, 3);
		paymentPane.add(new Label(flight.getPlane().getAirline()), 1, 3);
		paymentPane.add(new Label("Departing Airport: "), 0, 4);
		paymentPane.add(new Label(flight.getDepartureAirport().toString()), 1, 4);
		paymentPane.add(new Label("Destination Airport: "), 0, 5);
		paymentPane.add(new Label(flight.getDestinationAirport().toString()), 1, 5);
		paymentPane.add(new Label("Cost for Flight: "), 0, 6);
		paymentPane.add(new Label("$" + cost), 1, 6);
		
		
		paymentPane.add(new Label("Payment Method: "), 0, 8);
		if (paymentBox.getItems().isEmpty()) 
			paymentPane.add(new Button("Click here to setup a payment method"), 1, 8);
		else 
			paymentPane.add(paymentBox, 1, 8);
		
		paymentPane.setHgap(15);
		paymentPane.setVgap(15);
		paymentPane.setAlignment(Pos.TOP_CENTER);
		
		confirmButton.setMinWidth(250);
		mainPane.getChildren().addAll(header, paymentPane, new Separator(), confirmButton);
		mainPane.setAlignment(Pos.TOP_CENTER);
		
		// disable box
		if (user.getPaymentInformation().isEmpty()) {
			
		}
	}

	private void actionEvents() {
		confirmButton.setOnAction(event -> {
			user.getTripList().add(new Booking(flight, LocalDate.now(), user, cost));
			MessageBox.message(AlertType.INFORMATION, null, "Trip has been booked! Receipt number: ");
			APZState.saveInformation();
		});
	}

	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(550);
	}

}
