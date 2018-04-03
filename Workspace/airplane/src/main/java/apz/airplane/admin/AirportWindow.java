package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Airport;
import apz.airplane.util.MessageBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AirportWindow {

	private VBox mainPane;
	private HBox buttonBox;
	private ArrayList<Airport> airportList;
	private ListView<String> airportView;
	private TextField airportNameField, airportCityField;
	private Button createButton, removeButton, viewButton;
	private Airport selectedAirport; //to be used in change interface
	private Scene activeScene;

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
			else if(selectedAirport != null) {
				selectedAirport.setName(airportNameField.getText());
				selectedAirport.setCity(airportCityField.getText());
				int selectedIndex = airportView.getSelectionModel().getSelectedIndex();
				airportView.getItems().set(selectedIndex, selectedAirport.toString()); //set the selected item in the list view to the new airport value
				AdminState.saveAirports(airportList);
				FlightWindow.populateComboBoxes();
			}
			else {
				Airport airport = new Airport(airportNameField.getText(), airportCityField.getText());
				airportList.add(airport);

				airportView.getItems().add(airport.getName() + ", " + airport.getCity());
				AdminState.saveAirports(airportList);
				FlightWindow.populateComboBoxes();
			}
		});

		viewButton.setOnAction(event -> {
			System.out.println(airportList);
		});

		removeButton.setOnAction(event -> {
			String sAirport = airportView.getSelectionModel().getSelectedItem();
			Airport airport = findAirport(sAirport);
				if (findAirport(sAirport) != null) {
					airportList.remove(airport);
				}
			AdminState.saveAirports(airportList);
			FlightWindow.populateComboBoxes();	// NEED TO CHANGE BUT THIS UPDATES COMBOBOX. NEED TO HAVE STATIC BOXES WITH THESE DATA INSIDE?
			loadFile();
		});
		airportView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("Selected airport " + newValue);
				
				selectedAirport = findAirport(newValue);
				
				//if an airport is selected, change interface buttons and field labels to the change interface
				if(selectedAirport != null) {
					createButton.setText("Change Airport");
					airportNameField.setText(selectedAirport.getName());
					airportCityField.setText(selectedAirport.getCity());
				}
				else {
					createButton.setText("Create Airport");
					airportNameField.setText("");
					airportCityField.setText("");
				}
			}
			
		});
		activeScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ESCAPE) {
					airportView.getSelectionModel().clearSelection();

				}
				
			}
		});
	}

	public void properties() {
		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 300, 450));
		stage.setTitle("Create Airport");
		stage.show();
		activeScene = stage.getScene(); //used for getting escape key press
	}

	public AirportWindow() {
		initialize();
		content();
		properties(); //I have to swap properties and action event, otherwise activeScene wont exist when trying to add key event
		actionEvents();
	}
	private Airport findAirport(String sAirport) {
		//For searching database of airports for airport by string
		sAirport = airportView.getSelectionModel().getSelectedItem();
		for (Airport airport : airportList) {
			if (airport.toString().equals(sAirport)) {
				return airport;
			}
		}
		return null;
	}

	public void loadFile() {
		airportList = AdminState.loadAirports();
		if (!airportView.getItems().isEmpty())
			airportView.getItems().clear();
		for (int i = 0; i < airportList.size(); i++)
			airportView.getItems().add(airportList.get(i).toString());
	}
	
}