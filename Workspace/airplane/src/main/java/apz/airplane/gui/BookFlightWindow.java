package apz.airplane.gui;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.Booking;
import apz.airplane.Flight;
import apz.airplane.User;
import apz.airplane.admin.AdminState;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookFlightWindow {
	private VBox mainPane = new VBox(10);
	private ListView<Flight> flights = new ListView <>();
	private ArrayList <Flight> flightList = new ArrayList<>();
	private DatePicker calendar;
	
	
	public BookFlightWindow() {
		Button findFlightButton = new Button("Find Flights");
		Button bookFlightButton = new Button("Book Flight");
	    calendar = new DatePicker();
	    calendar.setEditable(false);
		mainPane.getChildren().addAll(new Label("Select a flight date"), calendar, findFlightButton, 
				new Label("List of flights on selected date"), flights, bookFlightButton);

		findFlightButton.setOnAction(event -> {
			if (calendar.getValue() != null) {
				flightList = findFlights(calendar.getValue());
				
				if (!flights.getItems().isEmpty())
					flights.getItems().clear();
				for (int i = 0; i < flightList.size(); i++)
					flights.getItems().add(flightList.get(i));
				
				if (flights.getItems().isEmpty() ) {
					MessageBox.message(AlertType.INFORMATION, "No Flights Found", "There are no flights scheduled for " + calendar.getValue());
				}
			}
			else {
				MessageBox.message(AlertType.ERROR, "ERROR", "You must select a date");
			}
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
				MessageBox.message(AlertType.INFORMATION, "No Data Entered", "You must select a flight to book");
}
		});
		
		APZLauncher.getBorderPane().setCenter(mainPane);
	}


	public ArrayList<Flight> findFlights(LocalDate departure) {
		
		ArrayList<Flight> searchFlights = AdminState.loadFlights();
		ArrayList<Flight> flightsFound = new ArrayList<>();
		
		for (int i = 0; i < searchFlights.size(); i ++) {
			if (departure.equals(searchFlights.get(i).getDepartureDate())) {
				flightsFound.add(searchFlights.get(i));
			}
		}
		return flightsFound;
	}
}
