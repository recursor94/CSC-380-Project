package apz.airplane.gui;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.Booking;
import apz.airplane.Flight;
import apz.airplane.User;
import apz.airplane.admin.AdminState;
import apz.airplane.util.MessageBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BookFlightByDestinationWindow {
	
	private VBox mainPane = new VBox(10);
	private ListView<Flight> flights = new ListView <>();
	private ArrayList <Flight> flightList = new ArrayList<>();
	private TextField destinationField;
	private Button findFlightButton, bookFlightButton;
	
	public BookFlightByDestinationWindow() {
		initialize();
		content();
		actionEvents();
	}
	
	private void initialize() {
		findFlightButton = new Button("Find Flights");
		bookFlightButton = new Button("Book Flight");
		destinationField = new TextField();
		
	}
	
	private void content() {
		mainPane.getChildren().addAll(new Label("Enter your desired destination"), destinationField, findFlightButton, flights, bookFlightButton);
		APZLauncher.getBorderPane().setCenter(mainPane);
	}

	private void actionEvents() {
		findFlightButton.setOnAction(event -> {
			flightList = findFlights();
		});
		
		bookFlightButton.setOnAction(event -> {
			if (!flights.getSelectionModel().isEmpty()) {
				User user = APZLauncher.getCurrentUser();
				user.addTrip(new Booking(flights.getSelectionModel().getSelectedItem(), LocalDate.now(), user));
				MessageBox.message(AlertType.INFORMATION, "Successful Booking", "Your flight has been booked!");
				apz.airplane.util.State.saveInformation(APZLauncher.getUserController());
				System.out.println(user.getTripList());
			}
			else {
				MessageBox.message(AlertType.INFORMATION, "No Flight Selected", "You must select a flight to book");
			}
		});
	}
	
	private ArrayList<Flight> findFlights() {
		ArrayList<Flight> searchFlights = AdminState.loadFlights();
		ArrayList<Flight> flightsFound = new ArrayList<>();
		if(!destinationField.getText().isEmpty()) {
			
			for (int i = 0; i < searchFlights.size(); i ++) {
				if (destinationField.getText().equals(searchFlights.get(i).getDestinationAirport().getCity())) {
					flightList.add(searchFlights.get(i));
				}
			}
			if (!flights.getItems().isEmpty())
				flights.getItems().clear();
			for (int i = 0; i < flightList.size(); i++)
				flights.getItems().add(flightList.get(i));
			if (flights.getItems().isEmpty()) {
				MessageBox.message(AlertType.INFORMATION, "No Flights Found", "There are no flights going to " + destinationField.getText());
			}
		}
		else {
			MessageBox.message(AlertType.ERROR, "ERROR: Invalid Data Entry", "You must enter a destination");
		}
		return flightsFound;
	}
}
