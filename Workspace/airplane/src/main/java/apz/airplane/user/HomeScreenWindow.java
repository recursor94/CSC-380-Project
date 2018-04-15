package apz.airplane.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import apz.airplane.model.Airport;
import apz.airplane.model.Flight;
import apz.airplane.model.Time;
import apz.airplane.util.APZState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class HomeScreenWindow {
	private Button logoutButton;
	
	private VBox rootPane;
	private Stage primaryStage;
	private ListView activeFlightView;
	private ComboBox cityComboBox;
	private Text timeLabel;
	private Timeline realTimeClock;
	private TableView<Flight> flightTable;
	TableColumn flightNumber; 
	//TableColumn departingAirport = new TableColumn("Departing Airpot");
	TableColumn departingCity;
	//TableColumn destinationAirport = new TableColumn("Destination Airport");
	TableColumn destinationCity;
	TableColumn departingTime;
	ArrayList<Flight> flightsToday;
	
	private int timeHour;
	private int timeMinute;
	private int timeSecond;

	public HomeScreenWindow() {
		initialize();
		content();
		actionEvents();
		System.out.println("Created");
	}

	private void actionEvents() {
		
	}

	private void content() {
		
		orderFlightsByTime();
		//ObservableList<Flight> flights = FXCollections.observableArrayList(orderedFlights);
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
		ObservableList<Flight> flightData = FXCollections.observableArrayList(flights);
		flightTable.setItems(flightData);
		flightTable.getColumns().addAll(flightNumber, departingCity, destinationCity, departingTime);
		flightNumber.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flightNum"));
		departingCity.setCellValueFactory(new PropertyValueFactory<Flight, Airport>("departureAirport"));
		destinationCity.setCellValueFactory(new PropertyValueFactory<Flight, Airport>("destinationAirport"));
		//departingTime.setCellFactory(new PropertyValueFactory<Flight, Time>("departureTime"));
		
		
	}	
	private void setupClock() {
		 realTimeClock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
		        Calendar cal = Calendar.getInstance();
		        timeSecond = cal.get(Calendar.SECOND);
		        timeMinute = cal.get(Calendar.MINUTE);
		        timeHour = cal.get(Calendar.HOUR);
		        String minuteString = timeMinute + "";
		        
		        if(timeHour >= 12) {
		        	timeHour -=12;
		        }
		        else if(timeHour == 0) {
		        	timeHour = 12;
		        }
		        if(timeMinute < 10) {
		        	minuteString = "0" + timeMinute;
		        }
		        timeLabel.setText(timeHour + ":" + (minuteString) + ":" + timeSecond);
		    }),
		         new KeyFrame(Duration.seconds(1))
		    );
		    realTimeClock.setCycleCount(Animation.INDEFINITE);
		    realTimeClock.play();
		    }
	}
	private ArrayList<Flight> getFlightsToday() {
		ArrayList<Flight> allFlights = APZState.loadFlights();
		ArrayList<Flight> flightsToday  = new ArrayList<Flight>(); //has to be new arraylist
		for(Flight flight : allFlights) {
			if(flight.getArriveDate().isEqual(LocalDate.now())) {
				flightsToday.add(flight);
			}
		}
		System.out.println(flightsToday.size());
		return flightsToday;
	}
	private void orderFlightsByTime() {
		Flight temp;
		Flight previous;

		for(int i = 0; i < flightsToday.size(); i++) {
			 previous = flightsToday.get(i);
			
			for(int j = 0; j < flightsToday.size(); j++) {
				double previousTime = previous.getDepartureTime().getTimeDouble();
				double comparisonTime = flightsToday.get(j).getArrivalTime().getTimeDouble();
				if(comparisonTime < previousTime) {
					temp = flightsToday.get(i);
					flightsToday.set(i, flightsToday.get(j));
					flightsToday.set(j, temp);
				}
			}
			
		} 
			
		} 
		
	}


