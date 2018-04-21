package jimmy.pack;

import java.time.LocalDate;

import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.Payment;
import apz.airplane.model.User;
import apz.airplane.user.APZLauncher;
import apz.airplane.user.PaymentAddWindow;
import apz.airplane.util.APZMath;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookingPaymentWindow implements WindowInterface {
	
	private User user;
	private VBox mainPane;
	private static GridPane paymentPane; 
	private Text header;
	private static ComboBox<Payment> paymentBox;
	private static ComboBox<Integer> baggageBox;
	private Button confirmButton;
	
	private Flight flight;
	private double cost;

	private Label costLabel;
	
	public BookingPaymentWindow(Flight flight) {
		this.flight = flight;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		user = APZLauncher.getCurrentUser();
		
		cost = APZMath.getPrice(flight.getDepartureAirport().getCity(), flight.getDestinationAirport().getCity());
		
		mainPane = new VBox(10);
		paymentPane = new GridPane();
		header = new Text("\nProcess Booking");
		
		paymentBox = new ComboBox<>();
		baggageBox = new ComboBox<>();
		confirmButton = new Button("Confirm Booking");
		
		costLabel = new Label("");
	}

	public void content() {
		header.setFont(new Font(32));
		
		loadComboBoxInformation();
		
		ImageView img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		img.setFitWidth(150);
		img.setFitHeight(150);
		
		paymentPane.add(new Label("Flight Date: "), 0, 2);
		paymentPane.add(new Label(flight.getDepartureDate().toString()), 1, 2);
		paymentPane.add(new Label("Airline: "), 0, 3);
		paymentPane.add(new Label(flight.getPlane().getAirline()), 1, 3);
		paymentPane.add(new Label("Departing Airport: "), 0, 4);
		paymentPane.add(new Label(flight.getDepartureAirport().toString()), 1, 4);
		paymentPane.add(new Label("Destination Airport: "), 0, 5);
		paymentPane.add(new Label(flight.getDestinationAirport().toString()), 1, 5);
		
		paymentPane.add(new Label("Extra Baggage: "), 0, 6);
		paymentPane.add(baggageBox, 1, 6);
		
		paymentPane.add(new Label("Cost for Flight: "), 0, 7);
		paymentPane.add(costLabel, 1, 7);
		
		costLabel.setText("$" + cost);
		
		paymentPane.add(new Label("Payment Method: "), 0, 9);
		if (paymentBox.getItems().isEmpty()) {
			Button pAddButton = new Button("Click here to setup a payment method");
			paymentPane.add(pAddButton, 1, 9);
			
			pAddButton.setOnAction(event -> {
				new PaymentAddWindow(new Stage(), flight);
			});
			
		} else 
			paymentPane.add(paymentBox, 1, 9);
		
		
		mainPane.getChildren().addAll(header, img, paymentPane, new Separator(), confirmButton);
		mainPane.setAlignment(Pos.TOP_CENTER);
	}

	public void actionEvents() {
		confirmButton.setOnAction(event -> {
			if(paymentBox.getSelectionModel().isEmpty())
				MessageBox.message(AlertType.ERROR, "ERROR", "You must select a payment method");
			else if(isCardExpired(paymentBox.getSelectionModel().getSelectedItem()))
				MessageBox.message(AlertType.ERROR, "Invalid Payment", "The card you have selected has expired. Please select another payment method");
			else {
					user.getTripList().add(new Booking(flight, LocalDate.now(), user, cost));
					MessageBox.message(AlertType.INFORMATION, null, "Trip has been booked!");
					APZState.saveInformation();
					
					// Splash page thanks for using APZ
			}
		});
		
		baggageBox.setOnAction(event -> {			//
			double baggagePrice = 30.35 * baggageBox.getValue();
			costLabel.setText("$" + (cost + baggagePrice));
//			paymentPane.getChildren().remove(1,  7);
//			paymentPane.add(new Label("$" + (cost + baggagePrice)), 1, 7);
		});
	}

	public void properties() {
		confirmButton.setMinWidth(250);
		paymentPane.setHgap(15);
		paymentPane.setVgap(15);
		paymentPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
	}
	
	
	private void loadComboBoxInformation() {
		for (int i = 0; i < 4; i++) 
			baggageBox.getItems().add(i);
		
		baggageBox.setValue(0);
		
		for (int i = 0; i < user.getPaymentInformation().size(); i++) {
			paymentBox.getItems().add(user.getPaymentInformation().get(i));
			//Long ccNum = user.getPaymentInformation().get(i).getCardNum();
			//paymentBox.getItems().add("Card number ending in " + ccNum.toString().substring(12, 16));
		}
	}
	
	private boolean isCardExpired(Payment payment) {
		boolean result = false;
		String expDate = payment.getExpirationDate();
		String month;
		String year;
		String currentMonth = LocalDate.now().getMonthValue() + "";
		String currentYear = LocalDate.now().getYear() + "";
		
		if(expDate.charAt(0) == '0') {
			month = expDate.substring(1, 2);
			year = expDate.substring(3);
		}
		else {
			month = expDate.substring(0, 2);
			year = expDate.substring(3);
		}
		
		if(Integer.valueOf(year) < Integer.valueOf(currentYear)) 
			result = true;
		else if(Integer.valueOf(year).equals(Integer.valueOf(currentYear)) && (Integer.valueOf(month) < Integer.valueOf(currentMonth)))
			result = true;
	
		return result;
	}
	
	// is full for airport

}
