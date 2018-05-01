package apz.airplane.user;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.User;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jimmy.pack.GuiApplication;

public class CancelFlightWindow implements GuiApplication {

	private Text windowHeader;
	private TableView<BookingTableData> tripTable;
	private TableColumn<BookingTableData, Integer> flightNumberColumn;
	private TableColumn<BookingTableData, String> departureTimeColumn, airlineColumn;
	private TableColumn<BookingTableData, Airport> departureAirportColumn, destinationAirportColumn;
	private TableColumn<BookingTableData, LocalDate> departureDateColumn, bookDateColumn;
	private TableColumn<BookingTableData, String> tripCostColumn;
	private Separator headerHorizontalSeparator;
	private VBox headerBox;
	private VBox bottomContentBox;
	private static VBox mainPane;
	private User user;

	public CancelFlightWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {

		windowHeader = new Text("Upcoming Trips");
		tripTable = new TableView<>();
		windowHeader = new Text("Manage Trips");
		flightNumberColumn = new TableColumn<>("Flight");
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

	public void content() {
		user = APZLauncher.getCurrentUser();
		resetTableData();
		setupTableContents();

		headerBox.getChildren().addAll(windowHeader, headerHorizontalSeparator);
		bottomContentBox.getChildren().add(tripTable);
		// mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(headerBox, bottomContentBox);

	}

	public void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		formatHeader();
	}

	public void formatHeader() {
		windowHeader.setStyle("-fx-font: 24 arial;");
		headerBox.setAlignment(Pos.CENTER);
	}

	public void actionEvents() {
		tripTable.setRowFactory(tableView -> {
			TableRow<BookingTableData> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() >= 2 && !row.isEmpty()) {
					System.out.println("Clicked twice");
					BookingTableData rowData = row.getItem();
					new TripResultWindow(rowData.getBookingRef(), this);
				}
			});
			return row;
		});
	}

	private void setupTableContents() {
		// System.out.println(user.getTripList().size());
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
		airlineColumn.setCellValueFactory(new PropertyValueFactory<>("Airline"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
		departureAirportColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
		destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
		bookDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		tripCostColumn.setCellValueFactory(new PropertyValueFactory<>("tripCost"));
		tripTable.getColumns().addAll(departureDateColumn, flightNumberColumn, airlineColumn, departureTimeColumn,
				departureAirportColumn, destinationAirportColumn);
		tripTable.setPrefHeight(APZLauncher.getBorderPane().getHeight() - 10);

		tripTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void resetTableData() {
		ArrayList<Booking> tripList = user.getTripList();
		ArrayList<BookingTableData> informationList = new ArrayList<>();
		for (Booking trip : tripList) {
			if (!trip.getBookDate().isBefore(LocalDate.now()))
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
