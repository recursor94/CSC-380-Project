package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.model.Airport;
import apz.airplane.model.Province;
import apz.airplane.util.MessageBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AirportWindow {

	private VBox mainPane;
	private HBox buttonBox;
	private ArrayList<Airport> airportList;
	private ListView<String> airportView;
	private TextField airportNameField;
	private ComboBox<String> airportProvinceBox;
	private Button createButton, removeButton;

	public AirportWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		airportList = new ArrayList<>();
		mainPane = new VBox(10);
		buttonBox = new HBox(10);
		airportView = new ListView<>();
		airportNameField = new TextField();
		airportProvinceBox = new ComboBox<>();
		createButton = new Button("Create Airport");
		removeButton = new Button("Remove");
	}

	public void content() {
		loadFile();
		populateProvince();
		buttonBox.getChildren().addAll(createButton, removeButton);
		mainPane.getChildren().addAll(new Label("Airport Name"), airportNameField, new Label("City"),
				airportProvinceBox, buttonBox, airportView);
	}

	private void populateProvince() {
		ArrayList<Province> pList = Province.getProvinces();

		airportProvinceBox.setValue(Province.getCityName(pList.get(0)));

		for (int i = 0; i < pList.size(); i++)
			airportProvinceBox.getItems().add(Province.getCityName(pList.get(i)));
	}

	public void actionEvents() {

		createButton.setOnAction(event -> {
			if (airportNameField.getText().isEmpty() || airportNameField.getText().isEmpty())
				MessageBox.message(AlertType.ERROR, "No Data Entered", "You must enter an aiport name");
			else if (createButton.getText().equals("Create Airport"))
				createAirport(new Airport(airportNameField.getText(), airportProvinceBox.getValue()));
			else if (createButton.getText().equals("Change Airport"))
				changeAirport();
		});

		removeButton.setOnAction(event -> {
			if (airportView.getSelectionModel().getSelectedItem() != null) {
				Airport airport = findAirport();
				if (airport != null)
					airportList.remove(airport);
				AdminState.saveAirports(airportList);
				FlightWindow.populateComboBoxes(); // NEED TO CHANGE BUT THIS UPDATES COMBOBOX. NEED TO HAVE STATIC BOXES WITH THESE DATA INSIDE?
				loadFile();
			} else {
				MessageBox.message(AlertType.ERROR, "ERROR: No Airport Selected",
						"You must select an airport to remove");
			}
		});

		airportView.getSelectionModel().selectedItemProperty().addListener(event -> {
			String item = airportView.getSelectionModel().getSelectedItem();
			if (item != null) {
				createButton.setText("Change Airport");
				String[] airport = item.split(", ");
				airportNameField.setText(airport[0]);
				airportProvinceBox.setValue(airport[1]);
			}
			else 
				createButton.setText("Create Airport");
		});

		airportView.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE)
				resetFields();
		});
	}

	public void properties() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 300, 450));
		stage.setTitle("Create Airport");
		stage.show();
	}

	private void createAirport(Airport airport) {
		airportList.add(airport);
		airportView.getItems().add(airport.toString());
		AdminState.saveAirports(airportList);
		FlightWindow.populateComboBoxes();
		resetFields();
		loadFile();
	}
	
	private void changeAirport() {
		for (int i = 0; i < airportList.size(); i++) {
			if (airportList.get(i) == findAirport()) {
				airportList.get(i).setName(airportNameField.getText());
				airportList.get(i).setCity(airportProvinceBox.getValue());
				System.out.println("Found and changed!");
				AdminState.saveAirports(airportList);
				FlightWindow.populateComboBoxes();
				resetFields();
				loadFile();
				return;
			}
		}
	}

	private void resetFields() {
		airportNameField.setText("");
		airportProvinceBox.setValue(airportProvinceBox.getItems().get(0));
		airportView.getSelectionModel().clearSelection();
	}

	private Airport findAirport() {
		String sAirport = airportView.getSelectionModel().getSelectedItem();
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
