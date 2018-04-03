package apz.airplane.gui;

import apz.airplane.User;
import apz.airplane.util.MessageBox;
import apz.airplane.util.State;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterWindow {

	private TextField userField;
	private PasswordField passField;
	private Button registerButton;
	
	private VBox rootPane;
	private HBox userBox, passBox;
	
	public RegisterWindow() {
		initialize();
		actionEvents();
		content();
	}
	
	public void initialize() {
		rootPane = new VBox(10);
		userBox = new HBox(10);
		passBox = new HBox(10);

		userField = new TextField();
		passField = new PasswordField();
		
		registerButton = new Button("Register");
	}
	
	public void content() {
		userBox.getChildren().addAll(new Label("Username: "), userField);
		passBox.getChildren().addAll(new Label("Password: "), passField);
		rootPane.getChildren().addAll(userBox, passBox, registerButton);
		
		userBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		
		APZLauncher.getBorderPane().setCenter(rootPane);
	}
	
	public void actionEvents() {
		//I made a static method in a new class called VerifyUserInput that I may use here. I am using it in the admin UserManagement class
		registerButton.setOnAction((event) -> {
			
		//Checks if a user name exists already 
		if (APZLauncher.getUserController().userExists(userField.getText())) {
			System.out.println("The username you chose already exists");
			MessageBox.message(AlertType.ERROR, "ERROR: The User Name Already Exists", "Please choose a different user name");
		}
		//If a user name does not already exist
		else {
			//Check if the text fields are empty
			if (!(userField.getText().isEmpty()) && !(passField.getText().isEmpty())) {
				
				//Check if the username contains any empty spaces
				if (userField.getText().contains(" ")) {
					MessageBox.message(AlertType.ERROR, "Invalid User Name", "Your user name cannot contain the empty space character");
					
				}
				//Check if the password contains empty spaces 
				else if (passField.getText().contains(" ")) {
					MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password cannot contain the empty space character");
				}
				// IF WE WANT A PASSWORD TO BE A CERTAIN LENGTH, WE CAN ADD THIS ELSE IF IN
//				else if (passField.getText().length() < 8) {
//					MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password must be at least 8 characters long");
//				}
				//Create a user if there are not errors in the input
				else {
					User user = new User (userField.getText(), passField.getText());
					APZLauncher.getUserController().addUser(user);
					State.saveInformation(APZLauncher.getUserController());
					System.out.println("User successfully created!");
					MessageBox.message(AlertType.INFORMATION, "Successful User Creation", "Your account has been created!");
					System.out.println(APZLauncher.getUserController());
					new LoginWindow();
					//new ViewFlightWindow(primaryStage);
				}	
			}
			//Display error if the textfields are empty
			else {
				MessageBox.message(AlertType.ERROR, "ERROR: You must enter a user name and password", "Please enter a user name and password");
			}
		}
		});
	}
}
