package apz.airplane.user;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.model.Flight;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jimmy.pack.BookingPaymentWindow;
import jimmy.pack.WindowInterface;

public class BookFlightByDateWindow implements WindowInterface {
	private ImageView img;
	private GridPane gridPane;
	private Text header, listText;
	private VBox mainPane;
	private ListView<Flight> flightView;
	private ArrayList<Flight> flightList;
	private DatePicker calendar;
	private Button findFlightButton, bookFlightButton;

	public BookFlightByDateWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		mainPane = new VBox(10);
		gridPane = new GridPane();
		header = new Text("Find and Book Flights by Date");
		listText = new Text("List of flights on selected date");
		flightView = new ListView<>();
		flightList = new ArrayList<>();
		findFlightButton = new Button("Find Flights");
		bookFlightButton = new Button("Book Flight");
		calendar = new DatePicker();
	}

	public void content() {
		img.setFitWidth(150);
		img.setFitHeight(150);
		
		header.setFont(new Font(28));
		listText.setFont(new Font(20));
		
		bookFlightButton.setDisable(true);
		
		calendar.setEditable(false);
		calendar.setMaxWidth(200);
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		gridPane.add(new Label("Select a flight date: "), 0, 0);
		gridPane.add(calendar, 1, 0);
		
		gridPane.setAlignment(Pos.CENTER);
		
		mainPane.getChildren().addAll(img, header, gridPane, findFlightButton, listText, flightView, bookFlightButton);

		mainPane.setAlignment(Pos.CENTER);
	}
	
	public void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Book Flight by Date");
	}
	public void actionEvents() {
		findFlightButton.setOnAction(event -> {
			if (calendar.getValue() != null) {
				flightList = findFlights(calendar.getValue());
				
				if (!flightView.getItems().isEmpty())
					flightView.getItems().clear();
				for (int i = 0; i < flightList.size(); i++)
					flightView.getItems().add(flightList.get(i));

				if (flightView.getItems().isEmpty())
					MessageBox.message(AlertType.INFORMATION, "No Flights Found", "There are no flights scheduled for " + calendar.getValue());
		   
			}
			else 
				MessageBox.message(AlertType.ERROR, "ERROR", "You must select a date");
			
			bookFlightButton.setDisable(true);
		});

		flightView.getSelectionModel().selectedItemProperty().addListener(event -> {
			bookFlightButton.setDisable(false);
		});
		
		bookFlightButton.setOnAction(event -> {
				//new CheckBaggageWindow(flightView.getSelectionModel().getSelectedItem());
				new BookingPaymentWindow(flightView.getSelectionModel().getSelectedItem());
		});
	}

	private ArrayList<Flight> findFlights(LocalDate departure) {

		ArrayList<Flight> searchFlights = APZState.loadFlights();
		ArrayList<Flight> flightsFound = new ArrayList<>();

		for (int i = 0; i < searchFlights.size(); i++) {
			if (departure.equals(searchFlights.get(i).getDepartureDate()) && !searchFlights.get(i).getPlane().getSeats().isFull()) 
				flightsFound.add(searchFlights.get(i));
		}
		
		return flightsFound;
	}
}
