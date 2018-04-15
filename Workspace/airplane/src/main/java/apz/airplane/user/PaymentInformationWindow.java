package apz.airplane.user;

import apz.airplane.model.Payment;
import apz.airplane.model.User;
import apz.airplane.util.APZState;
import apz.airplane.util.IsInteger;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PaymentInformationWindow {
	
	private GridPane rootPane;
	private TextField cardNumField, nameField, addressField, cityField, zipCodeField;
	private ComboBox<String> stateBox;
	private Button submitButton;
	private User user;

	public PaymentInformationWindow() {
		initialize();
		content();
		properties();
		actionEvents();
	}
	
	private void initialize() {
		rootPane = new GridPane();
		cardNumField = new TextField();
		nameField = new TextField();
		addressField = new TextField();
		cityField = new TextField();
		zipCodeField = new TextField();
		stateBox = new ComboBox<>();
		submitButton = new Button("Submit");
	}
	
	private void content() {
		
		user = APZLauncher.getCurrentUser();
		populateStates();
		
		rootPane.setHgap(10);
		rootPane.setVgap(10);
		
		rootPane.add(new Label("Credit Card Number: "), 0, 0);
		rootPane.add(cardNumField, 1, 0);
		
		rootPane.add(new Label("Enter Your Name: "), 0, 1);
		rootPane.add(nameField, 1, 1);
		
		rootPane.add(new Label("Enter Your Address: "), 0, 2);
		rootPane.add(addressField, 1, 2);	
		
		rootPane.add(new Label("Enter Your City: "), 0, 3);
		rootPane.add(cityField, 1, 3);
		
		rootPane.add(new Label("Enter Your Zip Code: "), 0, 4);
		rootPane.add(zipCodeField, 1, 4);
		
		rootPane.add(new Label("Select Your State: "), 0, 5);
		rootPane.add(stateBox, 1, 5);
		
		rootPane.add(submitButton, 0, 6);
		
		rootPane.setAlignment(Pos.CENTER);
	}
	private void properties() {
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setHeight(425);
		APZLauncher.getStage().setWidth(500);
	}
	
	private void actionEvents() {
		submitButton.setOnAction(event -> {
			verifyInput();
		});
	}
	
	private void verifyInput () {
		
		String name = nameField.getText();
		String street = addressField.getText();
		String city = cityField.getText();
		String state = stateBox.getSelectionModel().getSelectedItem();
		int zip = Integer.valueOf(zipCodeField.getText());
		long cardNum = Long.valueOf(cardNumField.getText());						// don't use int. OVERFLOW
		Payment payment = new Payment(name, street, city, state, zip, cardNum);
		user.addPayment(payment);
		APZState.saveInformation(APZLauncher.getUserController());
		
		//ERROR WITH EXCEPTIONS. WAY TOO LENGTHY
		
//		if(!(cardNumField.getText().isEmpty()) && !(nameField.getText().isEmpty()) && !(zipCodeField.getText().isEmpty())
//				&& !(addressField.getText().isEmpty()) && !(cityField.getText().isEmpty()) && !(stateBox.getSelectionModel().isEmpty()) ) {
//			if(IsInteger.isInteger(cardNumField.getText())) {
//				if(IsInteger.isInteger(zipCodeField.getText())) {
//					if(cardNumField.getText().length() == 16) {
//						if(zipCodeField.getText().length() == 5) {
//							String name = nameField.getText();
//							String street = addressField.getText();
//							String city = cityField.getText();
//							String state = stateBox.getSelectionModel().getSelectedItem();
//							int zip = Integer.valueOf(zipCodeField.getText());
//							int cardNum = Integer.valueOf(cardNumField.getText());
//							Payment payment = new Payment(name, street, city, state, zip, cardNum);
//							user.addPayment(payment);
//							APZState.saveInformation(APZLauncher.getUserController());
//						}
//						else {
//							MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter a 5 digit zip code");
//						}
//					}
//					else {
//						MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter a 16 digit card number");
//					}		
//				}
//				else {
//					MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter an integer for a zip code");
//				}
//			}
//			else {
//				MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter an integer for a card number");
//			}
//		}
//		else {
//			MessageBox.message(AlertType.ERROR, "Invalid Data Entry", "You must enter data into all fields");
//		}
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
}
