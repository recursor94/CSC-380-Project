package apz.airplane.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.User;
import apz.airplane.util.APZState;
import apz.airplane.util.MessageBox;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CancelFlightWindow {

	private Text windowHeader;
	private TableView<BookingTableData> tripTable;
	private TableColumn<BookingTableData, Integer> flightNumberColumn;
	private TableColumn<BookingTableData, String> arriveTimeColumn, departureTimeColumn, airlineColumn;
	private TableColumn<BookingTableData, Airport> departureAirportColumn, destinationAirportColumn;
	private TableColumn<BookingTableData, LocalDate> departureDateColumn, bookDateColumn;
	private TableColumn<BookingTableData, String> tripCostColumn;
	private Separator headerHorizontalSeparator;
	private VBox headerBox;
	private VBox bottomContentBox;
	private static VBox mainPane;
	private Button confirmButton;
	private User user;

	public CancelFlightWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	private void initialize() {

		windowHeader = new Text("Manage Trips");
		tripTable = new TableView<>();
		confirmButton = new Button("Cancel Flight");
		windowHeader = new Text("Manage Trips");
		flightNumberColumn = new TableColumn<>("Flight");
		arriveTimeColumn = new TableColumn<>("Boarding");
		departureTimeColumn = new TableColumn<>("Departing");
		airlineColumn = new TableColumn<>("Airline");
		departureAirportColumn = new TableColumn<>("From");
		destinationAirportColumn = new TableColumn<>("Arriving");
		departureDateColumn = new TableColumn<>("Date");
		bookDateColumn = new TableColumn<>("Booked On");
		tripCostColumn = new TableColumn<>("Subtotal");
		headerHorizontalSeparator = new Separator(Orientation.HORIZONTAL);
		headerBox = new VBox(5);
		bottomContentBox = new VBox(5);
		mainPane = new VBox(20);
	}

	private void content() {
		user = APZLauncher.getCurrentUser();
		resetTableData();
		setupTableContents();
		confirmButton.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(confirmButton, Priority.ALWAYS);
		headerBox.getChildren().addAll(windowHeader, headerHorizontalSeparator);
		bottomContentBox.getChildren().addAll(tripTable, new HBox(confirmButton));
		//mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(headerBox, bottomContentBox);

	}

	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		formatHeader();
	}

	private void formatHeader() {
		windowHeader.setStyle("-fx-font: 24 arial;");
		headerBox.setAlignment(Pos.CENTER);
	}

	private void actionEvents() {
		confirmButton.setOnAction(event -> {
			//System.out.println(tripTable.getSelectionModel().getSelectedItem());

			if (tripTable.getSelectionModel().getSelectedItem() != null) {
				Booking foundBooking = user.findTrip(tripTable.getSelectionModel().getSelectedItem().getFlight());
				
				if(!foundBooking.isCancellable()) {
					CancelDenialAlert.cancelFlightError();
					return;
				}

				Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "APZ Confirmation Dialog",
						"Are you okay with removing the selected flight?");
				if (result.get() == ButtonType.OK) {
					user.removeTrip(foundBooking.getFlight());
					resetTableData();
					APZState.saveInformation();

				} //else {
				//	System.out.println(":(");
				//}
			} else {
				MessageBox.message(AlertType.ERROR, "ERROR", "You must select a flight to cancel");
			}

		});
		tripTable.setRowFactory(tableView -> {
			TableRow<BookingTableData> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if(event.getClickCount() >= 2 &&!row.isEmpty()) {
					BookingTableData rowData = row.getItem();
					new TripResultWindow(rowData.getBookingRef());
				}
			});
			return row;
		});
	}

	private void setupTableContents() {
		//System.out.println(user.getTripList().size());
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
		airlineColumn.setCellValueFactory(new PropertyValueFactory<>("Airline"));
		arriveTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
		departureAirportColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
		destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
		bookDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		tripCostColumn.setCellValueFactory(new PropertyValueFactory<>("tripCost"));
		tripTable.getColumns().addAll(departureDateColumn, flightNumberColumn, airlineColumn, arriveTimeColumn,
				departureTimeColumn, departureAirportColumn, destinationAirportColumn);
		tripTable.setPrefHeight(APZLauncher.getBorderPane().getHeight() -10);
		
		tripTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void resetTableData() {
		ArrayList<Booking> tripList = user.getTripList();
		ArrayList<BookingTableData> informationList = new ArrayList<>();
		for (Booking trip : tripList) {
			informationList.add(new BookingTableData(trip));
		}
		tripTable.setItems(FXCollections.observableArrayList(informationList));

	}

	// public void createdStuff() {
	// Airplane airplane = new Airplane(1, "American Airline", 5);
	// LocalDate bookDate = LocalDate.now();
	// LocalDate departure = LocalDate.of(2018, 3, 12);
	// LocalDate arrival = LocalDate.of(2018, 3, 12);
	// Time departTime = new Time("12:00 PM");
	// Time arriveTime = new Time("7:00 PM");
	// Airport departureAirport = new Airport("JFK", "New York");
	// Airport destinationAirport = new Airport("Syracuse Hancock International",
	// "Syracuse");
	// Flight flight = new Flight(airplane, departureAirport, destinationAirport,
	// arrival, departure, departTime, arriveTime, 40908);
	// Booking trip = new Booking(flight, bookDate, user);
	// user.addTrip(trip);
	//
	// }

}
