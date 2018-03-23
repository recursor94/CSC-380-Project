package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminAddFlight {
	private VBox mainPane = new VBox(10);
	// private ArrayList<Flight> flightList = new ArrayList<>();
	private DatePicker departDate;
	private DatePicker arriveDate;
	
	static ComboBox<String> departField = new ComboBox<>();
	static ComboBox<String> arriveField = new ComboBox<>();
	static ComboBox<String> arriveTimeBox = new ComboBox<>();
	static ComboBox<String> departTimeBox = new ComboBox<>();

	public AdminAddFlight(Stage mainStage) {
		
		ComboBox<Airplane> planeBox = new ComboBox<>();
		
		ArrayList<Airplane> planeList = SaveState.loadPlanes();
		for (int i = 0; i < planeList.size(); i++) {
			planeBox.getItems().add(planeList.get(i));
		}
		if (!planeList.isEmpty())
			planeBox.setValue(planeList.get(0));

		Button createFlightButton = new Button("Create Flight");
		Button createAirportButton = new Button("Create Airport");
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

		mainPane.getChildren().addAll(new Label("Plane Selection"), planeBox, new Label("Departure Airport"), departField,
				new Label("Arrival Airport"), arriveField, arriveTimeBox, departTimeBox, createAirportButton, new Label("Departure Date"), departDate,
				new Label("Arrival Date"), arriveDate, createFlightButton);

		Stage stage = new Stage();
		stage.initOwner(mainStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.setScene(new Scene(mainPane, 400, 500));
		stage.show();

	}
	
	public static void populateComboBoxes() {
		departField.getItems().clear();
		arriveField.getItems().clear();
		
		ArrayList<String> airportList = SaveState.loadAirports();
		for (int i = 0; i < airportList.size(); i++) {
			departField.getItems().add(airportList.get(i));
			arriveField.getItems().add(airportList.get(i));
		}
		
		departField.setValue("Select a Airport");
		arriveField.setValue("Select a Airport");
	}
	

}
