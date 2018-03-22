package apz.airplane.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightBookingParentView extends Application{
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 350;
	public static final String BUTTON_FIND_FLIGHT_NAME = "Find Flights";
	public static final String BUTTON_VIEW_FLIGHTS_NAME = "View Flights";
	public static final String FRAME_TITLE = "Booking";
	private Stage primaryStage;
	private VBox rootPane;
	private Button buttonFindFlight;
	private Button buttonViewFlights;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		init();
		initContent();
		initActionEvents();
		setProperties();
		
	}
	
	public void init() {
		rootPane = new VBox(10);
		buttonFindFlight = new Button(BUTTON_FIND_FLIGHT_NAME);
		buttonViewFlights = new Button(BUTTON_VIEW_FLIGHTS_NAME);
	}
	
	public void initContent() {
		rootPane.getChildren().addAll(buttonFindFlight, buttonViewFlights);
	}
	public void initActionEvents() {
		
	}
	public void setProperties() {
		primaryStage.setScene(new Scene(rootPane));
		primaryStage.setWidth(FRAME_WIDTH);
		primaryStage.setHeight(FRAME_HEIGHT);
		primaryStage.show();
	} 

	//for testing 
	public static void main(String[] args) {
		launch(args);
	}

	

}
