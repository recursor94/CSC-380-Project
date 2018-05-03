package apz.airplane.user.gui.booking;

import java.util.ArrayList;

import apz.airplane.model.Airport;
import apz.airplane.model.Flight;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookByLocation implements GuiApplication {
	
	private ImageView img;
	private VBox mainPane;
	private GridPane gridPane;
	private Text header;
	private ListView<Flight> flightView;
	private Button findFlightButton, bookFlightButton;
	private ComboBox<String> departureBox, destinationBox;
	
	public BookByLocation() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	public void initialize() {
		img = new ImageView(new Image(FilePath.LOCATION_IMAGE));
		mainPane = new VBox(10);
		gridPane = new GridPane();
		header = new Text("Find and Book Flights by Location");
		flightView = new ListView<>();
		findFlightButton = new Button("Find Flights");
		bookFlightButton = new Button("Book Flight");
		departureBox = new ComboBox<>();
		destinationBox = new ComboBox<>();
	}
	
	public void content() {
		populateComboBox();
		
		gridPane.add(new Label("Select your current location"), 0, 0);
		gridPane.add(departureBox, 1, 0);
		
		gridPane.add(new Label("Select your desired destination"), 0, 1);
		gridPane.add(destinationBox, 1, 1);
		
		mainPane.getChildren().addAll(new Label(), header, img, new Separator(), gridPane,findFlightButton, flightView, bookFlightButton);
	}
	
	public void actionEvents() {
		findFlightButton.setOnAction(event -> {
			findFlights();
			bookFlightButton.setDisable(true);
		});
		
		flightView.getSelectionModel().selectedItemProperty().addListener(event -> {
			bookFlightButton.setDisable(false);
		});
		
		bookFlightButton.setOnAction(event -> {
				new BookingPaymentWindow(flightView.getSelectionModel().getSelectedItem());
		});
		
		destinationBox.setOnAction(event -> {
			comboBoxEvents(departureBox);
		});
		
		departureBox.setOnAction(event -> {
			comboBoxEvents(destinationBox);
		});
	}
	
	public void properties() {
		img.setFitWidth(250);
		img.setFitHeight(150);
		header.setFont(new Font(28));
		bookFlightButton.setDisable(true);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		mainPane.setAlignment(Pos.CENTER);
		gridPane.setAlignment(Pos.CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Book Flight By Location");
	}
	
	private void populateComboBox() {
		ArrayList<Airport> aList = APZState.loadAirports();
		
		if(!aList.isEmpty()) {
			if(aList.size() >= 2) {
				departureBox.setValue(aList.get(0).toString());
				destinationBox.setValue(aList.get(1).toString());
				for (int i = 0; i < aList.size(); i++) {
					destinationBox.getItems().add(aList.get(i).toString());	
					departureBox.getItems().add(aList.get(i).toString());	
				}
			}
			else {
				destinationBox.setDisable(true);
				departureBox.setDisable(true);
			}
		}
	}
	
	private void comboBoxEvents(ComboBox<String> box) {
		String depart = "1", arrive = "2";

		if (destinationBox.getSelectionModel().getSelectedItem() != null
				&& !destinationBox.getSelectionModel().getSelectedItem().isEmpty())
			arrive = destinationBox.getSelectionModel().getSelectedItem();

		if (departureBox.getSelectionModel().getSelectedItem() != null
				&& !departureBox.getSelectionModel().getSelectedItem().isEmpty())
			depart = departureBox.getSelectionModel().getSelectedItem();

		if (depart.equals(arrive))
			box.setValue(null);
	}

	private void findFlights() {
		ArrayList<Flight> searchFlights = APZState.loadFlights();
		ArrayList<Flight> flightsFound = new ArrayList<>();
		if(destinationBox.getSelectionModel().getSelectedItem() == null || departureBox.getSelectionModel().getSelectedItem() == null) {
			MessageBox.message(AlertType.ERROR, "ERROR: Invalid Data Entry", "You must enter a current location and desired destination");
		}
		else {	
			String departure = departureBox.getSelectionModel().getSelectedItem();
			String destination = destinationBox.getSelectionModel().getSelectedItem();
			for (int i = 0; i < searchFlights.size(); i ++) {
				if (destination.equals(searchFlights.get(i).getDestinationAirport().toString()) && 
						departure.equals(searchFlights.get(i).getDepartureAirport().toString()) && 
						!searchFlights.get(i).getPlane().getSeats().isFull()) {
					flightsFound.add(searchFlights.get(i));
				}
			}
			if (!flightView.getItems().isEmpty())
				flightView.getItems().clear();
			for (int i = 0; i < flightsFound.size(); i++)
				flightView.getItems().add(flightsFound.get(i));
			if (flightView.getItems().isEmpty()) {
				MessageBox.message(AlertType.INFORMATION, "No Flights Found", "There are no flights going from " + departure +
						" to " + destination);
			}
		}
	}
}
