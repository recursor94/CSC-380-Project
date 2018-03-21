package apz.airplane.gui;

import java.time.LocalDateTime;
import java.util.Optional;

import apz.airplane.Airplane;
import apz.airplane.Booking;
import apz.airplane.Flight;
import apz.airplane.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
//			System.out.println(list.getSelectionModel().getSelectedItem());
			
//			if (list.getSelectionModel().getSelectedItem() != null) {
//				Booking foundBooking = user.findTrip(list.getSelectionModel().getSelectedItem().getFlight());
//				
//				Alert alert = new Alert(AlertType.CONFIRMATION);
//				alert.setTitle("Confirmation Dialog");
//				alert.setHeaderText(null);
//				alert.setContentText("REMOVE IT OR WAT?");
//
//				Optional<ButtonType> result = alert.showAndWait();
//				if (result.get() == ButtonType.OK){
//				    user.removeTrip(foundBooking.getFlight());
//				    ObservableList<Booking> asd = FXCollections.observableArrayList(user.getTripList());
//					list.setItems(asd);
//					
//					
//					
//				} else {
//				    System.out.println(":(");
//				}
//				
//			}
			
			new LoginView(stage);
		});
		
		
		
		mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(new Label("List of Flights you made"), list, confirmButton);
		stage.setTitle("Cancel Flight Screen");
		DisplayDriver.getMainPane().setCenter(mainPane);
	}
	
	public void createdStuff() {
		Airplane airplane = new Airplane(1, "American Airline", 5);
		LocalDateTime bookDate = LocalDateTime.now();
		LocalDateTime departure =  LocalDateTime.of(2018, 3, 12, 7, 45);
		LocalDateTime arrival = LocalDateTime.of(2018, 3, 12, 12, 55);
		Flight flight = new Flight(airplane, "Hawaii", arrival, departure, 40908 );
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		
		Airplane airplane2 = new Airplane(1, "Delta Airline", 5);
		LocalDateTime bookDate2 = LocalDateTime.now();
		LocalDateTime departure2 =  LocalDateTime.of(2018, 3, 12, 7, 45);
		LocalDateTime arrival2 = LocalDateTime.of(2018, 3, 12, 12, 55);
		Flight flight2 = new Flight(airplane2, "YOU ES OF AYE", arrival2, departure2, 40908 );
		Booking trip2 = new Booking(flight2, bookDate2, user);
		user.addTrip(trip2);
	}

}
