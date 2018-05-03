package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.model.Airport;
import apz.airplane.model.Province;
import apz.airplane.util.MessageBox;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AirportWindow {

	private VBox mainPane, headerBox, buttonBox;
	private ArrayList<Airport> airportList;
	private TableView<Airport> airportView;
	private TableColumn<Airport, String> nameColumn, provinceColumn;
	private TextField airportNameField;
	private ComboBox<String> airportProvinceBox;
	private Button createButton, removeButton;
	private GridPane gridPane;
	private Text windowHeader, airportName, city;;
	private HBox removeButtonBox, createButtonBox;
	private Separator headerSeperator;

	public AirportWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		windowHeader = new Text("Manage Airports");
		mainPane = new VBox(20);
		headerBox = new VBox(0);
		buttonBox = new VBox(2);
		airportList = new ArrayList<>();
		airportView = new TableView<>();
		nameColumn = new TableColumn<>("Airport Name");
		provinceColumn = new TableColumn<>("City");
		airportNameField = new TextField();
		airportProvinceBox = new ComboBox<>();
		createButton = new Button("Create Airport");
		removeButton = new Button("Remove");
		gridPane = new GridPane();
		windowHeader = new Text("Manage Airports");
		airportName = new Text("Airport Name: ");
		city = new Text("City Name: ");
		removeButtonBox = new HBox(20);
		createButtonBox = new HBox(20);
		headerSeperator = new Separator(Orientation.HORIZONTAL);
	

	}

	public void content() {
		loadFile();
		populateProvince();
		formatHeader();
		headerBox.getChildren().add(headerSeperator);
		removeButton.setMaxWidth(Double.MAX_VALUE);
		createButton.setMaxWidth(Double.MAX_VALUE);
		// HBox.setHgrow(createButton, Priority.ALWAYS);
		HBox.setHgrow(removeButton, Priority.ALWAYS);
		createButtonBox.getChildren().add(createButton);
		createButtonBox.setAlignment(Pos.BASELINE_CENTER);
		removeButtonBox.getChildren().add(removeButton);
		buttonBox.getChildren().addAll(airportView, removeButtonBox);
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(airportName, 0, 0);
		gridPane.add(airportNameField, 1, 0);
		gridPane.add(city, 0, 1);
		gridPane.add(airportProvinceBox, 1, 1);
		// gridPane.add(createButton, 0, 2);
		mainPane.getChildren().addAll(windowHeader, headerBox, gridPane, createButtonBox, buttonBox);
		mainPane.setAlignment(Pos.CENTER);
		setupTableContent();
	}

	public void setupTableContent() {
		airportView.setItems(FXCollections.observableArrayList(airportList));
		airportView.getColumns().addAll(nameColumn, provinceColumn);
		nameColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("name"));
		provinceColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));

		nameColumn.prefWidthProperty().bind(airportView.widthProperty().multiply(0.5));
		provinceColumn.prefWidthProperty().bind(airportView.widthProperty().multiply(0.5));
		nameColumn.setResizable(false);
		provinceColumn.setResizable(false);
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
				MessageBox.message(AlertType.ERROR, "No Data Entered", "You must enter an airport name");
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
				FlightWindow.populateComboBoxes(); // NEED TO CHANGE BUT THIS UPDATES COMBOBOX. NEED TO HAVE STATIC
													// BOXES WITH THESE DATA INSIDE?
				loadFile();
			} else {
				MessageBox.message(AlertType.ERROR, "ERROR: No Airport Selected",
						"You must select an airport to remove");
			}
		});

		airportView.getSelectionModel().selectedItemProperty().addListener(event -> {
			Airport airport = airportView.getSelectionModel().getSelectedItem();
			if (airport != null) {
				//createButton.setText("Change Airport");
				airportNameField.setText(airport.getName());
				airportProvinceBox.setValue(airport.getCity());
			} else
				createButton.setText("Create Airport");
		});

		airportView.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE)
				resetFields();
		});
	}

	public void properties() {
		airportView.setMinHeight(150);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 400, 500));
		stage.setTitle("Create Airport");
		stage.show();
		stage.setResizable(false);
	} 
	private void createAirport(Airport airport) {
		airportList.add(airport);
		airportView.getItems().add(airport);
		AdminState.saveAirports(airportList);
		FlightWindow.populateComboBoxes();
		resetFields();
		loadFile();
	}

	private void changeAirport() {
		// for (int i = 0; i < airportList.size(); i++) {
		// if (airportList.get(i) == findAirport()) {
		// System.out.println("Airport list size: " + airportList.size());
		// airportList.get(i).setName(airportNameField.getText());
		// airportList.get(i).setCity(airportProvinceBox.getValue());
		// System.out.println("Found and changed!");
		// AdminState.saveAirports(airportList);
		// FlightWindow.populateComboBoxes();
		// resetFields();
		// loadFile();
		// return;
		// }
		for (Airport airport : airportList) {
			if (airport.equals(findAirport())) {
				//System.out.println("FOUND");
				airport.setName(airportNameField.getText());
				airport.setCity(airportProvinceBox.getValue());
				break;
			}
		}
		AdminState.saveAirports(airportList);
		FlightWindow.populateComboBoxes();
		resetFields();
		loadFile();
	}

	private void resetFields() {
		airportNameField.setText("");
		airportProvinceBox.setValue(airportProvinceBox.getItems().get(0));
		airportView.getSelectionModel().clearSelection();
	}

	private Airport findAirport() {
		String sAirport = airportView.getSelectionModel().getSelectedItem().toString();
	//	System.out.println(sAirport);
		for (Airport airport : airportList) {
			if (airport.toString().equals(sAirport)) {
				System.out.println("find airport found it");
				return airport;
			}
		}
		//System.out.println("Null");
		return null;
	}

	public void loadFile() {
		airportList = AdminState.loadAirports();
		if (!airportView.getItems().isEmpty())
			airportView.getItems().clear();
		for (int i = 0; i < airportList.size(); i++)
			airportView.getItems().add(airportList.get(i));
	}
}
