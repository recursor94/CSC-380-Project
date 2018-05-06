package apz.airplane.user.gui.viewtrips;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.booking.ViewUpcomingTripWindow;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TripResultWindow implements GuiApplication {

	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private HBox buttonBox;
	private GridPane gridPane;
	private Button backButton;
	private Button cancelButton;
	private DecimalFormat df;

	private Booking booking;
	private GuiApplication parentWindow;

	public TripResultWindow(Booking booking) {
		this.booking = booking;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public TripResultWindow(Booking booking, GuiApplication parentWindow) {
		this.booking = booking;
		this.parentWindow = parentWindow;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.QUERY_IMAGE));
		headerText = new Text("Trip Query");
		mainPane = new VBox(10);
		buttonBox = new HBox(8);
		gridPane = new GridPane();
		backButton = new Button("Go back");
		cancelButton = new Button("Cancel Flight");
		df = new DecimalFormat(".00");
	}

	public void content() {
		buttonBox.setAlignment(Pos.BASELINE_CENTER);
		buttonBox.getChildren().addAll(cancelButton, backButton);
		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 0);
		gridPane.add(new Label("Date Booked: " + booking.getBookDate()), 1, 0);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 1);
		gridPane.add(new Label("Trip Cost: $" + df.format(booking.getTripCost())), 1, 1);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 2);
		gridPane.add(new Label("Airline: " + booking.getFlight().getPlane().getAirline()), 1, 2);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 3);
		gridPane.add(new Label("Plane Number: " + booking.getFlight().getPlane().getPlaneNum()), 1, 3);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 4);
		gridPane.add(new Label("Departing Airport: " + booking.getFlight().getDepartureAirport()), 1, 4);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 5);
		gridPane.add(new Label("Departing Time: " + booking.getFlight().getDepartureTime()), 1, 5);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 6);
		gridPane.add(new Label("Destination Airport: " + booking.getFlight().getDestinationAirport()), 1, 6);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 7);
		gridPane.add(new Label("Destination Time: " + booking.getFlight().getArrivalTime()), 1, 7);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 8);
		gridPane.add(new Label("Flight Number: " + booking.getFlight().getFlightNum()), 1, 8);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 0, 9);
		gridPane.add(new Label("Account Holder: " + APZLauncher.getCurrentUser().getEmail()), 1, 9);

		mainPane.getChildren().addAll(new Label(), headerText, img, new Separator(), gridPane, new Separator(),
				buttonBox);

	}

	public void actionEvents() {
		for (int i = 0; i < gridPane.getChildren().size(); i++)
			if (gridPane.getChildren().get(i) instanceof ImageView) {
				((ImageView) gridPane.getChildren().get(i)).setFitWidth(20);
				((ImageView) gridPane.getChildren().get(i)).setFitHeight(20);
			}

		backButton.setOnAction(event -> {
			if (parentWindow instanceof ViewUpcomingTripWindow) {
				new ViewUpcomingTripWindow();
			} else {
				
				new TripViewWindow();
			}
		});

		cancelButton.setOnAction(event -> {

			Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "APZ Confirmation Dialog",
					"Are you okay with removing the selected flight?");
			if (result.get() == ButtonType.OK) {
				APZLauncher.getCurrentUser().removeTrip(booking.getFlight());
				
				ArrayList<Flight> flightList = APZState.loadFlights();
				for (int i = 0; i < flightList.size(); i++) {
					if (flightList.get(i).getFlightNum() == booking.getFlight().getFlightNum()) 
						flightList.set(i, booking.getFlight());
				}
				
				APZState.saveFlight(flightList);
				APZState.saveInformation();
				new TripViewWindow();
			} 
			else {
				MessageBox.message(AlertType.INFORMATION, "Cancellation Aborted", "Flight Cancellation Aborted");
			}
		});

	}

	public void properties() {
		img.setFitWidth(230);
		img.setFitHeight(150);
		headerText.setFont(new Font(32));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Trip Query");
	}
}
