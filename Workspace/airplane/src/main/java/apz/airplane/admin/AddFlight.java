package apz.airplane.admin;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Airport;
import apz.airplane.Flight;
import apz.airplane.Time;
import apz.airplane.util.MessageBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddFlight {
	private VBox mainPane;
	private ArrayList<Flight> flightList;
	private ListView<Flight> flightView;
	private TextField flightNumField;
	private DatePicker departDatePicker, arriveDatePicker;
	private static ComboBox<Airplane> planeBox;
	private static ComboBox<String> departAirportBox, arriveAirportBox;
	private static ComboBox<String> arriveTimeBox, departTimeBox;
	private Button createFlightButton, createAirportButton, removeFlightButton;

	public AddFlight(Stage mainStage) {
		initialize();
		content();
		actionEvents();
		properties(mainStage);
	}
	
	private void initialize() {
		mainPane = new VBox(10);
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

		addTimeToBox();
		loadPlanes();
		loadFlights();
		populateComboBoxes();
	}

	private void content() {
		arriveDatePicker.setEditable(false);
		departDatePicker.setEditable(false);

		mainPane.getChildren().addAll(new Label("Plane Selection"), planeBox, new Label("Flight Number"),
				flightNumField, new Label("Departure Airport"), departAirportBox, new Label("Arrival Airport"),
				arriveAirportBox, createAirportButton, new Label("Departure Time"), departTimeBox, new Label("Arrival Time"), arriveTimeBox,
				new Label("Departure Date"), departDatePicker, new Label("Arrival Date"),
				arriveDatePicker, createFlightButton, flightView, removeFlightButton);
	}

	private void actionEvents() {
		createAirportButton.setOnAction(event -> {
			new CreateAirport();
		});

		createFlightButton.setOnAction(event -> {
			if (!planeBox.getSelectionModel().isEmpty() && !departTimeBox.getSelectionModel().isEmpty()
					&& !arriveTimeBox.getSelectionModel().isEmpty() && !arriveAirportBox.getSelectionModel().isEmpty()
					&& !departAirportBox.getSelectionModel().isEmpty() && departDatePicker != null
					&& arriveDatePicker != null && !flightNumField.getText().isEmpty()) {
				Airplane plane = planeBox.getSelectionModel().getSelectedItem();
				Time departure = new Time(departTimeBox.getSelectionModel().getSelectedItem());
				Time arrival = new Time(arriveTimeBox.getSelectionModel().getSelectedItem());
				String outgoing = departAirportBox.getSelectionModel().getSelectedItem();
				String incoming = arriveAirportBox.getSelectionModel().getSelectedItem();
				String[] partitionOutgoing = outgoing.split(", "); // airport name and city will be seperated by a comma
																	// and a space
				String[] partitionIncoming = incoming.split(", ");
				Airport outgoingAirport = new Airport(partitionOutgoing[0], partitionOutgoing[1]);
				Airport incomingAirport = new Airport(partitionIncoming[0], partitionIncoming[1]);
				LocalDate leaving = departDatePicker.getValue();
				LocalDate arriving = arriveDatePicker.getValue();
				int flightNum = Integer.valueOf(flightNumField.getText());
				flightList.add(new Flight(plane, incomingAirport, outgoingAirport, arriving, leaving, arrival,
						departure, flightNum));// TODO: FIX THIS. Use real airports
				State.saveFlight(flightList);
				loadFlights();
			} else {
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter data into all fields");
			}
		});

		removeFlightButton.setOnAction(event -> {
			Flight flight = flightView.getSelectionModel().getSelectedItem();
			flightList.remove(flight);
			State.saveFlight(flightList);
			loadFlights();
		});
	}

	private void properties(Stage mainStage) {
		Stage stage = new Stage();
		stage.initOwner(mainStage);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 500, 800));
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

		ArrayList<Airport> airportList = State.loadAirports();
		for (int i = 0; i < airportList.size(); i++) {
			departAirportBox.getItems().add(airportList.get(i).toString());
			arriveAirportBox.getItems().add(airportList.get(i).toString());
		}

		departAirportBox.setValue("Select an Airport");
		arriveAirportBox.setValue("Select an Airport");
	}

	public void loadFlights() {
		flightList = State.loadFlights();
		if (!flightView.getItems().isEmpty())
			flightView.getItems().clear();
		for (int i = 0; i < flightList.size(); i++)
			flightView.getItems().add(flightList.get(i));
	}
	
	private void loadPlanes() { // DEFAULT TO GET VALUE WHEN OPEN PROGRAM
		ArrayList<Airplane> planeList = State.loadPlanes();
		for (int i = 0; i < planeList.size(); i++) {
			planeBox.getItems().add(planeList.get(i));
		}
		if (!planeList.isEmpty())
			planeBox.setValue(planeList.get(0));
	}

}
