package apz.airplane.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import apz.airplane.Flight;
import apz.airplane.admin.AdminState;
import apz.airplane.util.State;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeScreenWindow {
	private Button logoutButton;
	
	private VBox rootPane;
	private Stage primaryStage;
	private ListView activeFlightView;
	private ComboBox cityComboBox;
	private Label timeLabel;
	private Timeline realTimeClock;
	
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
		
		ObservableList<Flight> flights = FXCollections.observableArrayList(getFlightsToday());
		activeFlightView.setItems(flights);
		timeLabel.setText(LocalDateTime.now().toString());
		rootPane.getChildren().addAll(timeLabel, activeFlightView);
		APZLauncher.getBorderPane().setCenter(rootPane);
		
		realTimeClock.stop();
		realTimeClock.playFromStart();
	
	}

	private void initialize() {
		rootPane = new VBox(10);
		activeFlightView = new ListView<String>();
		timeLabel = new Label("00:00");
		
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
	
	private ArrayList<Flight> getFlightsToday() {
		ArrayList<Flight> allFlights = State.loadFlights();
		ArrayList<Flight> flightsToday  = new ArrayList<Flight>(); //has to be new arraylist
		for(Flight flight : allFlights) {
			if(flight.getArriveDate().isEqual(LocalDate.now())) {
				flightsToday.add(flight);
			}
		}
		return flightsToday;
	}
	/*private void orderFlightsByTime(ArrayList<Flight> flightsToday) {
		Flight lastAddedFlight = flightsToday.get(0);
		ArrayList<Flight> orderedFlights = new ArrayList<>();
		for(int i = 0; i < flightsToday.size(); i++) {
			for(int j = 0; j < flightsToday.size(); j++) {
				
			}
			
		} 
			
		}*/ 
		
	}


