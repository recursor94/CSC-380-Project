package apz.airplane.user;

import java.time.LocalDate;
import java.util.Optional;

import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.User;
import apz.airplane.util.APZMath;
import apz.airplane.util.APZState;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jimmy.pack.WindowInterface;

public class CheckBaggageWindow implements WindowInterface {
	
	private Text header;
	private VBox mainPane;
	private GridPane gridPane;
	private ComboBox<Integer> numBagsBox;
	private Button confirmButton;
	private Stage stage;
	private Flight bookedFlight;
	
	public CheckBaggageWindow (Flight flight) {
		bookedFlight = flight;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		header = new Text("Check Baggage: $20 per bag");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		numBagsBox = new ComboBox<>();
		confirmButton = new Button ("Confirm");
	}

	public void content() {
		
		header.setFont(new Font(24));
		populateBagsBox();
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.add(new Label("Select Number of Bags to Check: "), 0, 0);
		gridPane.add(numBagsBox, 1, 0);
		
		mainPane.setAlignment(Pos.TOP_CENTER);
		mainPane.getChildren().addAll(header, gridPane, confirmButton);
	}

	public void actionEvents() {
		confirmButton.setOnAction(event -> {
			double cost = APZMath.getPrice(bookedFlight.getDepartureAirport().getCity(), bookedFlight.getDestinationAirport().getCity());
			cost += 20 * numBagsBox.getSelectionModel().getSelectedIndex();
			//System.out.println("Your trip bill: $" + cost);
			
			Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "Confirmation of Booking", "The cost of your trip will be $" 
			+ cost + "\n Press OK to book your trip");
			
			//Book the flight
			if(result.get() == ButtonType.OK) {
				User user = APZLauncher.getCurrentUser();
				Booking trip = new Booking(bookedFlight, LocalDate.now(), user, cost);
				user.addTrip(trip);
				MessageBox.message(AlertType.INFORMATION, "Successful Booking", "Your flight has been booked!");
				APZState.saveInformation();
				System.out.println("Booked Trips\n" + user.getTripList());
				System.out.println("Trip Cost: $" + trip.getTripCost());
				stage.close();
			}
		});
		
	}

	public void properties() {
		stage = new Stage();
		stage.setTitle("Check Baggage");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 325, 200));
		stage.show();
		
	}

	private void populateBagsBox() {
		for(int i = 0; i < 4; i ++) {
			numBagsBox.getItems().add(i);
		}
		numBagsBox.setValue(0);
	}
}
