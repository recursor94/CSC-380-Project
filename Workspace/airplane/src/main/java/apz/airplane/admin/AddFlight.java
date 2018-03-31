package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	private VBox mainPane = new VBox(10);
	private ArrayList<Flight> flightList = new ArrayList<>();
	private ListView<Flight> flights = new ListView<>();
	private DatePicker departDate;
	private DatePicker arriveDate;
	
	static ComboBox<String> departField = new ComboBox<>();
	static ComboBox<String> arriveField = new ComboBox<>();
	static ComboBox<String> arriveTimeBox = new ComboBox<>();
	static ComboBox<String> departTimeBox = new ComboBox<>();

	public AddFlight(Stage mainStage) {
		loadFile();
		ComboBox<Airplane> planeBox = new ComboBox<>();
		
		ArrayList<Airplane> planeList = State.loadPlanes();
		for (int i = 0; i < planeList.size(); i++) {
			planeBox.getItems().add(planeList.get(i));
		}
		if (!planeList.isEmpty())
			planeBox.setValue(planeList.get(0));

		Button createFlightButton = new Button("Create Flight");
		Button createAirportButton = new Button("Create Airport");
		Button removeFlightButton = new Button("Remove Flight");
		TextField flightNumField = new TextField();
		departDate = new DatePicker();
		departDate.setEditable(false);
		arriveDate = new DatePicker();
		arriveDate.setEditable(false);
		
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
		populateComboBoxes();
		
		createAirportButton.setOnAction(event -> {
			new CreateAirport();
		});

		mainPane.getChildren().addAll(new Label("Plane Selection"), planeBox, new Label("Flight Number"), flightNumField, new Label("Departure Airport"), departField,
				new Label("Arrival Airport"), arriveField, new Label("Departure Time"), departTimeBox, new Label("Arrival Time"), arriveTimeBox, createAirportButton, new Label("Departure Date"), departDate,
				new Label("Arrival Date"), arriveDate, createFlightButton, flights, removeFlightButton);

		Stage stage = new Stage();
		stage.initOwner(mainStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.setScene(new Scene(mainPane, 500, 800));
		stage.show();

		createFlightButton.setOnAction(event -> {
			if (!planeBox.getSelectionModel().isEmpty() && !departTimeBox.getSelectionModel().isEmpty() && !arriveTimeBox.getSelectionModel().isEmpty()
					&& !arriveField.getSelectionModel().isEmpty() && !departField.getSelectionModel().isEmpty() && departDate != null 
					&& arriveDate != null && !flightNumField.getText().isEmpty()) {
				Airplane plane = planeBox.getSelectionModel().getSelectedItem();
				Time departure = new Time (departTimeBox.getSelectionModel().getSelectedItem());
				Time arrival = new Time (arriveTimeBox.getSelectionModel().getSelectedItem());
				String outgoing = departField.getSelectionModel().getSelectedItem();
				String incoming = arriveField.getSelectionModel().getSelectedItem();
				Airport departingAirport= new Airport(outgoing, incoming);
				LocalDate leaving = departDate.getValue();
				LocalDate arriving = arriveDate.getValue();
				int flightNum = Integer.valueOf(flightNumField.getText());
				flightList.add(new Flight (plane, null, null, arriving, leaving, arrival, departure, flightNum));//TODO:  FIX THIS. Use real airports
				State.saveFlight(flightList);
				loadFile();
			}
			else {
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter data into all fields");
			}
		});
		
		
		removeFlightButton.setOnAction(event -> {
			Flight flight = flights.getSelectionModel().getSelectedItem();
			flightList.remove(flight);
			State.saveFlight(flightList);
			loadFile();
		});
	}
	
	public static void populateComboBoxes() {
		departField.getItems().clear();
		arriveField.getItems().clear();
		
		ArrayList<String> airportList = State.loadAirports();
		for (int i = 0; i < airportList.size(); i++) {
			departField.getItems().add(airportList.get(i));
			arriveField.getItems().add(airportList.get(i));
		}
		
		departField.setValue("Select an Airport");
		arriveField.setValue("Select an Airport");
	}
	public void loadFile() {
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("flightObject.apz");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			//System.out.println("The Object has been read from the file");
			objectIn.close();
			flightList = (ArrayList<Flight>) obj;

			if (!flights.getItems().isEmpty())
				flights.getItems().clear();
			for (int i = 0; i < flightList.size(); i++)
				flights.getItems().add(flightList.get(i));

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
