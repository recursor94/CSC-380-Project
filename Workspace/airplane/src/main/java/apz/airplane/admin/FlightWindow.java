package apz.airplane.admin;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Airport;
import apz.airplane.Flight;
import apz.airplane.Time;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FlightWindow {
	
	
//	private GridPane mainPane; Lets change it up.
	
	private VBox mainPane;
	private HBox planeSelectPane, flightNumPane, airportLblPane, airportBoxPane, timeLblPane, timeBoxPane, dateLblPane, datePickPane; 
	private ArrayList<Flight> flightList;
	private ListView<Flight> flightView;
	private TextField flightNumField;
	private DatePicker departDatePicker, arriveDatePicker;
	private static ComboBox<Airplane> planeBox;
	private static ComboBox<String> departAirportBox, arriveAirportBox, arriveTimeBox, departTimeBox;
	private Button createFlightButton, createAirportButton, removeFlightButton;

	public FlightWindow(Stage mainStage) {
		initialize();
		content();
		actionEvents();
		properties(mainStage);
	}
	
	private void initialize() {
		mainPane = new VBox(10);
		planeSelectPane = new HBox(10);
		flightNumPane = new HBox(10);
		airportLblPane = new HBox(60);
		airportBoxPane = new HBox(10);
		timeLblPane = new HBox(20);
		timeBoxPane = new HBox(10);
		dateLblPane = new HBox(130);
		datePickPane = new HBox(10);
		flightList = new ArrayList<>();
		flightView = new ListView<>();
		flightNumField = new TextField();
		arriveDatePicker = new DatePicker();
		departDatePicker = new DatePicker();
		planeBox = new ComboBox<>();
		arriveAirportBox = new ComboBox<>();
		departAirportBox = new ComboBox<>();
		arriveTimeBox = new ComboBox<>();
		departTimeBox = new ComboBox<>();
		createFlightButton = new Button("Create Flight");
		createAirportButton = new Button("Create Airport");
		removeFlightButton = new Button("Remove Flight");
	}

	private void content() {
		addTimeToBox();
		loadPlanes();
		loadFlights();
		populateComboBoxes();
		
		arriveDatePicker.setEditable(false);
		departDatePicker.setEditable(false);
		planeSelectPane.getChildren().addAll(new Label("Plane Selection"), planeBox);
		flightNumPane.getChildren().addAll(new Label("Flight Number"),flightNumField);
		airportLblPane.getChildren().addAll(new Label("Departure Airport"), new Label("Arrival Airport"));
		airportBoxPane.getChildren().addAll(departAirportBox, arriveAirportBox, createAirportButton);
		timeLblPane.getChildren().addAll(new Label("Departure Time"), new Label("Arrival Time"));
		timeBoxPane.getChildren().addAll(departTimeBox, arriveTimeBox);
		dateLblPane.getChildren().addAll(new Label("Departure Date"), new Label("Arrival Date"));
		datePickPane.getChildren().addAll(departDatePicker, arriveDatePicker, createFlightButton);
		mainPane.getChildren().addAll(planeSelectPane, flightNumPane, airportLblPane, airportBoxPane, timeLblPane,
				timeBoxPane, dateLblPane, datePickPane,flightView, removeFlightButton);
		mainPane.setAlignment(Pos.CENTER);
	}

	private void actionEvents() {
		createAirportButton.setOnAction(event -> {
			new AirportWindow();
		});

		createFlightButton.setOnAction(event -> {
			
			// Put message box first then have all others. Always == and !=
			if (!planeBox.getSelectionModel().isEmpty() && !departTimeBox.getSelectionModel().isEmpty()
					&& !arriveTimeBox.getSelectionModel().isEmpty() && !arriveAirportBox.getSelectionModel().isEmpty()
					&& !departAirportBox.getSelectionModel().isEmpty() && departDatePicker != null
					&& arriveDatePicker != null && !flightNumField.getText().isEmpty()) {
				
				//Check to make sure the depart date is before (or the same as) the arrive date
				if(departDatePicker.getValue().isAfter(arriveDatePicker.getValue())) 
					MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your departure date can not be after your arrival date");
				//Check to make sure you are not creating a flight in the past
				else if(departDatePicker.getValue().isBefore(LocalDate.now()))
					MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your flight cannot depart on a date that has already past");
				//Check to make sure the airports for departure and arrival are different
				else if(departAirportBox.getSelectionModel().getSelectedItem().equals(arriveAirportBox.getSelectionModel().getSelectedItem()))
						MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your departure airport cannot be the same as your arrival airport");
				else {
					Airplane plane = planeBox.getSelectionModel().getSelectedItem();
					Time departure = new Time(departTimeBox.getSelectionModel().getSelectedItem());
					Time arrival = new Time(arriveTimeBox.getSelectionModel().getSelectedItem());
					
					// Lets name outgoing and incoming self-explanatory variables
					String outgoing = departAirportBox.getSelectionModel().getSelectedItem();
					String incoming = arriveAirportBox.getSelectionModel().getSelectedItem();
					String[] partitionOutgoing = outgoing.split(", ");
					String[] partitionIncoming = incoming.split(", ");
					Airport outgoingAirport = new Airport(partitionOutgoing[0], partitionOutgoing[1]);
					Airport incomingAirport = new Airport(partitionIncoming[0], partitionIncoming[1]);
					LocalDate leaving = departDatePicker.getValue();
					LocalDate arriving = arriveDatePicker.getValue();
					int flightNum = Integer.valueOf(flightNumField.getText());
					
					//If the dates are the same, check to see if the depart time is before the arrive time
					if((departure.getTimeDouble() >= arrival.getTimeDouble()) && leaving.equals(arriving)) {
						MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your arrival time must be after your departure time");
					}
					else {
						flightList.add(new Flight(plane, outgoingAirport, incomingAirport, arriving, leaving, arrival,
								departure, flightNum));
						AdminState.saveFlight(flightList);
						loadFlights();
					}
				}
			} else 
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter data into all fields");
		});

		removeFlightButton.setOnAction(event -> {
			Flight flight = flightView.getSelectionModel().getSelectedItem();
			flightList.remove(flight);
			AdminState.saveFlight(flightList);
			loadFlights();
		});
	}

	private void properties(Stage mainStage) {
		Stage stage = new Stage();
		stage.initOwner(mainStage);
		stage.setTitle("Create Flights");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 600, 800));
		stage.show();
	}

	private void addTimeToBox() {
		for (int i = 1; i < 25; i++) {
			int num = i;
			String timeType = "AM";

			if (num == 12)
				timeType = "PM";

			if (num > 12) {
				if (num == 24)
					timeType = "AM";
				else
					timeType = "PM";
				num -= 12;
			}
			
			arriveTimeBox.getItems().add(num + ":00 " + timeType);
			departTimeBox.getItems().add(num + ":00 " + timeType);
			arriveTimeBox.getItems().add(num + ":30 " + timeType);
			departTimeBox.getItems().add(num + ":30 " + timeType);
		}
	}

	public static void populateComboBoxes() {
		departAirportBox.getItems().clear();
		arriveAirportBox.getItems().clear();

		ArrayList<Airport> airportList = AdminState.loadAirports();
		for (int i = 0; i < airportList.size(); i++) {
			departAirportBox.getItems().add(airportList.get(i).toString());
			arriveAirportBox.getItems().add(airportList.get(i).toString());
		}

		departAirportBox.setValue("Select an Airport");
		arriveAirportBox.setValue("Select an Airport");
		
		if (departAirportBox.getItems().size() < 2 || arriveAirportBox.getItems().size() < 2 || departAirportBox.getItems().size() < 2 && arriveAirportBox.getItems().size() < 2) {
			departAirportBox.setDisable(true);
			arriveAirportBox.setDisable(true);
		} else {
			departAirportBox.setDisable(false);
			arriveAirportBox.setDisable(false);
		}
	} 

	public void loadFlights() {
		flightList = AdminState.loadFlights();
		if (!flightView.getItems().isEmpty())
			flightView.getItems().clear();
		for (int i = 0; i < flightList.size(); i++)
			flightView.getItems().add(flightList.get(i));
	}
	
	private void loadPlanes() {
		ArrayList<Airplane> planeList = AdminState.loadPlanes();
		for (int i = 0; i < planeList.size(); i++) 
			planeBox.getItems().add(planeList.get(i));
		if (!planeList.isEmpty())
			planeBox.setValue(planeList.get(0));
	}

}
