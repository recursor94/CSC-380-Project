package apz.airplane.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightBookingBookedFlightView extends Application{
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 350;
	public static final String FRAME_TITLE = "Flight Information";
	private static final String LABEL_DATE_NAME = "Date: ";
	private static final String LABEL_FLIGHT_NUMBER = "Flight Number: ";
	private static final String BUTTON_CANCEL_BUTTON_NAME = "Cancel Flight";
	private Stage primaryStage;
	private VBox rootPane;
	private Label labelDate;
	private Label labelFlightNumber;
	private Button cancelButton;
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
		labelFlightNumber = new Label("Flight Number: 0000");
		labelDate = new Label("Date: 9/9/9");
		cancelButton = new Button(BUTTON_CANCEL_BUTTON_NAME);
	}
	
	public void initContent() {
		rootPane.getChildren().addAll(labelFlightNumber, labelDate, cancelButton);
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
