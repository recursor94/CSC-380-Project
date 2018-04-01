package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Airport;
import apz.airplane.util.MessageBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAirport {
	
	private ArrayList<Airport> airportList = new ArrayList<>();
	private VBox mainPane = new VBox(10);
	private ListView<String> airports = new ListView<>();

	
	public CreateAirport() {
		loadFile();
		airportList = State.loadAirports();
		TextField airportField = new TextField();
		Button createButton = new Button("Create Airport");
		Button removeButton = new Button("Remove");
		Button viewButton = new Button("View");
		HBox buttons = new HBox(10);
		buttons.getChildren().addAll(createButton, removeButton, viewButton);
		
		
		createButton.setOnAction(event -> {
			if (!airportField.getText().isEmpty()) {
			//The airport name and city will be split by a comma and a space	
				String[] airportFieldPartition = airportField.getText().split(", ");
				Airport airport = new Airport(airportFieldPartition[0], airportFieldPartition[1]);
				airportList.add(airport);
				
				airports.getItems().add(airportField.getText());
				State.saveAirports(airportList);
				AddFlight.populateComboBoxes();
			}
			else {
				MessageBox.message(AlertType.ERROR, "No Data Entered", "You must enter an aiport name");
			}
		});
		
		viewButton.setOnAction(event -> {
			System.out.println(airportList);
		});
		
		removeButton.setOnAction(event -> {
			String sAirport = airports.getSelectionModel().getSelectedItem();
			for(Airport airport : airportList) {
				//iterate through all airports, if the toStrings match with user selected item, remove it from the database
				if(airport.toString().equals(sAirport)) {
					airportList.remove(airport);
					break;
				}
			}
			State.saveAirports(airportList);
			AddFlight.populateComboBoxes();
			loadFile();
		});
		mainPane.getChildren().addAll(new Label("Airport Name"), airportField, buttons, airports);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 300, 450));
		stage.setTitle("Create Airport");
		stage.show();
	}
	public void loadFile() {
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("airportobject.apz");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			//System.out.println("The Object has been read from the file");
			objectIn.close();
			airportList = (ArrayList<Airport>) obj;

			if (!airports.getItems().isEmpty())
				airports.getItems().clear();
			for (int i = 0; i < airportList.size(); i++)
				airports.getItems().add(airportList.get(i).toString());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
