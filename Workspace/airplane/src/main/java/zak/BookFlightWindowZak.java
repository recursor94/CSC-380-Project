package zak;

import java.time.LocalDate;
import java.util.ArrayList;

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

public class BookFlightWindowZak {
	private VBox mainPane = new VBox(10);
	private ListView<FlightZak> flights = new ListView <>();
	private ArrayList <FlightZak> flightList = new ArrayList<>();
	private DatePicker calendar;
	
	
	public BookFlightWindowZak() {
		Button findFlightButton = new Button("Find Flights");
	    calendar = new DatePicker();
		mainPane.getChildren().addAll(new Label("Select a flight date"), calendar, findFlightButton, new Label("List of flights on selected date"), flights);

		findFlightButton.setOnAction(event -> {
			if (calendar.getValue() != null) {
				flightList = findFlights(calendar.getValue());
				
				if (!flights.getItems().isEmpty())
					flights.getItems().clear();
				for (int i = 0; i < flightList.size(); i++)
					flights.getItems().add(flightList.get(i));
				
				if (flights.getItems().isEmpty() ) {
					MessageBoxZak.message(AlertType.INFORMATION, "No Flights Found", "There are no flights scheduled for " + calendar.getValue());
				}
			}
			else {
				MessageBoxZak.message(AlertType.INFORMATION, "ERROR", "You must select a date");
			}
		});
		
		ZakLauncher.getBorderPane().setCenter(mainPane);
	}


	public ArrayList<FlightZak> findFlights(LocalDate departure) {
		
		ArrayList<FlightZak> searchFlights = StateZakAdmin.loadFlights();
		ArrayList<FlightZak> flightsFound = new ArrayList<>();
		
		for (int i = 0; i < searchFlights.size(); i ++) {
			if (departure.equals(searchFlights.get(i).getDepartureDate())) {
				flightsFound.add(searchFlights.get(i));
			}
		}
		return flightsFound;
	}
}

