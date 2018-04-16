package apz.airplane.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import apz.airplane.model.Flight;
import apz.airplane.util.APZState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeScreenWindow {
	private Button logoutButton;

	private VBox rootPane;
	private Stage primaryStage;
	private ListView activeFlightView;
	private ComboBox cityComboBox;
	private Text timeLabel;
	private Timeline realTimeClock;
	private TableView<FlightInformation> flightTable;
	TableColumn flightNumber;
	// TableColumn departingAirport = new TableColumn("Departing Airpot");
	TableColumn departingCity;
	// TableColumn destinationAirport = new TableColumn("Destination Airport");
	TableColumn destinationCity;
	TableColumn departingTime;
	ArrayList<FlightInformation> flightsToday;

	private int timeHour;
	private int timeMinute;

	public HomeScreenWindow() {
		initialize();
		content();
		actionEvents();
		System.out.println("Created");
	}

	private void actionEvents() {

	}

	private void content() {

		flightsToday = getFlightsToday();
		orderFlightsByTime();
		// ObservableList<Flight> flights =
		// FXCollections.observableArrayList(orderedFlights);
		timeLabel.setText(LocalDateTime.now().toString());
		setupTableContents();
		rootPane.getChildren().addAll(timeLabel, flightTable);
		APZLauncher.getBorderPane().setCenter(rootPane);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(120, 120);
		scrollPane.setContent(flightTable);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		realTimeClock.stop();
		realTimeClock.playFromStart();

	}

	private void initialize() {
		flightsToday = new ArrayList<>();
		rootPane = new VBox(10);
		activeFlightView = new ListView<String>();
		flightTable = new TableView<>();
		flightNumber = new TableColumn("Flight Number");
		departingCity = new TableColumn("Arriving From");
		destinationCity = new TableColumn("Departing To");
		departingTime = new TableColumn("Scheduled");
		timeLabel = new Text("00:00");
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
			String minuteString = timeMinute + "";

			if (timeHour >= 12) {
				timeHour -= 12;
			} else if (timeHour == 0) {
				timeHour = 12;
			}
			if (timeMinute < 10) {
				minuteString = "0" + timeMinute;
			}
			timeLabel.setText(timeHour + ":" + (minuteString));
		}), new KeyFrame(Duration.minutes(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("handling");
				for (FlightInformation flight : flightsToday) {
					if (flight != null) {
						double departureTime = flight.getTime();
						int departureHour = (int) departureTime;
						int departureMinute = 0;

						if (departureTime % 1 == 0) {
							departureMinute = 30;
						}

						if (departureHour == timeHour && departureMinute == timeMinute) {
							System.out.println("Time To Run");
							flightTable.getItems().remove(flight);
						}

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
<<<<<<< HEAD
			
		} 
		} 
		
	}
=======
>>>>>>> 739e661cb74d0e44d016f12950d3d586edfdb5df

		}
	}

}
