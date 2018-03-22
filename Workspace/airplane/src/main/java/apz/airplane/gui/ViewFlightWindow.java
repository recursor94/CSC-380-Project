package apz.airplane.gui;

import java.time.LocalDateTime;
import apz.airplane.Airplane;
import apz.airplane.Booking;
import apz.airplane.Flight;
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
		DisplayDriver.getMainPane().setCenter(mainPane);
	}

	
	public void createdStuff() {
		Airplane airplane = new Airplane(1, "American Airline", 5);
		LocalDateTime bookDate = LocalDateTime.now();
		LocalDateTime departure =  LocalDateTime.of(2018, 3, 29, 7, 45);
		LocalDateTime arrival = LocalDateTime.of(2018, 3, 29, 12, 55);
		Flight flight = new Flight(airplane, "Hawaii", arrival, departure, 40908 );
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		
		Airplane airplane2 = new Airplane(1, "Delta Airline", 5);
		LocalDateTime bookDate2 = LocalDateTime.now();
		LocalDateTime departure2 =  LocalDateTime.of(2018, 3, 25, 9, 55);
		LocalDateTime arrival2 = LocalDateTime.of(2018, 3, 25, 1, 55);
		Flight flight2 = new Flight(airplane2, "Chicago", arrival2, departure2, 12917 );
		Booking trip2 = new Booking(flight2, bookDate2, user);
		user.addTrip(trip2);
	}
}
