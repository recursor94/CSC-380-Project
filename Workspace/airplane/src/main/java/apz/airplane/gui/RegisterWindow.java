package apz.airplane.gui;

import apz.airplane.User;
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
		registerButton.setOnAction((event) -> {
			
		//Checks if a user name exists already
		if (APZLauncher.getUserController().userExists(userField.getText())) {
			System.out.println("The username you chose already exists");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("User Name Already Exists");
			alert.setHeaderText("ERROR: The User Name Already Exists");
			alert.setContentText("Please choose a different user name");
			alert.showAndWait();
		}
		//If a user name does not already exist
		else {
			//If the text fields are not empty, it creates a user 
			if (!(userField.getText().isEmpty()) && !(passField.getText().isEmpty())) {
				User user = new User (userField.getText(), passField.getText());
				APZLauncher.getUserController().addUser(user);
				State.saveInformation(APZLauncher.getUserController());
				System.out.println("User successfully created!");
				Alert msg = new Alert(AlertType.INFORMATION);
				msg.setTitle("Successful User Creation");
				msg.setHeaderText(null);
				msg.setContentText("Your account has been created!");
				msg.showAndWait();
				System.out.println(APZLauncher.getUserController());
				new LoginWindow();
				//new ViewFlightWindow(primaryStage);
				
			}
			else {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("Empty Field");
				alert2.setHeaderText("ERROR: You must enter a user name and password");
				alert2.setContentText("Please enter a user name and password");
				alert2.showAndWait();
				System.out.println("You must enter a user name and a password");
			}
		}
		});
	}
}
