package apz.airplane.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import apz.airplane.model.Airplane;
import apz.airplane.model.Airport;
import apz.airplane.model.Flight;
import apz.airplane.model.Province;
import apz.airplane.model.Time;
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
	
	public GridPane gridPane;
	public VBox mainPane; 
	public ArrayList<Flight> flightList;
	public ListView<Flight> flightView;
	public TextField flightNumField;
	public DatePicker departDatePicker, arriveDatePicker;
	public static ComboBox<Airplane> planeBox;
	public static ComboBox<String> departAirportBox, arriveAirportBox, arriveTimeBox, departTimeBox;
	public Button createFlightButton, createAirportButton, removeFlightButton, autoGenerateButton;
	public Text header, flightText;

	public FlightWindow(Stage mainStage) {
		initialize();
		content();
		actionEvents();
		properties(mainStage);
	}
	
	public void initialize() {
		gridPane = new GridPane();
		mainPane = new VBox(10);
		flightList = new ArrayList<>();
		flightView = new ListView<>();
		flightNumField = new TextField();
		departDatePicker = new DatePicker();
		arriveDatePicker = new DatePicker();
		planeBox = new ComboBox<>();
		arriveAirportBox = new ComboBox<>();
		departAirportBox = new ComboBox<>();
		arriveTimeBox = new ComboBox<>();
		departTimeBox = new ComboBox<>();
		createFlightButton = new Button("Create Flight");
		createAirportButton = new Button("Create Airport");
		removeFlightButton = new Button("Remove Flight");
		autoGenerateButton = new Button("Generate Flights");
		header = new Text("Create Flights");
		flightText = new Text("List of Created Flights");
	}

	public void content() {
		addTimeToBox();
		loadPlanes();
		loadFlights();
		populateComboBoxes();
		
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
		
		gridPane.add(createFlightButton, 0, 8);
		gridPane.add(autoGenerateButton, 1, 8);
		
		mainPane.getChildren().addAll(header, new Separator(), gridPane, 
				new Separator(), flightText, flightView, removeFlightButton);
	}

	public void actionEvents() {
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
			removeFlightButton.setDisable(true);
			resetFields();
		});
		
		autoGenerateButton.setOnAction(event -> {
			autoGenerate();
		});
		
		flightView.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE)
				resetFields();
		});
		
		flightView.getSelectionModel().selectedItemProperty().addListener(event -> {
			removeFlightButton.setDisable(false);
			Flight flight = flightView.getSelectionModel().getSelectedItem();
			if(flight != null) {
				planeBox.setValue(flight.getPlane());
				flightNumField.setText(flight.getFlightNum() + "");
				departAirportBox.setValue(flight.getDepartureAirport().toString());
				arriveAirportBox.setValue(flight.getDestinationAirport().toString());
				departDatePicker.setValue(flight.getDepartureDate());
				arriveDatePicker.setValue(flight.getArriveDate());
				departTimeBox.setValue(flight.getDepartureTime().getTimeString());
				arriveTimeBox.setValue(flight.getArrivalTime().getTimeString());			
			}
		});
		
		flightNumField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (!"0123456789".contains(keyEvent.getCharacter()))
		            keyEvent.consume();
	        }
	      });
	}

	public void properties(Stage mainStage) {
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
		gridPane.setAlignment(Pos.CENTER);
		mainPane.setAlignment(Pos.CENTER);
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
		
		if (departAirportBox.getItems().size() < 2 || arriveAirportBox.getItems().size() < 2 || 
				departAirportBox.getItems().size() < 2 && arriveAirportBox.getItems().size() < 2) {
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
			
			//Check to see if the flight number is unique 
			boolean result = false;
			
			for (int i = 0; i < flightList.size(); i ++) {
				if(Integer.valueOf(flightNumField.getText()) == flightList.get(i).getFlightNum()) {
					result = true;
					break;
				}
			}
			if(result)
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter a unique flight number");
			//Check to make sure the depart date is before (or the same as) the arrive date
			else if(departDatePicker.getValue().isAfter(arriveDatePicker.getValue())) 
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your departure date can not be after your arrival date");
			//Check to make sure you are not creating a flight in the past
			else if(departDatePicker.getValue().isBefore(LocalDate.now()))
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your flight cannot depart on a date that has already past");
			//Check to make sure the airports for departure and arrival are different
			else if(departAirportBox.getSelectionModel().getSelectedItem().equals(arriveAirportBox.getSelectionModel().getSelectedItem()))
					MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your departure airport cannot be the same as your arrival airport");
			else {
				int flightNum = Integer.valueOf(flightNumField.getText());
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
				
				//If the dates are the same, check to see if the depart time is before the arrive time
				if((departure.getTimeDouble() >= arrival.getTimeDouble()) && leaving.equals(arriving)) {
					MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "Your arrival time must be after your departure time");
				}
				else {
						flightList.add(new Flight(plane, outgoingAirport, incomingAirport, arriving, leaving, arrival,
								departure, flightNum));
						AdminState.saveFlight(flightList);
						loadFlights();
						resetFields();
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
		planeBox.getItems().clear();
		ArrayList<Airplane> planeList = AdminState.loadPlanes();
		for (int i = 0; i < planeList.size(); i++) 
			planeBox.getItems().add(planeList.get(i));
		if (!planeList.isEmpty())
			planeBox.setValue(planeList.get(0));
	}
	
	private void autoGenerate() {
		ArrayList<Airplane> planes = new ArrayList<>();
		ArrayList<String> airlines = new ArrayList<>();
		
		//Make Airlines
		airlines.add("Delta");
		airlines.add("Southwest");
		airlines.add("United");
		airlines.add("Jet Blue");
		airlines.add("American");
		Random rand = new Random();
		
		//Make Planes
		for(int i = 0; i < 16; i ++) {
			int airlineNum = rand.nextInt(5);
			planes.add(new Airplane(i + 1, airlines.get(airlineNum), 80));
		}
		
		//Make Airports
		ArrayList<Airport> airports = new ArrayList<>();
		airports.add(new Airport("JFK", Province.strNY));
		airports.add(new Airport("SYR", Province.strNY));
		airports.add(new Airport("SFO", Province.strCA));
		airports.add(new Airport("LAX", Province.strCA));
		airports.add(new Airport("AUSTIN", Province.strTX));
		airports.add(new Airport("TAMPA", Province.strFL));
		airports.add(new Airport("FT MYERS", Province.strFL));
		airports.add(new Airport("PHI", Province.strPA));
		
		//Make Flights
		int flightNum = 1;
		ArrayList<Flight> flights = new ArrayList<>();

		for (int i = 0; i < 22; i ++) {
			LocalDate depart = LocalDate.of(2018, 5, i + 10);
			LocalDate arrive = LocalDate.of(2018, 5, i + 10);
			
			for (int j = 0; j < 2; j ++) {
				Time departTime = new Time("11:30 AM");
				Time arriveTime = new Time("2:30 PM");
				int airportDepartNum = rand.nextInt(4);
				int airportArriveNum = rand.nextInt(4) + 4;
				flights.add(new Flight(planes.get(j), airports.get(airportDepartNum), airports.get(airportArriveNum), 
						arrive, depart, arriveTime, departTime, flightNum));
				flightNum++;
			}
			for (int k = 2; k < 5; k ++) {
				Time departTime = new Time("2:00 PM");
				Time arriveTime = new Time("4:30 PM");
				int airportNum = rand.nextInt(4);
				flights.add(new Flight(planes.get(k), airports.get(airportNum), airports.get(airportNum  + k - 1), 
						arrive, depart, arriveTime, departTime, flightNum));
				flightNum++;
			}
			for (int l = 5; l < 7; l ++) {
				Time departTime = new Time("5:00 PM");
				Time arriveTime = new Time("8:30 PM");
				int airportDepartNum = rand.nextInt(4) + 4;
				int airportArriveNum = rand.nextInt(4);
				flights.add(new Flight(planes.get(l), airports.get(airportDepartNum), airports.get(airportArriveNum), 
						arrive, depart, arriveTime, departTime, flightNum));
				flightNum++;
			}
			for (int m = 7; m < 10; m ++) {
				Time departTime = new Time("7:30 PM");
				Time arriveTime = new Time("10:00 PM");
				int airportNum = rand.nextInt(4) + 4;
				flights.add(new Flight(planes.get(m), airports.get(airportNum), airports.get(airportNum + 6 - m), 
						arrive, depart, arriveTime, departTime, flightNum));
				flightNum++;
			}
			for (int n = 10; n < 13; n ++) {
				Time departTime = new Time("9:00 PM");
				Time arriveTime = new Time("11:00 PM");
				int airportNum = rand.nextInt(4);
				flights.add(new Flight(planes.get(n), airports.get(airportNum + n - 9), airports.get(airportNum), 
						arrive, depart, arriveTime, departTime, flightNum));
				flightNum++;
			}
			for (int o = 13; o < 16; o++) {
				Time departTime = new Time("10:00 PM");
				Time arriveTime = new Time("11:30 PM");
				int airportNum = rand.nextInt(4) + 4;
				flights.add(new Flight(planes.get(o), airports.get(airportNum + 12 - o), airports.get(airportNum), 
						arrive, depart, arriveTime, departTime, flightNum));
				flightNum++;
			}
		}
		
		//Save the info
		AdminState.saveAirports(airports);
		AdminState.saveFlight(flights);
		AdminState.savePlane(planes);
		loadPlanes();
		loadFlights();
		populateComboBoxes();
	}
}
