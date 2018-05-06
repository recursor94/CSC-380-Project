package apz.airplane.user.gui.booking;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.Payment;
import apz.airplane.model.Province;
import apz.airplane.model.User;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.account.PaymentAddWindow;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import apz.car.gui.CarPickerWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookingPaymentWindow implements GuiApplication {
	
	private ImageView img;
	private User user;
	private VBox mainPane;
	private static GridPane paymentPane; 
	private Text header;
	private static ComboBox<Payment> paymentBox;
	private static ComboBox<Integer> baggageBox;
	private Button confirmButton;
	private DecimalFormat df;;
	private Flight flight;
	private double cost, baggagePrice;
	private Label costLabel;
	
	public BookingPaymentWindow(Flight flight) {
		this.flight = flight;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.PAYMENT_INFO_IMAGE));
		user = APZLauncher.getCurrentUser();
		
		mainPane = new VBox(10);
		paymentPane = new GridPane();
		header = new Text("\nPayment");
		
		paymentBox = new ComboBox<>();
		baggageBox = new ComboBox<>();
		confirmButton = new Button("Confirm Payment");
		
		df = new DecimalFormat(".00");
		
		cost = Province.getPrice(flight.getDepartureAirport().getCity(), flight.getDestinationAirport().getCity());
		
		costLabel = new Label("");
	}

	public void content() {
		header.setFont(new Font(32));
		
		loadComboBoxInformation();
		
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
				MessageBox.message(AlertType.ERROR, "Invalid Payment", "The card you have selected has expired. Please select another payment method");	// this should not be here. Should make card not visible if expired.
			else {
					Booking trip = new Booking(flight, LocalDate.now(), (cost + baggagePrice));
					user.getTripList().add(trip);
					MessageBox.message(AlertType.INFORMATION, null, "Trip has been booked!");
					APZState.saveInformation();
					
					ArrayList<Flight> fList = APZState.loadFlights();
					for (int i = 0; i < fList.size(); i++) {
						if (fList.get(i).getFlightNum() == trip.getFlight().getFlightNum()) {
							fList.set(i, trip.getFlight());
							break;
						}
					}
					APZState.saveFlight(fList);
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation Dialog with Custom Actions");
					alert.setHeaderText("APZ Auto Rental");
					alert.setContentText("Would you like to rent a vehicle as well at our auto airport location in " + 
							flight.getDestinationAirport().getName() + ", " + flight.getDestinationAirport().getCity()  +"?");

					ButtonType yesButton = new ButtonType("Yes");
					ButtonType cancelButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);

					alert.getButtonTypes().setAll(yesButton, cancelButton);
					
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == yesButton){
						APZLauncher.getStage().getScene().getRoot().setEffect(new GaussianBlur());
						new CarPickerWindow(new Stage(), APZLauncher.getCurrentUser());
					} else {
						new HomeScreenWindow();
					}
					
			}
		});
		
		baggageBox.setOnAction(event -> {
			baggagePrice = 30.35 * baggageBox.getValue();
			costLabel.setText("$" + df.format(cost + baggagePrice));
		});
		
		
	}

	public void properties() {
		img.setFitWidth(250);
		img.setFitHeight(150);
		confirmButton.setMinWidth(250);
		paymentPane.setHgap(15);
		paymentPane.setVgap(15);
		paymentPane.setAlignment(Pos.TOP_CENTER);
		costLabel.setText("$" + df.format(cost));
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Pay for Booking");
	}
	
	private void loadComboBoxInformation() {
		for (int i = 0; i < 4; i++) 
			baggageBox.getItems().add(i);
		
		baggageBox.setValue(0);
		
		for (int i = 0; i < user.getPaymentInformation().size(); i++) {
			paymentBox.getItems().add(user.getPaymentInformation().get(i));
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
}
