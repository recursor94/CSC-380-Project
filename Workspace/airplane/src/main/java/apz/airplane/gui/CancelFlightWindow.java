package apz.airplane.gui;

import java.time.LocalDate;
import java.util.Optional;

import apz.airplane.Airplane;
import apz.airplane.Airport;
import apz.airplane.Booking;
import apz.airplane.Flight;
import apz.airplane.Time;
import apz.airplane.User;
import apz.airplane.gui.test.DisplayDriver;
import apz.airplane.util.MessageBox;
import apz.airplane.util.State;
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

public class CancelFlightWindow {

	private ListView<Booking> list;
	private static VBox mainPane;
	private Button confirmButton;
	private User user = APZLauncher.getCurrentUser();

	public CancelFlightWindow() {
		initialize();
		content();
		actionEvents();
	}

	private void initialize() {
		mainPane = new VBox(10);

		list = new ListView<>();
		confirmButton = new Button("Cancel Flight");
	}

	private void content() {
//		createdStuff();
//		ObservableList<String> items = FXCollections.observableArrayList("Delta 03/12/2018", "Jet Blue 04/06/2017");
//
		ObservableList<Booking> displayList = FXCollections.observableArrayList(user.getTripList());
		list.setItems(displayList);

		mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(new Label("List of Flights you made"), list, confirmButton);
		APZLauncher.getBorderPane().setCenter(mainPane);
	}

	private void actionEvents() {
		confirmButton.setOnAction(event -> {
			System.out.println(list.getSelectionModel().getSelectedItem());

			if (list.getSelectionModel().getSelectedItem() != null) {
				Booking foundBooking = user.findTrip(list.getSelectionModel().getSelectedItem().getFlight());

				Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "APZ Confirmation Dialog",
						"Are you okay with removing the selected flight?");
				if (result.get() == ButtonType.OK) {
					user.removeTrip(foundBooking.getFlight());
					ObservableList<Booking> asd = FXCollections.observableArrayList(user.getTripList());
					list.setItems(asd);
					State.saveInformation(APZLauncher.getUserController());

				} else {
					System.out.println(":(");
				}
			}
			else {
				MessageBox.message(AlertType.ERROR, "ERROR", "You must select a flight to cancel");
			}

		});
	}

	public void createdStuff() {
		Airplane airplane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure = LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time("12:00 PM");
		Time arriveTime = new Time("7:00 PM");
		Airport departureAirport = new Airport("JFK", "New York");
		Airport destinationAirport = new Airport("Syracuse Hancock International", "Syracuse");
		Flight flight = new Flight(airplane, departureAirport, destinationAirport, arrival, departure, departTime, arriveTime, 40908);
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);

	}

}
