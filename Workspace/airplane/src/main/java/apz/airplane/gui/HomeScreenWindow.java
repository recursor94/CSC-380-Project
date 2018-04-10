package apz.airplane.gui;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.Flight;
import apz.airplane.admin.AdminState;
import apz.airplane.util.State;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreenWindow {
	private Button logoutButton;
	
	private VBox rootPane;
	private Stage primaryStage;
	private ListView activeFlightView;
	private ComboBox cityComboBox;

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
		rootPane.getChildren().addAll(activeFlightView);
		APZLauncher.getBorderPane().setCenter(rootPane);
	
	}

	private void initialize() {
		rootPane = new VBox(10);
		activeFlightView = new ListView<String>();
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

}
