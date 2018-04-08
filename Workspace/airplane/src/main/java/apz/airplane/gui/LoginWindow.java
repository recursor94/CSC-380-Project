package apz.airplane.gui;

import java.time.LocalDate;

import apz.airplane.User;
import apz.airplane.util.MessageBox;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow {
	
	// if we're going to do this, lets make a text dir
	
	private int loginAttempt = 0;

	private TextField userField;
	private PasswordField passField;
	private Button loginButton;
	private Button createUserButton;
	
	private VBox rootPane;
	private HBox userBox, passBox, buttonBox;
	private Stage primaryStage;
	
	private Text status;
	
	public LoginWindow() {
		primaryStage = APZLauncher.getStage();
		initialize();
		content();
		actionEvents();
	}
	
	public void initialize() {
		rootPane = new VBox(10);
		userBox = new HBox(10);
		passBox = new HBox(10);
		buttonBox = new HBox(10);

		userField = new TextField();
		passField = new PasswordField();
		
		status = new Text("");
		status.setFill(Color.RED);
		
		loginButton = new Button("Login");
		createUserButton = new Button("Register");
	}
	
	public void content() {
		userBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		
		userBox.getChildren().addAll(new Label("Username: "), userField);
		passBox.getChildren().addAll(new Label("Password: "), passField);
		buttonBox.getChildren().addAll(loginButton, createUserButton);
		rootPane.getChildren().addAll(status, userBox, passBox, buttonBox);
		
		APZLauncher.getBorderPane().setCenter(rootPane);
	}
	
	public void actionEvents() {
		
		
		rootPane.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER)
				tryLogin();
		});
		
		loginButton.setOnAction((event) -> {
			tryLogin();
		});
		
		createUserButton.setOnAction(event -> {
			new RegisterWindow();
		});
	}
	
	private void tryLogin() {
		++loginAttempt;
		User user = APZLauncher.getUserController().login(userField.getText(), passField.getText());
		if (user != null) {
			System.out.println("Username: " + userField.getText());
			System.out.println("Password: " + passField.getText());
			
			MessageBox.message(AlertType.INFORMATION, "APZ Airplane Application", "Welcome " + userField.getText() + " to the APZ Application!");
			APZLauncher.setCurrentUser(user);
			new UtilMenuBar();					// on logout it will remove menubar
			new BookFlightByDestinationWindow();
		} 
		else {
			if (loginAttempt < 5) 
				status.setText("Login Attempt " + loginAttempt + "\nIncorrect user or password combination!");
			else if (loginAttempt == 5) {
				status.setText("Last login attempt" + "\nIncorrect user or password combination!");
			} else if (loginAttempt > 5){
				MessageBox.message(AlertType.ERROR, null, "Your account has been locked out. Try again in 5 minutes!");
				Platform.exit();
			}
		}
	}
	
}
