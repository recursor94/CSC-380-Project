package apz.airplane.user;

import apz.airplane.model.User;
import apz.airplane.util.MessageBox;
import apz.airplane.util.APZState;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegisterWindow {

	private TextField userField;
	private PasswordField passField;
	private Button registerButton;
	private Button backButton;
	
	private VBox rootPane;
	private HBox userBox, passBox, buttonBox;
	
	public RegisterWindow() {
		initialize();
		actionEvents();
		content();
		properties();
	}
	
	private void initialize() {
		rootPane = new VBox(10);
		userBox = new HBox(10);
		passBox = new HBox(10);
		buttonBox = new HBox(10);
		userField = new TextField();
		passField = new PasswordField();
		registerButton = new Button("Register");
		backButton = new Button("Back");
	}
	
	private void content() {
		userBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		
		userBox.getChildren().addAll(new Label("Username: "), userField);
		passBox.getChildren().addAll(new Label("Password: "), passField);
		buttonBox.getChildren().addAll(registerButton, backButton);
		
		ImageView img = new ImageView(new Image("file:img.png"));
		img.setFitWidth(150);
		img.setFitHeight(150);
		
		rootPane.getChildren().addAll(img, userBox, passBox, buttonBox);
		
	}
	
	private void properties() {
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setHeight(400);
		APZLauncher.getStage().setWidth(300);
	}
	
	private void actionEvents() {
		registerButton.setOnAction((event) -> {
			if ((userField.getText().isEmpty()) || (passField.getText().isEmpty()))
				MessageBox.message(AlertType.ERROR, "ERROR: You must enter a user name and password", "Please enter a user name and password");
			else
				verifyInput();
		});
		
		backButton.setOnAction(event -> {
			new LoginWindow();
		});
	}
	
	private void verifyInput() {
		//Checks if a user name exists already 
		if (APZLauncher.getUserController().userExists(userField.getText())) {
			System.out.println("The username you chose already exists");
			MessageBox.message(AlertType.ERROR, "ERROR: The User Name Already Exists", "Please choose a different user name");
		}
		//If a user name does not already exist
		else {	
			//Check if the username contains any empty spaces
			if (userField.getText().contains(" ")) {
				MessageBox.message(AlertType.ERROR, "Invalid User Name", "Your user name cannot contain the empty space character");	
			}
			//Check if the password contains empty spaces 
			else if (passField.getText().contains(" ")) {
				MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password cannot contain the empty space character");
			}
			// IF WE WANT A PASSWORD TO BE A CERTAIN LENGTH, WE CAN ADD THIS ELSE IF IN
//						else if (passField.getText().length() < 8) {
//							MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password must be at least 8 characters long");
//						}
			//Create a user if there are not errors in the input
			else {
				User user = new User (userField.getText(), passField.getText());
				APZLauncher.getUserController().addUser(user);
				APZState.saveInformation();
				System.out.println("User successfully created!");
				MessageBox.message(AlertType.INFORMATION, "Successful User Creation", "Your account has been created!");
				System.out.println(APZLauncher.getUserController());
				new LoginWindow();
				//new ViewFlightWindow(primaryStage);
			}	
		}
	}
}
