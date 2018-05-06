package apz.airplane.user.gui.account;

import apz.airplane.model.Flight;
import apz.airplane.model.Payment;
import apz.airplane.model.User;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.booking.BookingPaymentWindow;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentAddWindow implements GuiApplication {
	
	private ImageView img;
	private Text header;
	private VBox mainPane;
	private HBox expirationPane, infoPane;
	private GridPane gridPane;
	private TextField cardNumField, CCVNumField, nameField, addressField, cityField, zipCodeField;
	private ComboBox<String> monthBox, yearBox, stateBox;
	private Button submitButton;
	private User user;
	private Stage stage = null;
	private Flight flight = null;

	public PaymentAddWindow() {
		initialize();
		content();
		properties();
		actionEvents();
	}
	
	public PaymentAddWindow(Stage stage, Flight flight) {
		this.stage = stage;
		this.flight = flight;
		initialize();
		content();
		properties();
		actionEvents();
	}
	
	public void initialize() {
		img = new ImageView(new Image(FilePath.PAYMENT_ADD_IMAGE));
		header = new Text("Enter Payment Information");
		mainPane = new VBox(10);
		infoPane = new HBox(10);
		expirationPane = new HBox(10);
		gridPane = new GridPane();
		cardNumField = new TextField();
		CCVNumField = new TextField();
		nameField = new TextField();
		addressField = new TextField();
		cityField = new TextField();
		zipCodeField = new TextField();
		monthBox = new ComboBox<>();
		yearBox = new ComboBox<>();
		stateBox = new ComboBox<>();
		submitButton = new Button("Submit");
		user = APZLauncher.getCurrentUser();
	}
	
	public void content() {
		populateExpirationFields();
		populateStates();
		
		expirationPane.getChildren().addAll(monthBox, yearBox);
		
		infoPane.getChildren().addAll(cardNumField, CCVNumField);
		
		gridPane.add(new Label("Credit Card Number: "), 0, 0);
		gridPane.add(infoPane, 1, 0);
		
		gridPane.add(new Label("Select Expiration Date: "), 0, 1);
		gridPane.add(expirationPane, 1, 1);
		
		gridPane.add(new Label("Enter Your Name: "), 0, 2);
		gridPane.add(nameField, 1, 2);
		
		gridPane.add(new Label("Enter Your Address: "), 0, 3);
		gridPane.add(addressField, 1, 3);	
		
		gridPane.add(new Label("Enter Your City: "), 0, 4);
		gridPane.add(cityField, 1, 4);
		
		gridPane.add(new Label("Enter Your Zip Code: "), 0, 5);
		gridPane.add(zipCodeField, 1, 5);
		
		gridPane.add(new Label("Select Your State: "), 0, 6);
		gridPane.add(stateBox, 1, 6);
		
		mainPane.getChildren().addAll(header, img, new Separator(), gridPane, submitButton);
	}
	
	public void actionEvents() {
		submitButton.setOnAction(event -> {
			verifyInput();
		});
		
		mainPane.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER)
				verifyInput();
		});
		
		zipCodeField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (!"0123456789".contains(keyEvent.getCharacter()))
		            keyEvent.consume();
				if (zipCodeField.getText().length() >= 5) {
					zipCodeField.setText(zipCodeField.getText(0, (zipCodeField.getText().length() - 1)));
					zipCodeField.positionCaret(zipCodeField.getText().length());
				}
	        }
	      });
		
		cardNumField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
					if (!"0123456789".contains(keyEvent.getCharacter()))
						keyEvent.consume();
					if (cardNumField.getText().length() >= 16) {
						cardNumField.setText(cardNumField.getText(0, (cardNumField.getText().length() - 1)));
						cardNumField.positionCaret(cardNumField.getText().length());
					}
	        }
	      });
		
		CCVNumField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (!"0123456789".contains(keyEvent.getCharacter()))
		            keyEvent.consume();
				
				if (CCVNumField.getText().length() >= 3) {
					CCVNumField.setText(CCVNumField.getText(0, (CCVNumField.getText().length() - 1)));
					CCVNumField.positionCaret(CCVNumField.getText().length());
				}
	        }
	      });
	}
	
	public void properties() {
		img.setFitWidth(250);
		img.setFitHeight(150);
		header.setFont(new Font(32));
		CCVNumField.setMaxWidth(50);
		CCVNumField.setTooltip(new Tooltip("CCV"));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		mainPane.setAlignment(Pos.CENTER);
		
		if (stage == null) {
			APZLauncher.getBorderPane().setCenter(mainPane);
			APZLauncher.getStage().setTitle("APZ Application - Add Payment Method");
		}
		else {
			stage.setScene(new Scene(mainPane, 500, 725));
			stage.setTitle("APZ Application - Add Payment Method");
			stage.show();
		}
	}
	
	private void verifyInput () {
		
		if(!(cardNumField.getText().isEmpty()) && !(nameField.getText().isEmpty()) && !(zipCodeField.getText().isEmpty())
			&& !(addressField.getText().isEmpty()) && !(cityField.getText().isEmpty()) && !(CCVNumField.getText().isEmpty()) 
			&& !(stateBox.getSelectionModel().isEmpty()) && !(monthBox.getSelectionModel().isEmpty()) 
			&& !(yearBox.getSelectionModel().isEmpty()) ) {
			
		    if(cardNumField.getText().length() != 16)
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter a 16 digit card number");
			else if(zipCodeField.getText().length() != 5)
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter a 5 digit zip code");
			else if(CCVNumField.getText().length() != 3)
				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter a 3 digit CCV Number");
			else {
				String name = nameField.getText();
				String street = addressField.getText();
				String city = cityField.getText();
				String state = stateBox.getSelectionModel().getSelectedItem();
				int zip = Integer.valueOf(zipCodeField.getText());
				long cardNum = Long.valueOf(cardNumField.getText());
				String expirationDate = monthBox.getSelectionModel().getSelectedItem() + "/" + yearBox.getSelectionModel().getSelectedItem();
				int CCV = Integer.valueOf(CCVNumField.getText());
				user.addPayment(new Payment(name, street, city, state, zip, cardNum, expirationDate, CCV));
				APZState.saveInformation();
				if (stage != null) {
					new BookingPaymentWindow(flight);
					stage.close();
				}
				else
					new PaymentWindow();
			}
		}
		else 
			MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter data into all fields");
	}
	
	private void populateStates() {
		stateBox.getItems().add("AL");
		stateBox.getItems().add("AK");
		stateBox.getItems().add("AR");
		stateBox.getItems().add("AZ");
		stateBox.getItems().add("CA");
		stateBox.getItems().add("CO");
		stateBox.getItems().add("CT");
		stateBox.getItems().add("DE");
		stateBox.getItems().add("FL");
		stateBox.getItems().add("GA");
		stateBox.getItems().add("HI");
		stateBox.getItems().add("IA");
		stateBox.getItems().add("ID");
		stateBox.getItems().add("IL");
		stateBox.getItems().add("IN");
		stateBox.getItems().add("KS");
		stateBox.getItems().add("KY");
		stateBox.getItems().add("LA");
		stateBox.getItems().add("MA");
		stateBox.getItems().add("MD");
		stateBox.getItems().add("ME");
		stateBox.getItems().add("MI");
		stateBox.getItems().add("MN");
		stateBox.getItems().add("MO");
		stateBox.getItems().add("MS");
		stateBox.getItems().add("MT");
		stateBox.getItems().add("NC");
		stateBox.getItems().add("ND");
		stateBox.getItems().add("NE");
		stateBox.getItems().add("NH");
		stateBox.getItems().add("NJ");
		stateBox.getItems().add("NM");
		stateBox.getItems().add("NV");
		stateBox.getItems().add("NY");
		stateBox.getItems().add("OH");
		stateBox.getItems().add("OK");
		stateBox.getItems().add("OR");
		stateBox.getItems().add("PA");
		stateBox.getItems().add("RI");
		stateBox.getItems().add("SC");
		stateBox.getItems().add("SD");
		stateBox.getItems().add("TN");
		stateBox.getItems().add("TX");
		stateBox.getItems().add("UT");
		stateBox.getItems().add("VA");
		stateBox.getItems().add("VT");
		stateBox.getItems().add("WA");
		stateBox.getItems().add("WI");
		stateBox.getItems().add("WV");
		stateBox.getItems().add("WY");
	}
	
	private void populateExpirationFields() {
		for(int i = 1; i < 13; i ++) {
			
			if(i < 10)
				monthBox.getItems().add("0" + i);
			else 
				monthBox.getItems().add("" + i);
		}
		
		for(int i = 2018; i < 2026; i ++) 
			yearBox.getItems().add("" + i);	
	}
}
