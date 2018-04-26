package apz.airplane.user;

import java.time.LocalDate;

import apz.airplane.model.Booking;
import apz.airplane.util.FilePath;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jimmy.pack.WindowInterface;

public class TripResultWindow implements WindowInterface {
	
	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private GridPane gridPane;
	private Button backButton;
	
	private Booking booking;

	public TripResultWindow(Booking booking) {
		this.booking = booking;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		headerText = new Text("Trip Query");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		backButton = new Button("Go back");
	}

	public void content() {
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 0);
		gridPane.add(new Label("Date Booked: " + booking.getBookDate()), 1, 0);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 1);
		gridPane.add(new Label("Trip Cost: $" + booking.getTripCost()), 1, 1);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 2);
		gridPane.add(new Label("Airline: " + booking.getFlight().getPlane().getAirline()), 1, 2);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 3);
		gridPane.add(new Label("Plane Number: " + booking.getFlight().getPlane().getPlaneNum()), 1, 3);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 4);
		gridPane.add(new Label("Departing Airport: " + booking.getFlight().getDepartureAirport()), 1, 4);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 5);
		gridPane.add(new Label("Departing Time: " + booking.getFlight().getDepartureTime()), 1, 5);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 6);
		gridPane.add(new Label("Destination Airport: " + booking.getFlight().getDestinationAirport()), 1, 6);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 7);
		gridPane.add(new Label("Destination Time: " + booking.getFlight().getArrivalTime()), 1, 7);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 8);
		gridPane.add(new Label("Flight Number: " + booking.getFlight().getFlightNum()), 1, 8);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 0, 9);
		gridPane.add(new Label("Account Holder: " + APZLauncher.getCurrentUser().getEmail()), 1, 9);
		
		mainPane.getChildren().addAll(new Label(), headerText, img, new Separator(), gridPane, new Separator(), backButton);
		
	}

	public void actionEvents() {
		for (int i = 0; i < gridPane.getChildren().size(); i++) 
			if (gridPane.getChildren().get(i) instanceof ImageView) {
				((ImageView) gridPane.getChildren().get(i)).setFitWidth(25);
				((ImageView) gridPane.getChildren().get(i)).setFitHeight(25);
		}
		
		backButton.setOnAction(event -> {
			new TripViewWindow();
		});
	}

	public void properties() {
		img.setFitWidth(150);
		img.setFitHeight(150);
		headerText.setFont(new Font(32));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
	}

}
