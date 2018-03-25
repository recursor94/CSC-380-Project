package apz.airplane.gui.test;

import java.time.LocalDate;
import apz.airplane.Airplane;
import apz.airplane.Booking;
import apz.airplane.Flight;
import apz.airplane.Time;
import apz.airplane.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewFlightWindow {

	static VBox mainPane = new VBox(10);
	private Button viewButton = new Button("View Selected Flight");
	User user = new User("Andrew", "pendrew");
	
	public ViewFlightWindow(Stage stage) {
		ListView<Booking> list = new ListView<>();
		createdStuff();
		ObservableList<Booking> displayList = FXCollections.observableArrayList(user.getTripList());
		list.setItems(displayList);
		
		viewButton.setOnAction(event -> {
			
			if (list.getSelectionModel().getSelectedItem() != null) {
				Booking foundBooking = user.findTrip(list.getSelectionModel().getSelectedItem().getFlight());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("View Selected Flight");
				alert.setHeaderText(null);
				String text = "Destination: " + foundBooking.getFlight().getDestinationName() + "\nFlight Number: "
						+ foundBooking.getFlight().getFlightNum();
				alert.setContentText(text);
				alert.showAndWait();
				
			}
		});
		mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(new Label("List of Flights you made"), list, viewButton);
		stage.setTitle("View Flight Screen");
		DisplayDriver.getBorderPane().setCenter(mainPane);
	}

	public void createdStuff() {
		Airplane airplane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Flight flight = new Flight(airplane,"New York", "Hawaii", arrival, departure, departTime, arriveTime, 40908 );
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		
	}
}
