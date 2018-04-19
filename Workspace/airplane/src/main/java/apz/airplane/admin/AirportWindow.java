package apz.airplane.admin;

import java.util.ArrayList;

import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

import apz.airplane.model.Airport;
import apz.airplane.model.Province;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AirportWindow {

	private Text windowHeader;
	private HBox buttonBox;
	private ArrayList<Airport> airportList;
	private ListView<String> airportView;
	private TextField airportNameField;
	private ComboBox<String> airportProvinceBox;
	private Button createButton, removeButton;
	private VBox mainPane;
	private GridPane gridPane;
	private Text airportName;
	private Text city;
	private HBox removeButtonBox;
	private HBox createButtonBox;


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
		removeButtonBox = new HBox(10);
		createButtonBox = new HBox(10);
		gridPane = new GridPane();
		airportView = new ListView<>();
		airportNameField = new TextField();
		airportProvinceBox = new ComboBox<>();
		createButton = new Button("Create Airport");
		removeButton = new Button("Remove");
		windowHeader = new Text("Make Airport");
		airportName = new Text("Airport Name: ");
		city = new Text("City Name: ");

	}

	public void content() {
		loadFile();
		populateProvince();
		removeButton.setMaxWidth(Double.MAX_VALUE);
		createButton.setMaxWidth(Double.MAX_VALUE);
		buttonBox.getChildren().addAll(createButton, removeButton);
		//mainPane.getChildren().addAll(new HBox(windowHeader), new Label("Airport Name"), airportNameField, new Label("City"),
		//		airportProvinceBox, buttonBox, airportView);
		HBox.setHgrow(createButton, Priority.ALWAYS);
		HBox.setHgrow(removeButton, Priority.ALWAYS);
		//createButtonBox.getChildren().add(createButton);
		//removeButtonBox.getChildren().add(removeButton);
		formatHeader();
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		System.out.println(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(airportName, 0, 0);
		gridPane.add(airportNameField, 1, 0);
		gridPane.add(city, 0, 1);
		gridPane.add(airportProvinceBox, 1, 1);
		gridPane.add(removeButton, 1, 2);
		gridPane.add(createButton, 1, 3);
		gridPane.add(airportView, 0, 4);
		
		mainPane.getChildren().addAll(windowHeader, gridPane, airportView);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	public void formatHeader() {
		windowHeader.setStyle("-fx-font: 24 arial;");
		windowHeader.setTextAlignment(TextAlignment.CENTER);
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
		stage.setScene(new Scene(mainPane, 400, 350));
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
