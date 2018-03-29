package zak;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Flight;
import apz.airplane.Time;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddFlightZak {
	private VBox mainPane = new VBox(10);
	private ArrayList<FlightZak> flightList = new ArrayList<>();
	private ListView<FlightZak> flights = new ListView<>();
	private DatePicker departDate;
	private DatePicker arriveDate;
	
	static ComboBox<String> departField = new ComboBox<>();
	static ComboBox<String> arriveField = new ComboBox<>();
	static ComboBox<String> arriveTimeBox = new ComboBox<>();
	static ComboBox<String> departTimeBox = new ComboBox<>();

	public AddFlightZak(Stage mainStage) {
		loadFile();
		ComboBox<AirplaneZak> planeBox = new ComboBox<>();
		
		ArrayList<AirplaneZak> planeList = StateZakAdmin.loadPlanes();
		for (int i = 0; i < planeList.size(); i++) {
			planeBox.getItems().add(planeList.get(i));
		}
		if (!planeList.isEmpty())
			planeBox.setValue(planeList.get(0));

		Button createFlightButton = new Button("Create Flight");
		Button createAirportButton = new Button("Create Airport");
		Button removeFlightButton = new Button("Remove Flight");
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
			new CreateAirportZak();
		});

		mainPane.getChildren().addAll(new Label("Plane Selection"), planeBox, new Label("Departure Airport"), departField,
				new Label("Arrival Airport"), arriveField, new Label("Departure Time"), departTimeBox, new Label("Arrival Time"), arriveTimeBox, createAirportButton, new Label("Departure Date"), departDate,
				new Label("Arrival Date"), arriveDate, createFlightButton, flights, removeFlightButton);

		Stage stage = new Stage();
		stage.initOwner(mainStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.setScene(new Scene(mainPane, 500, 700));
		stage.show();

		createFlightButton.setOnAction(event -> {
			if (!planeBox.getSelectionModel().isEmpty() && !departTimeBox.getSelectionModel().isEmpty() && !arriveTimeBox.getSelectionModel().isEmpty()
					&& !arriveField.getSelectionModel().isEmpty() && !departField.getSelectionModel().isEmpty() && !departDate.equals(null) && !arriveDate.equals(null)) {
				AirplaneZak plane = planeBox.getSelectionModel().getSelectedItem();
				TimeZak departure = new TimeZak (departTimeBox.getSelectionModel().getSelectedItem());
				TimeZak arrival = new TimeZak (arriveTimeBox.getSelectionModel().getSelectedItem());
				String outgoing = departField.getSelectionModel().getSelectedItem();
				String incoming = arriveField.getSelectionModel().getSelectedItem();
				LocalDate leaving = departDate.getValue();
				LocalDate arriving = arriveDate.getValue();
				flightList.add(new FlightZak (plane, outgoing, incoming, arriving, leaving, arrival, departure, 102));
				StateZakAdmin.saveFlight(flightList);
				loadFile();
			}
			else 
			{
				MessageBoxZak.message(AlertType.INFORMATION, "Invalid Data Entry", "You must enter data into all fields");
			}
			
		});
		
		removeFlightButton.setOnAction(event -> {
			FlightZak flight = flights.getSelectionModel().getSelectedItem();
			flightList.remove(flight);
			StateZakAdmin.saveFlight(flightList);
			loadFile();
		});
	}
	
	public static void populateComboBoxes() {
		departField.getItems().clear();
		arriveField.getItems().clear();
		
		ArrayList<String> airportList = StateZakAdmin.loadAirports();
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
			fileIn = new FileInputStream("flightObject.zak");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			flightList = (ArrayList<FlightZak>) obj;

			if (!flights.getItems().isEmpty())
				flights.getItems().clear();
			for (int i = 0; i < flightList.size(); i++)
				flights.getItems().add(flightList.get(i));

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
