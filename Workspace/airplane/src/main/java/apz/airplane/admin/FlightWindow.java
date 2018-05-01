package apz.airplane.admin;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.model.Airplane;
import apz.airplane.model.Airport;
import apz.airplane.model.Flight;
import apz.airplane.model.Time;
import apz.airplane.model.User;
import apz.airplane.model.UserController;
import apz.airplane.util.APZState;
import apz.airplane.util.IsInteger;
import apz.airplane.util.MessageBox;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FlightWindow {
	
	private GridPane gridPane;
	private VBox mainPane; 
	private ArrayList<Flight> flightList;
	private ListView<Flight> flightView;
	private TextField flightNumField;
	private DatePicker departDatePicker, arriveDatePicker;
	private static ComboBox<Airplane> planeBox;
	private static ComboBox<String> departAirportBox, arriveAirportBox, arriveTimeBox, departTimeBox;
	private Button createFlightButton, createAirportButton, removeFlightButton;
	private Text header, flightText;

	public FlightWindow(Stage mainStage) {
		initialize();
		content();
		actionEvents();
		properties(mainStage);
	}
	
	private void initialize() {
		gridPane = new GridPane();
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
		header = new Text("Create Flights");
		flightText = new Text("List of Created Flights");
	}

	private void content() {
		addTimeToBox();
		loadPlanes();
		loadFlights();
		populateComboBoxes();
		
		header.setFont(new Font(32));
		flightText.setFont(new Font(18));
		
		arriveDatePicker.setMaxWidth(200);
		departDatePicker.setMaxWidth(200);
		arriveAirportBox.setMaxWidth(200);
		departAirportBox.setMaxWidth(200);
		arriveTimeBox.setMaxWidth(200);
		departTimeBox.setMaxWidth(200);
		planeBox.setMaxWidth(200);
		
		removeFlightButton.setDisable(true);
		
		arriveDatePicker.setEditable(false);
		departDatePicker.setEditable(false);
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		gridPane.add(new Label("Plane Selection"), 0, 0);
		gridPane.add(planeBox, 1, 0);
		
		gridPane.add(new Label("Flight Number"), 0, 1);
		gridPane.add(flightNumField, 1, 1);
		
		gridPane.add(new Label("Departure Airport"), 0, 2);
		gridPane.add(new Label("Arrival Airport"), 1, 2);
		
		gridPane.add(departAirportBox, 0, 3);
		gridPane.add(arriveAirportBox, 1, 3);
		
		gridPane.add(createAirportButton, 2, 3);
		
		gridPane.add(new Label("Departure Time"), 0, 4);
		gridPane.add(new Label("Arrival Time"), 1, 4);
		
		gridPane.add(departTimeBox, 0, 5);
		gridPane.add(arriveTimeBox, 1, 5);
		
		gridPane.add(new Label("Departure Date"), 0, 6);
		gridPane.add(new Label("Arrival Date"), 1, 6);
		
		gridPane.add(departDatePicker, 0, 7);
		gridPane.add(arriveDatePicker, 1, 7);
		
		gridPane.setAlignment(Pos.CENTER);
		
		mainPane.getChildren().addAll(header, new Separator(), gridPane, createFlightButton, new Separator(), flightText, flightView, removeFlightButton);
		
		mainPane.setAlignment(Pos.CENTER);
	}

	private void actionEvents() {
		createAirportButton.setOnAction(event -> {
			new AirportWindow();
			removeFlightButton.setDisable(true);
		});

		createFlightButton.setOnAction(event -> {
			verifyInput();
			
			if(flightView.getSelectionModel().isEmpty())
				removeFlightButton.setDisable(true);
		});
		

		removeFlightButton.setOnAction(event -> {
			Flight flight = flightView.getSelectionModel().getSelectedItem();
			flightList.remove(flight);
			AdminState.saveFlight(flightList);
			loadFlights();
			
			//This is not doing exactly what I want yet
			//removeFlights();
			removeFlightButton.setDisable(true);
			resetFields();
		});
		
		flightView.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE)
				resetFields();
		});
		
		flightView.getSelectionModel().selectedItemProperty().addListener(event -> {
			removeFlightButton.setDisable(false);
			Flight flight = flightView.getSelectionModel().getSelectedItem();
			if(flight != null) {
				//createFlightButton.setText("Change Flight");
				planeBox.setValue(flight.getPlane());
				flightNumField.setText(flight.getFlightNum() + "");
				departAirportBox.setValue(flight.getDepartureAirport().toString());
				arriveAirportBox.setValue(flight.getDestinationAirport().toString());
				departDatePicker.setValue(flight.getDepartureDate());
				arriveDatePicker.setValue(flight.getArriveDate());
				departTimeBox.setValue(flight.getDepartureTime().getTimeString());
				arriveTimeBox.setValue(flight.getArrivalTime().getTimeString());			
			}
//			else
//				createFlightButton.setText("Create Flight");
		});
		
		flightNumField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (!"0123456789".contains(keyEvent.getCharacter()))
		            keyEvent.consume();
	        }
	      });
	}

	private void properties(Stage mainStage) {
		Stage stage = new Stage();
		stage.initOwner(mainStage);
		stage.setTitle("Create Flights");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 600, 700));
		stage.setResizable(false);
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
	
	private void verifyInput() {
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
			//Check if the flight number is an integer
//			else if(!(IsInteger.isInteger(flightNumField.getText()))) {
//				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "The flight number must be an integer");
//			}
			else {
				Airplane plane = planeBox.getSelectionModel().getSelectedItem();
				Time departure = new Time(departTimeBox.getSelectionModel().getSelectedItem());
				Time arrival = new Time(arriveTimeBox.getSelectionModel().getSelectedItem());
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
					
					if(createFlightButton.getText().equals("Create Flight")) {
						flightList.add(new Flight(plane, outgoingAirport, incomingAirport, arriving, leaving, arrival,
								departure, flightNum));
						AdminState.saveFlight(flightList);
						loadFlights();
						resetFields();
					}
					else {
						for (int i = 0; i < flightList.size(); i++) {
							if (flightList.get(i) == flightView.getSelectionModel().getSelectedItem()) {
								flightList.get(i).setPlane(plane);
								flightList.get(i).setFlightNum(flightNum);
								flightList.get(i).setArrivalTime(arrival);
								flightList.get(i).setDepartureTime(departure);
								flightList.get(i).setArriveDate(arriving);
								flightList.get(i).setDepartureDate(leaving);
								flightList.get(i).setDepartureAirport(outgoingAirport);
								flightList.get(i).setDestinationAirport(incomingAirport);
								System.out.println("Found and changed!");
								AdminState.saveFlight(flightList);
								loadFlights();
								return;
							}
						}
					}
				}
			}
		} else 
			MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter data into all fields");
	}
	
	private void resetFields() {
		flightNumField.setText("");
		departAirportBox.setValue("Select an Airport");
		arriveAirportBox.setValue("Select an Airport");
		arriveDatePicker.setValue(null);
		departDatePicker.setValue(null);
		arriveTimeBox.setValue(null);
		departTimeBox.setValue(null);
		flightView.getSelectionModel().clearSelection();
		removeFlightButton.setDisable(true);
	}

	private void loadFlights() {
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
	
	//I Tried to get this to remove a flight from the user list, but it did not work
	private void removeFlights() {
		UserController uc = APZState.loadInformation();
		Flight flight = flightView.getSelectionModel().getSelectedItem();
		
		for(User user : uc.getUserList()) {
			user.removeTrip(flight);
		}
		flightList.remove(flight);
		AdminState.saveFlight(flightList);
		APZState.saveFlight(flightList);
		AdminState.saveInformation(uc);
		APZState.saveInformation(uc);
		loadFlights();
	}

}
