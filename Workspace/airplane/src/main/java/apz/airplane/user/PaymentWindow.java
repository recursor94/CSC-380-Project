package apz.airplane.user;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PaymentWindow {
	
	private GridPane rootPane;
	private TextField cardNumField, nameField, addressField, cityField, zipCodeField;
	private ComboBox<String> stateBox;
	private Button submitButton;

	public PaymentWindow() {
		initialize();
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
		
	}
}
