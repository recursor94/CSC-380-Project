package apz.jimmeh.idostuff;

import java.time.LocalTime;

import apz.airplane.SessionCache;
import apz.airplane.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginWindow {
	
	// if we're going to do this, lets make a text dir

	private TextField userField;
	private PasswordField passField;
	private Button loginButton;
	private Button createUserButton;
	
	private VBox rootPane;
	private HBox userBox, passBox;
	private Stage primaryStage;
	
	public LoginWindow() {
		primaryStage = DisplayDriver.getStage();
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	public void initialize() {
		rootPane = new VBox(10);
		userBox = new HBox(10);
		passBox = new HBox(10);

		userField = new TextField();
		passField = new PasswordField();
		
		loginButton = new Button("Login");
		createUserButton = new Button("Register");
	}
	
	public void content() {
		userBox.getChildren().addAll(new Label("Username: "), userField);
		passBox.getChildren().addAll(new Label("Password: "), passField);
		rootPane.getChildren().addAll(userBox, passBox, loginButton, createUserButton);
		
		userBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		
		DisplayDriver.getBorderPane().setCenter(rootPane);
	}
	
	public void actionEvents() {
		DisplayDriver.getUserController().addUser(new User("asd", "asd"));
		
		loginButton.setOnAction((event) -> {
			User user = DisplayDriver.getUserController().login(userField.getText(), passField.getText());
			
			if (user != null) {
				SessionCache session = new SessionCache(user, LocalTime.now());
				System.out.println("Username: " + userField.getText());
				System.out.println("Password: " + passField.getText());
				System.out.println("Session: " + session);
				
//				new CancelFlightView(primaryStage);
			}
			
//			primaryStage.close();
		});
		
		createUserButton.setOnAction(event -> {
			new RegisterWindow();
		});
	}
	
	public void properties() {
	}
	
}
