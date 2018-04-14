package apz.airplane.user;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.util.APZState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class JBookingWindow {

	private Text headerText;
	private GridPane mainPane;
	private DatePicker datePicker;
	private ComboBox<String> cityDepartBox, cityArrivalBox;
	private Button searchButton;
	
	public JBookingWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	private void initialize() {
		headerText = new Text("Book a trip");
		mainPane = new GridPane();
		datePicker = new DatePicker();
		cityDepartBox = new ComboBox<>();
		cityArrivalBox = new ComboBox<>();
		
		searchButton = new Button("Search");
	}
	
	private void content() {
		headerText.setFont(new Font(32));
		
		populateComboBox();

		// X		-	Y
		mainPane.add(headerText, 0, 0);
		
		mainPane.add(new Label("Trip date: "), 0, 2);
		mainPane.add(datePicker, 1, 2);
		
		mainPane.add(new Label("Departing Airport: "), 0, 3);
		mainPane.add(cityDepartBox, 1, 3);
		
		mainPane.add(new Label("Destination Airport: "), 0, 4);
		mainPane.add(cityArrivalBox, 1, 4);
		
		mainPane.add(searchButton, 1, 6);
		
		mainPane.setVgap(15);
		mainPane.setHgap(15);
		
		mainPane.setAlignment(Pos.CENTER);
		
		datePicker.setMaxWidth(200);
	}
	
	private void actionEvents() {
		searchButton.setOnAction(event -> {
			ArrayList<Booking> bookList = APZLauncher.getCurrentUser().getTripList();
			
			
			for (int i = 0; i < bookList.size(); i++) {
				LocalDate date = bookList.get(i).getFlight().getArriveDate();
				String departAirport = bookList.get(i).getFlight().getDepartureAirport().toString();
				String arriveAirport = bookList.get(i).getFlight().getDestinationAirport().toString();
				
				
				if (departAirport == cityDepartBox.getValue() && arriveAirport == cityArrivalBox.getValue() && date == datePicker.getValue()) {
					
				}
			}
			
			
//			new JBookingResultWindow();
		});
	}
	
	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setWidth(400);
		APZLauncher.getStage().setHeight(300);
	}
	
	private void populateComboBox() {
		
		// check if > 2 else disable and make so can't select same airport
		ArrayList<Airport> aList = APZState.loadAirports();

		cityDepartBox.setValue(aList.get(0).toString());
		cityArrivalBox.setValue(aList.get(1).toString());

		for (int i = 0; i < aList.size(); i++) {
			cityDepartBox.getItems().add(aList.get(i).toString());
			cityArrivalBox.getItems().add(aList.get(i).toString());
		}
		
	}

}
