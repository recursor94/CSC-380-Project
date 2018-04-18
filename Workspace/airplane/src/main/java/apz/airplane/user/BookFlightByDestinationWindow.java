package apz.airplane.user;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.admin.AdminState;
import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.Province;
import apz.airplane.model.User;
import apz.airplane.util.APZState;
import apz.airplane.util.MessageBox;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookFlightByDestinationWindow {
	
	private VBox mainPane;
	private GridPane gridPane;
	private HBox buttonBox;
	private Text header;
	private ListView<Flight> flightView;
	private ArrayList <Flight> flightList;
	private Button findFlightButton, bookFlightButton;
	private ComboBox<String> destinationBox;
	
	public BookFlightByDestinationWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	private void initialize() {
		gridPane = new GridPane();
		mainPane = new VBox(10);
		buttonBox = new HBox(10);
		header = new Text("Find and Book Flights by Destination");
		flightView = new ListView <>();
	    flightList = new ArrayList<>();
		findFlightButton = new Button("Find Flights");
		bookFlightButton = new Button("Book Flight");
		destinationBox = new ComboBox<>();
	}
	
	private void content() {
		
		header.setFont(new Font(28));
		
		populateComboBox();
		
		buttonBox.getChildren().addAll(findFlightButton, bookFlightButton);
		buttonBox.setAlignment(Pos.CENTER);
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		gridPane.add(new Label("Select your desired destination"), 0, 0);
		gridPane.add(destinationBox, 1, 0);
		
		gridPane.setAlignment(Pos.CENTER);
		
		mainPane.getChildren().addAll(header, gridPane, flightView, buttonBox);
		
		mainPane.setAlignment(Pos.CENTER);
	}
	
	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
	}
	private void actionEvents() {
		findFlightButton.setOnAction(event -> {
			flightList = findFlights();
		});
		
		bookFlightButton.setOnAction(event -> {
			if (!flightView.getSelectionModel().isEmpty()) {
				new CheckBaggageWindow(flightView.getSelectionModel().getSelectedItem());
			}
			else {
				MessageBox.message(AlertType.INFORMATION, "No Flight Selected", "You must select a flight to book");
			}
		});
	}
	
	private void populateComboBox() {
		ArrayList<Airport> aList = APZState.loadAirports();

		if(!aList.isEmpty()) {
			
			destinationBox.setValue(aList.get(0).toString());
			for (int i = 0; i < aList.size(); i++) 
				destinationBox.getItems().add(aList.get(i).toString());	
		}
	}

	private ArrayList<Flight> findFlights() {
		ArrayList<Flight> searchFlights = AdminState.loadFlights();
		ArrayList<Flight> flightsFound = new ArrayList<>();
		if(destinationBox.getSelectionModel().getSelectedItem().equals(null)) {
			MessageBox.message(AlertType.ERROR, "ERROR: Invalid Data Entry", "You must enter a destination");
		}
		else {	
			String destination = destinationBox.getSelectionModel().getSelectedItem();
			for (int i = 0; i < searchFlights.size(); i ++) {
				if (destination.equals(searchFlights.get(i).getDestinationAirport().toString())) {
					flightList.add(searchFlights.get(i));
				}
			}
			if (!flightView.getItems().isEmpty())
				flightView.getItems().clear();
			for (int i = 0; i < flightList.size(); i++)
				flightView.getItems().add(flightList.get(i));
			if (flightView.getItems().isEmpty()) {
				MessageBox.message(AlertType.INFORMATION, "No Flights Found", "There are no flights going to " + destination);
			}
		}
		return flightsFound;
	}
}
