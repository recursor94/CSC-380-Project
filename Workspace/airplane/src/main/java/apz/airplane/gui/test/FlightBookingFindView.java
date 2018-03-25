package apz.airplane.gui.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightBookingFindView extends Application{
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 350;
	public static final String FRAME_TITLE = "Find Flights";
	public static final String LABEL_PICK_DATE_NAME = "Choose A Date:";
	public static final String LABEL_CHOOSE_DESTINATION_NAME = "Choose Destination: ";
	public static final String BUTTON_CONFIRM_FLIGHT_NAME = "Confirm Flight";

	private Stage primaryStage;
	private VBox rootPane;
	private HBox datePane;
	private HBox destinationPane;
	
	private Label labelPickDate;
	private Label labelChooseDestination;
	
	private Button buttonConfirm;
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
		datePane = new HBox(10);
		destinationPane = new HBox(10);
		labelPickDate = new Label(LABEL_PICK_DATE_NAME);
		labelChooseDestination = new Label(LABEL_CHOOSE_DESTINATION_NAME);
		datePickerFlightDate = new DatePicker();
		comboBoxDestinationList = new ComboBox<>();
		buttonConfirm = new Button(BUTTON_CONFIRM_FLIGHT_NAME);
	}
	
	public void initContent() {
		datePane.getChildren().addAll(labelPickDate, datePickerFlightDate);
		destinationPane.getChildren().addAll(labelChooseDestination, comboBoxDestinationList);
		rootPane.getChildren().addAll(datePane, destinationPane, buttonConfirm);
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
