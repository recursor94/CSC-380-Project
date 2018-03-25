package apz.airplane.gui.test;

import java.time.LocalDate;
import java.util.Optional;

import apz.airplane.Airplane;
import apz.airplane.Booking;
import apz.airplane.Flight;
import apz.airplane.Time;
import apz.airplane.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CancelFlightView {

	static VBox mainPane = new VBox(10);
	private Button confirmButton = new Button("Confirm Selection");
	User user = new User("Andrew", "pendrew");
	
	public CancelFlightView(Stage stage) {
		ListView<Booking> list = new ListView<>();
		createdStuff();

		ObservableList<String> items =FXCollections.observableArrayList (
		    "Delta 03/12/2018", "Jet Blue 04/06/2017");
		
		ObservableList<Booking> displayList = FXCollections.observableArrayList(user.getTripList());
		list.setItems(displayList);
		
		
		
		confirmButton.setOnAction(event -> {
			System.out.println(list.getSelectionModel().getSelectedItem());
			
			if (list.getSelectionModel().getSelectedItem() != null) {
				Booking foundBooking = user.findTrip(list.getSelectionModel().getSelectedItem().getFlight());
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText(null);
				alert.setContentText("REMOVE IT OR WAT?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				    user.removeTrip(foundBooking.getFlight());
				    ObservableList<Booking> asd = FXCollections.observableArrayList(user.getTripList());
					list.setItems(asd);
						
				} else {
				    System.out.println(":(");
				}
				
			}
			
		});
		
		
		
		mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(new Label("List of Flights you made"), list, confirmButton);
		stage.setTitle("Cancel Flight Screen");
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
