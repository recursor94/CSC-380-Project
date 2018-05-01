package apz.airplane.user.gui.home;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import apz.airplane.model.Flight;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.booking.BookingResultWindow;
import apz.airplane.util.APZState;
import apz.airplane.util.GuiApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BrowseScheduleWindow implements GuiApplication {
	private TableView<HomeTableData> flightTable;
	private TableColumn<HomeTableData, Integer> flightNumber;
	private TableColumn<HomeTableData, String> departingCity, destinationCity, departingTime;
	private ArrayList<HomeTableData> flightsOnDate;
	private DatePicker flightDatePicker;
	private Text windowHeader;
	private Text dateText;
	private VBox mainPane;
	private VBox windowHeaderBox;
	private HBox dateBox;
	private Separator headerSeparator;
	private Timeline realTimeClock;
	private double timeHour;
	private double timeMinute;
	private Text dateTimeLabel;

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
		dateText = new Text("Select Date: ");
		windowHeaderBox = new VBox(20);
		dateBox = new HBox(10);
		headerSeparator = new Separator(Orientation.HORIZONTAL);
		dateTimeLabel = new Text("");
		setupClock();
	}

	public void content() {
		flightDatePicker.setValue(LocalDate.now());
		setTableData(LocalDate.now());
		setupTableContents();
		windowHeaderBox.getChildren().addAll(windowHeader, headerSeparator);
		windowHeaderBox.setAlignment(Pos.BASELINE_CENTER);
		dateBox.getChildren().addAll(dateText, flightDatePicker);
		dateBox.setAlignment(Pos.BASELINE_CENTER);
		HBox dateTimeHbox = new HBox();
		dateTimeHbox.setAlignment(Pos.BASELINE_CENTER);
		dateTimeHbox.getChildren().add(dateTimeLabel);
		mainPane.getChildren().addAll(windowHeaderBox, dateBox, dateTimeHbox, flightTable);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Scheduled Flights");
	}

	public void actionEvents() {
		flightDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			setTableData(newValue);
		});
		flightTable.setRowFactory(tableView -> {
			TableRow<HomeTableData> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() >= 2 && !row.isEmpty()) {
					HomeTableData rowData = row.getItem();
					
					ArrayList<Flight> flightList = new ArrayList<>();
					flightList.add(rowData.getFlightRef());
					new BookingResultWindow(flightList);
				}
			});
			return row;
		});
	}

	public void properties() {
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(725);
		APZLauncher.getStage().setTitle("APZ Application - Browse Schedules");
		flightTable.setMinHeight(APZLauncher.getStage().getHeight() - 10);
		flightNumber.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		departingCity.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		destinationCity.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		departingTime.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		windowHeader.setStyle("-fx-font: 32 arial;");
		dateText.setStyle("-fx-font: 14 arial;");
		dateTimeLabel.setStyle("-fx-font: 12 arial;");
	}

	private void setupTableContents() {
		flightTable.getColumns().addAll(flightNumber, departingCity, destinationCity, departingTime);
		flightNumber.setCellValueFactory(new PropertyValueFactory<HomeTableData, Integer>("flightNumber"));
		departingCity.setCellValueFactory(new PropertyValueFactory<HomeTableData, String>("departureAirport"));
		destinationCity.setCellValueFactory(new PropertyValueFactory<HomeTableData, String>("destinationAirport"));
		departingTime.setCellValueFactory(new PropertyValueFactory<HomeTableData, String>("departureTimeString"));
	}

	private void setTableData(LocalDate date) {
		flightsOnDate = getFlightsOnDate(date);
		flightTable.setItems(FXCollections.observableArrayList(flightsOnDate));
		orderFlightsByTime();
	}

	private ArrayList<HomeTableData> getFlightsOnDate(LocalDate date) {
		ArrayList<Flight> allFlights = APZState.loadFlights();
		
		ArrayList<Flight> freeFlightList = new ArrayList<>();
		for (int i = 0; i < allFlights.size(); i++) {
			if (!allFlights.get(i).getPlane().getSeats().isFull()) {
				freeFlightList.add(allFlights.get(i));
			}
		}
		
		ArrayList<HomeTableData> flightsOnDate = new ArrayList<>(); // has to be new arraylist
		for (Flight flight : freeFlightList) {
			if (flight.getArriveDate().isEqual(date)) {
				flightsOnDate.add(new HomeTableData(flight.getFlightNum(), flight.getDepartureAirport().toString(),
						flight.getDestinationAirport().toString(), flight.getDepartureTime().getTimeDouble(),
						flight.getDepartureTime().getTimeString(), flight));
			}
		}
		return flightsOnDate;
	}

	private void orderFlightsByTime() {
		HomeTableData temp, previous;

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

	private void setupClock() {
		refreshClockContent();
		realTimeClock = new Timeline(new KeyFrame(Duration.minutes(1), e -> {
			refreshClockContent();
			if (flightDatePicker.getValue().equals(LocalDate.now()))
				updateTable();

		}));
		realTimeClock.setCycleCount(Animation.INDEFINITE);
		realTimeClock.play();

	}

	private void refreshClockContent() {
		Calendar cal = Calendar.getInstance();
		timeHour = cal.get(Calendar.HOUR);
		timeMinute = cal.get(Calendar.MINUTE);
		String timeOfDay = "AM";

		if (cal.get(Calendar.AM_PM) == Calendar.PM) {
			timeOfDay = "PM";
		}
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM dd hh:mm");
		String sDate = dateFormat.format(cal.getTime());
		dateTimeLabel.setText("Current Time: " + sDate + " " + timeOfDay);

	}

	private void updateTable() {
		for (HomeTableData flight : flightsOnDate) {
			if (flight != null) {
				double departureTime = flight.getTime();
				int departureMinute = 0;
				int departureHour = (int) departureTime;

				if (departureTime % 1 == 0) {
					departureMinute = 30;
				}

				if (departureHour == timeHour && departureMinute == timeMinute) {
					flightsOnDate.remove(flight);
					flightTable.getItems().remove(flightTable.getSelectionModel().getSelectedItem());
				}

			}

		}
	}
}
