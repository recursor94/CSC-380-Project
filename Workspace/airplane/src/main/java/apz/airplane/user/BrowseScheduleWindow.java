package apz.airplane.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import apz.airplane.model.Flight;
import apz.airplane.util.APZState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jimmy.pack.WindowInterface;

public class BrowseScheduleWindow implements WindowInterface {
	private TableView<FlightInformation> flightTable;
	private TableColumn<FlightInformation, Integer> flightNumber;
	private TableColumn<FlightInformation, String> departingCity, destinationCity, departingTime;
	private ArrayList<FlightInformation> flightsOnDate;
	private DatePicker flightDatePicker;
	private Text windowHeader;
	private Text dateText;
	private VBox mainPane;
	private VBox windowHeaderBox;
	private HBox dateBox;
	private Separator headerSeparator;

	public BrowseScheduleWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		mainPane = new VBox(20);
		flightTable = new TableView<>();
		flightNumber = new TableColumn<>("Flight Number");
		departingCity = new TableColumn<>("Departing From");
		destinationCity = new TableColumn<>("Arriving At");
		departingTime = new TableColumn<>("Time");
		flightDatePicker = new DatePicker();
		windowHeader = new Text("Browse Flight Schedules");
		dateText = new Text("Select Date");
		windowHeaderBox = new VBox(10);
		dateBox = new HBox(10);
		headerSeparator = new Separator(Orientation.HORIZONTAL);
	}

	public void content() {
		flightDatePicker.setValue(LocalDate.now());
		refreshTable(LocalDate.now());
		setupTableContents();
		windowHeaderBox.getChildren().addAll(windowHeader, headerSeparator);
		windowHeaderBox.setAlignment(Pos.BASELINE_CENTER);
		dateBox.getChildren().addAll(dateText, flightDatePicker);
		dateBox.setAlignment(Pos.BASELINE_CENTER);
		mainPane.getChildren().addAll(windowHeaderBox, flightDatePicker, flightTable);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("Scheduled Flights");
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(120, 120);
		scrollPane.setContent(flightTable);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	}

	public void actionEvents() {
		flightDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			refreshTable(newValue);
		});
	}

	public void properties() {
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(725);
		flightTable.setMinHeight(APZLauncher.getStage().getHeight() - 10);
		flightNumber.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		departingCity.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		destinationCity.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		departingTime.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		windowHeader.setStyle("-fx-font: 32 arial;");
		dateText.setStyle("-fx-font: 18 arial;");
	}

	private void setupTableContents() {
		flightTable.getColumns().addAll(flightNumber, departingCity, destinationCity, departingTime);
		flightNumber.setCellValueFactory(new PropertyValueFactory<FlightInformation, Integer>("flightNumber"));
		departingCity.setCellValueFactory(new PropertyValueFactory<FlightInformation, String>("departureAirport"));
		destinationCity.setCellValueFactory(new PropertyValueFactory<FlightInformation, String>("destinationAirport"));
		departingTime.setCellValueFactory(new PropertyValueFactory<FlightInformation, String>("departureTimeString"));
	}

	private void refreshTable(LocalDate date) {
		flightsOnDate = getFlightsOnDate(date);
		flightTable.setItems(FXCollections.observableArrayList(flightsOnDate));
		orderFlightsByTime();
	}

	private ArrayList<FlightInformation> getFlightsOnDate(LocalDate date) {
		ArrayList<Flight> allFlights = APZState.loadFlights();
		ArrayList<FlightInformation> flightsOnDate = new ArrayList<>(); // has to be new arraylist
		for (Flight flight : allFlights) {
			if (flight.getArriveDate().isEqual(date)) {
				flightsOnDate.add(new FlightInformation(flight.getFlightNum(), flight.getDepartureAirport().toString(),
						flight.getDestinationAirport().toString(), flight.getDepartureTime().getTimeDouble(),
						flight.getDepartureTime().getTimeString()));
			}
		}
		System.out.println(flightsOnDate.size());
		return flightsOnDate;
	}

	private void orderFlightsByTime() {
		FlightInformation temp, previous;

		for (int i = 0; i < flightsOnDate.size(); i++) {
			previous = flightsOnDate.get(i);

			for (int j = 0; j < flightsOnDate.size(); j++) {
				double previousTime = previous.getTime();
				double comparisonTime = flightsOnDate.get(j).getTime();
				if (comparisonTime < previousTime) {
					temp = flightsOnDate.get(i);
					flightsOnDate.set(i, flightsOnDate.get(j));
					flightsOnDate.set(j, temp);
				}
			}
		}
	}
}
