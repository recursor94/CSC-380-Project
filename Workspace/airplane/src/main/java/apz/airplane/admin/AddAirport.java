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

public class AddAirport {

	private VBox mainPane;
	private HBox buttonBox;
	private ArrayList<Airport> airportList;
	private ListView<String> airportView;
	private TextField airportNameField, airportCityField;
	private Button createButton, removeButton, viewButton;

	public void initialize() {
		airportList = new ArrayList<>();
		mainPane = new VBox(10);
		buttonBox = new HBox(10);
		airportView = new ListView<>();
		airportNameField = new TextField();
		airportCityField = new TextField();
		createButton = new Button("Create Airport");
		removeButton = new Button("Remove");
		viewButton = new Button("View");

		loadFile();
	}

	public void content() {
		buttonBox.getChildren().addAll(createButton, removeButton, viewButton);
		mainPane.getChildren().addAll(new Label("Airport Name"), new Label("Name"), airportNameField, new Label("City"), airportCityField, buttonBox, airportView);
	}

	public void actionEvents() {
		createButton.setOnAction(event -> {
			
			if (airportNameField.getText().isEmpty() && airportCityField.getText().isEmpty() || airportNameField.getText().isEmpty() || airportCityField.getText().isEmpty())
				MessageBox.message(AlertType.ERROR, "No Data Entered", "You must enter an aiport name");
			else {
				Airport airport = new Airport(airportNameField.getText(), airportCityField.getText());
				airportList.add(airport);

				airportView.getItems().add(airport.getName() + ", " + airport.getCity());
				State.saveAirports(airportList);
				AddFlight.populateComboBoxes();
			}
		});

		viewButton.setOnAction(event -> {
			System.out.println(airportList);
		});

		removeButton.setOnAction(event -> {
			String sAirport = airportView.getSelectionModel().getSelectedItem();
			for (Airport airport : airportList) {
				if (airport.toString().equals(sAirport)) {
					airportList.remove(airport);
					break;
				}
			}
			State.saveAirports(airportList);
			AddFlight.populateComboBoxes();	// NEED TO CHANGE BUT THIS UPDATES COMBOBOX. NEED TO HAVE STATIC BOXES WITH THESE DATA INSIDE?
			loadFile();
		});
	}

	public void properties() {
		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 300, 450));
		stage.setTitle("Create Airport");
		stage.show();
	}

	public AddAirport() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void loadFile() {
		airportList = State.loadAirports();
		if (!airportView.getItems().isEmpty())
			airportView.getItems().clear();
		for (int i = 0; i < airportList.size(); i++)
			airportView.getItems().add(airportList.get(i).toString());
	}
	
}
