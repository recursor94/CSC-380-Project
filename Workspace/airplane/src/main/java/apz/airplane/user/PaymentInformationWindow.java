package apz.airplane.user;

import javafx.geometry.Pos;
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

	public PaymentInformationWindow() {
		initialize();
		content();
		properties();
	}
	
	public void initialize() {
		rootPane = new GridPane();
		cardNumField = new TextField();
		nameField = new TextField();
		addressField = new TextField();
		cityField = new TextField();
		zipCodeField = new TextField();
		stateBox = new ComboBox<>();
		submitButton = new Button("Submit");
	}
	
	public void content() {
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
}
