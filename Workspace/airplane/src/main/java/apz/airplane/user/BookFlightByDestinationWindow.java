package apz.airplane.user;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.admin.AdminState;
import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.Province;
import apz.airplane.model.User;
import apz.airplane.util.MessageBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BookFlightByDestinationWindow {
	
	private VBox mainPane;
	private GridPane rootPane;
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
		rootPane = new GridPane();
		mainPane = new VBox(10);
		flightView = new ListView <>();
	    flightList = new ArrayList<>();
		findFlightButton = new Button("Find Flights");
		bookFlightButton = new Button("Book Flight");
		destinationBox = new ComboBox<>();
	}
	
	private void content() {
		populateProvince();
		rootPane.setHgap(10);
		rootPane.setVgap(10);
		
		rootPane.add(new Label("Select your desired destination"), 0, 0);
		rootPane.add(destinationBox, 1, 0);
		
		rootPane.add(findFlightButton, 0, 1);
		rootPane.add(flightView, 0, 2);
		rootPane.add(bookFlightButton, 0, 3);
		mainPane.getChildren().addAll(new Label("Select your desired destination"), destinationBox, findFlightButton, flightView, bookFlightButton);
	}
	
	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setHeight(425);
		APZLauncher.getStage().setWidth(500);
	}
	private void actionEvents() {
		findFlightButton.setOnAction(event -> {
			flightList = findFlights();
		});
		
		bookFlightButton.setOnAction(event -> {
			if (!flightView.getSelectionModel().isEmpty()) {
				User user = APZLauncher.getCurrentUser();
				user.addTrip(new Booking(flightView.getSelectionModel().getSelectedItem(), LocalDate.now(), user));
				MessageBox.message(AlertType.INFORMATION, "Successful Booking", "Your flight has been booked!");
				apz.airplane.util.APZState.saveInformation();
				System.out.println(user.getTripList());
			}
			else {
				MessageBox.message(AlertType.INFORMATION, "No Flight Selected", "You must select a flight to book");
			}
		});
	}
	
	private void populateProvince() {
		ArrayList<Province> pList = Province.getProvinces();

		destinationBox.setValue(Province.getCityName(pList.get(0)));

		for (int i = 0; i < pList.size(); i++)
			destinationBox.getItems().add(Province.getCityName(pList.get(i)));
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
				if (destination.equals(searchFlights.get(i).getDestinationAirport().getCity())) {
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
