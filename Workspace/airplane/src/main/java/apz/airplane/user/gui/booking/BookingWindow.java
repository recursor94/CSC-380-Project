package apz.airplane.user.gui.booking;

import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookingWindow implements GuiApplication {

	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private GridPane gridPane;
	private DatePicker datePicker;
	private ComboBox<String> cityDepartBox, cityArrivalBox;
	private Button searchButton;

	public BookingWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.BOOK_TRIP_IMAGE));
		headerText = new Text("Book a trip");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		datePicker = new DatePicker();
		cityDepartBox = new ComboBox<>();
		cityArrivalBox = new ComboBox<>();
		searchButton = new Button("Search");
	}

	public void content() {
		populateComboBox();
		
		mainPane.getChildren().addAll(new Label(), headerText, img, gridPane);
		
		gridPane.add(new Label("Trip date: "), 0, 2);
		gridPane.add(datePicker, 1, 2);
		
		gridPane.add(new Label("Departing Airport: "), 0, 3);
		gridPane.add(cityDepartBox, 1, 3);
		
		gridPane.add(new Label("Destination Airport: "), 0, 4);
		gridPane.add(cityArrivalBox, 1, 4);
		
		gridPane.add(searchButton, 1, 6);
	}

	public void actionEvents() {
		searchButton.setOnAction(event -> {
			if (datePicker.getValue() == null)
				MessageBox.message(AlertType.ERROR, null, "Date field is blank!");
			else if (datePicker.getValue().isBefore(LocalDate.now()))
				MessageBox.message(AlertType.ERROR, null, "Enter a valid date (not one beyond today)");
			else
				findAirportWindow();
		});

		cityDepartBox.setOnAction(event -> {
			comboBoxEvents(cityArrivalBox);
		});

		cityArrivalBox.setOnAction(event -> {
			comboBoxEvents(cityDepartBox);
		});
	}

	public void properties() {
		img.setFitWidth(235);
		img.setFitHeight(150);
		headerText.setFont(new Font(32));
		datePicker.setMaxWidth(200);
		datePicker.setEditable(false);
		gridPane.setVgap(15);
		gridPane.setHgap(15);
		gridPane.setAlignment(Pos.TOP_CENTER);
		mainPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle(("APZ Application - Search for Flights"));
	}

	private void populateComboBox() {
		// check if > 2 else disable and make so can't select same airport
		ArrayList<Airport> aList = APZState.loadAirports();

		cityDepartBox.setValue(aList.get(0).toString());
		cityArrivalBox.setValue(aList.get(1).toString());

		for (int i = 0; i < aList.size(); i++) {
			cityDepartBox.getItems().add(aList.get(i).toString());
			cityArrivalBox.getItems().add(aList.get(i).toString());
		}
	}

	private void comboBoxEvents(ComboBox<String> box) {
		String depart = "1", arrive = "2";

		if (cityArrivalBox.getSelectionModel().getSelectedItem() != null
				&& !cityArrivalBox.getSelectionModel().getSelectedItem().isEmpty())
			arrive = cityArrivalBox.getSelectionModel().getSelectedItem();

		if (cityDepartBox.getSelectionModel().getSelectedItem() != null
				&& !cityDepartBox.getSelectionModel().getSelectedItem().isEmpty())
			depart = cityDepartBox.getSelectionModel().getSelectedItem();

		if (depart.equals(arrive))
			box.setValue(null);
	}

	private void findAirportWindow() {
		
		if(cityDepartBox.getSelectionModel().getSelectedItem() == null || cityArrivalBox.getSelectionModel().getSelectedItem() == null)
			MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter a location for your departure and arrival");
		else {
			ArrayList<Flight> bookList = APZState.loadFlights();
			ArrayList<Flight> bookFoundList = new ArrayList<>();
	
			for (int i = 0; i < bookList.size(); i++) {
				LocalDate date = bookList.get(i).getDepartureDate();
				String departAirport = bookList.get(i).getDepartureAirport().toString();
				String arriveAirport = bookList.get(i).getDestinationAirport().toString();
				
	
				if (departAirport.equals(cityDepartBox.getValue()) && arriveAirport.equals(cityArrivalBox.getValue())
						&& date.equals(datePicker.getValue()) && !bookList.get(i).getPlane().getSeats().isFull())
					bookFoundList.add(bookList.get(i));
			}
	
			if (bookFoundList.isEmpty())
				MessageBox.message(AlertType.INFORMATION, null, "No flights found for " + cityDepartBox.getValue() + " to "
						+ cityArrivalBox.getValue() + " on " + datePicker.getValue());
			else
				new BookingResultWindow(bookFoundList);
		}
	}
}
