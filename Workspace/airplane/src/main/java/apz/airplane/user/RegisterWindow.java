package apz.airplane.user;

import apz.airplane.model.User;
import apz.airplane.util.APZState;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jimmy.pack.GuiApplication;

public class RegisterWindow implements GuiApplication {

	private TextField userField, emailField;
	private Text header;
	private PasswordField passField;
	private Button registerButton;
	private Button backButton;
	
	private VBox rootPane;
	private HBox userBox, emailBox, passBox, buttonBox;
	
	public RegisterWindow() {
		initialize();
		actionEvents();
		content();
		properties();
	}
	
	public void initialize() {
		header = new Text("User Registration");
		rootPane = new VBox(10);
		userBox = new HBox(10);
		passBox = new HBox(10);
		emailBox = new HBox(10);
		buttonBox = new HBox(10);
		userField = new TextField();
		emailField = new TextField();
		passField = new PasswordField();
		registerButton = new Button("Register");
		backButton = new Button("Back");
	}
	
	public void content() {
		header.setFont(new Font(20));
		
		userBox.setAlignment(Pos.CENTER);
		emailBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		
		userBox.getChildren().addAll(new Label("      Username: "), userField);
		emailBox.getChildren().addAll(new Label("Email Address: "), emailField);
		passBox.getChildren().addAll(new Label("       Password: "), passField);
		buttonBox.getChildren().addAll(registerButton, backButton);
		
		ImageView img = new ImageView(new Image("file:img.png"));
		img.setFitWidth(150);
		img.setFitHeight(150);
		
		rootPane.getChildren().addAll(header, img, userBox, emailBox, passBox, buttonBox);
		
	}
	
	public void properties() {
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setTitle("APZ Application - Register Account");
	}
	
	public void actionEvents() {
		registerButton.setOnAction((event) -> {
			tryRegister();
		});
		
		backButton.setOnAction(event -> {
			new LoginWindow();
		});
		
		rootPane.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER)
				tryRegister();
		});
	}
	
	private void tryRegister() {
		if ((userField.getText().isEmpty()) || (passField.getText().isEmpty()) || emailField.getText().isEmpty())
			MessageBox.message(AlertType.ERROR, null, "Please enter a user name, email, and password");
		else
			verifyInput();
	}
	
	private void verifyInput() {
		//Checks if a user name exists already 
		if (APZLauncher.getUserController().userExists(userField.getText())) {
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
				User user = new User (emailField.getText(), userField.getText(), passField.getText());
				APZLauncher.getUserController().addUser(user);
				APZState.saveInformation();
				System.out.println("User successfully created!");
				MessageBox.message(AlertType.INFORMATION, "Successful User Creation", "Your account has been created!");
				new LoginWindow();
			}	
		}
	}
}
