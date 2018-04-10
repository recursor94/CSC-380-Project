package apz.airplane.gui;

import apz.airplane.Flight;
import apz.airplane.admin.AdminState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreenWindow {
	private Button viewFlightButton;
	private Button logoutButton;
	
	private VBox rootPane;
	private Stage primaryStage;
	private ListView activeFlightView;

	public HomeScreenWindow() {
		initialize();
		content();
		actionEvents();
		System.out.println("Created");
	}

	private void actionEvents() {
		
	}

	private void content() {
		
		ObservableList<Flight> flights = FXCollections.observableArrayList(AdminState.loadFlights());
		activeFlightView.setItems(flights);
		rootPane.getChildren().addAll(viewFlightButton, activeFlightView);
		APZLauncher.getBorderPane().setCenter(rootPane);
	}

	private void initialize() {
		rootPane = new VBox(10);
		viewFlightButton = new Button("View Booked Flights");
		activeFlightView = new ListView<String>();
	}
	


}
