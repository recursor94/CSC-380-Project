package apz.airplane.user.gui.booking;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.User;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.viewtrips.TripResultWindow;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewUpcomingTripWindow implements GuiApplication {

	private ImageView img;
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

	public ViewUpcomingTripWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.HOME_PLANE));
		windowHeader = new Text("View Upcoming Trips");
		tripTable = new TableView<>();
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

		headerBox.getChildren().addAll(new Label(), windowHeader, img, headerHorizontalSeparator);
		bottomContentBox.getChildren().add(tripTable);
		// mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(headerBox, bottomContentBox);

	}

	public void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		formatHeader();
	}

	public void formatHeader() {
		img.setFitWidth(150);
		img.setFitHeight(150);
		windowHeader.setStyle("-fx-font: 24 arial;");
		headerBox.setAlignment(Pos.CENTER);
	}

	public void actionEvents() {
		tripTable.setRowFactory(tableView -> {
			TableRow<BookingTableData> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() >= 2 && !row.isEmpty()) {
					//System.out.println("Clicked twice");
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
}
