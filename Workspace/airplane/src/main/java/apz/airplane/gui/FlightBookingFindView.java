package apz.airplane.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightBookingFindView extends Application{
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 350;
	public static final String FRAME_TITLE = "Find Flights";
	private Stage primaryStage;
	private VBox rootPane;
	
	private Label labelPickDate;
	private Label labelChooseDestination;
	
	private DatePicker datePickerFlightDate;
	private ComboBox comboBoxDestinationList;
	

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
