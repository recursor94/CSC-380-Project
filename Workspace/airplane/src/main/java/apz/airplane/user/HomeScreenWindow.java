package apz.airplane.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import apz.airplane.model.Flight;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import jimmy.pack.WindowInterface;

public class HomeScreenWindow implements WindowInterface {
	private ImageView img;
	private VBox rootPane;
	private Text timeLabel, dateLabel, header;
	private Timeline realTimeClock;
	private TableView<FlightInformation> flightTable;
	private TableColumn<FlightInformation, Integer> flightNumber;
	private TableColumn<FlightInformation, String> departingCity, destinationCity, departingTime;
	private ArrayList<FlightInformation> flightsToday;

	private int timeHour;
	private int timeMinute;

	public HomeScreenWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void actionEvents() {

	}

	public void properties() {
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(725);
		flightTable.setMinHeight(APZLauncher.getStage().getHeight() - 10);
		flightNumber.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		departingCity.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		destinationCity.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
		departingTime.prefWidthProperty().bind(flightTable.widthProperty().multiply(0.25));
	}

	public void content() {

		img.setFitWidth(150);
		img.setFitHeight(150);
		flightsToday = getFlightsToday();
		orderFlightsByTime();
		timeLabel.setText(LocalDateTime.now().toString());
		timeLabel.setStyle("-fx-font: 24 arial;");
		timeLabel.setFill(Color.BLACK);
		dateLabel.setText(LocalDateTime.now().toString());
		dateLabel.setStyle("-fx-font: 24 arial;");
		dateLabel.setFill(Color.BLACK);

		header.setFont(new Font(28));
		setupTableContents();
		rootPane.getChildren().addAll(header, img, dateLabel, timeLabel, flightTable);
		rootPane.setAlignment(Pos.CENTER);
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setTitle("APZ Application - Home Screen");
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(120, 120);
		scrollPane.setContent(flightTable);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		realTimeClock.stop();
		realTimeClock.playFromStart();

	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		flightsToday = new ArrayList<>();
		rootPane = new VBox(10);
		flightTable = new TableView<>();
		flightNumber = new TableColumn<>("Flight Number");
		departingCity = new TableColumn<>("Arriving From");
		destinationCity = new TableColumn<>("Departing To");
		departingTime = new TableColumn<>("Scheduled");
		dateLabel = new Text("");
		timeLabel = new Text("00:00");
		header = new Text("Welcome to APZ Booking!");
		setupClock();
	}

	private void setupTableContents() {
		ObservableList<FlightInformation> flightData = FXCollections.observableArrayList(flightsToday);
		flightTable.setItems(flightData);
		flightTable.getColumns().addAll(flightNumber, departingCity, destinationCity, departingTime);
		flightNumber.setCellValueFactory(new PropertyValueFactory<FlightInformation, Integer>("flightNumber"));
		departingCity.setCellValueFactory(new PropertyValueFactory<FlightInformation, String>("departureAirport"));
		destinationCity.setCellValueFactory(new PropertyValueFactory<FlightInformation, String>("destinationAirport"));
		departingTime.setCellValueFactory(new PropertyValueFactory<FlightInformation, String>("departureTimeString"));
	}

	private void setupClock() {
		realTimeClock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			Calendar cal = Calendar.getInstance();
			timeHour = cal.get(Calendar.HOUR);
			timeMinute = cal.get(Calendar.MINUTE);
			// timeHour++;
			String timeOfDay = "AM";

			if (cal.get(Calendar.AM_PM) == Calendar.PM) {
				timeOfDay = "PM";
			}
			DateFormat dateFormat = new SimpleDateFormat("MMMMM dd");
			DateFormat timeFormat = new SimpleDateFormat("hh:mm");
			String sDate = dateFormat.format(cal.getTime());
			String sTime = timeFormat.format(cal.getTime());
			//timeLabel.setText(timeHour + ":" + (minuteString) + " " + timeOfDay);
			timeLabel.setText(sTime + " " + timeOfDay);
			dateLabel.setText(sDate);
		}), new KeyFrame(Duration.minutes(1), e -> {
			for (FlightInformation flight : flightsToday) {
				if (flight != null) {
					double departureTime = flight.getTime();
					int departureMinute = 0;
					int departureHour = (int) departureTime;

					if (departureTime % 1 == 0) {
						departureMinute = 30;
					}

					if (departureHour == timeHour && departureMinute == timeMinute) {
						flightsToday.remove(flight);
						flightTable.getItems().remove(flightTable.getSelectionModel().getSelectedItem());
					}

				}

			}

		}));
		realTimeClock.setCycleCount(Animation.INDEFINITE);
		realTimeClock.play();

	}

	private ArrayList<FlightInformation> getFlightsToday() {
		ArrayList<Flight> allFlights = APZState.loadFlights();
		ArrayList<FlightInformation> flightsToday = new ArrayList<>(); // has to be new arraylist
		for (Flight flight : allFlights) {
			if (flight.getArriveDate().isEqual(LocalDate.now())) {
				flightsToday.add(new FlightInformation(flight.getFlightNum(), flight.getDepartureAirport().toString(),
						flight.getDestinationAirport().toString(), flight.getDepartureTime().getTimeDouble(),
						flight.getDepartureTime().getTimeString()));
			}
		}
		System.out.println(flightsToday.size());
		return flightsToday;
	}

	private void orderFlightsByTime() {
		FlightInformation temp, previous;

		for (int i = 0; i < flightsToday.size(); i++) {
			previous = flightsToday.get(i);

			for (int j = 0; j < flightsToday.size(); j++) {
				double previousTime = previous.getTime();
				double comparisonTime = flightsToday.get(j).getTime();
				if (comparisonTime < previousTime) {
					temp = flightsToday.get(i);
					flightsToday.set(i, flightsToday.get(j));
					flightsToday.set(j, temp);
				}
			}
		}
	}
}
