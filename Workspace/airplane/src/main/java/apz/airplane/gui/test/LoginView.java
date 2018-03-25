package apz.airplane.gui.test;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
	
	private TextField userField;
	private PasswordField passField;
	private Button loginButton;
	private Button createUserButton;
	private HBox buttons = new HBox(10);
	
	private VBox rootPane;
	private HBox userBox, passBox;
	private Stage primaryStage;
	
	public LoginView() {
		primaryStage = DisplayDriver.getStage();
		initialize();
		content();
		actionEvents();
	}
	
	public void initialize() {
		rootPane = new VBox(10);
		userBox = new HBox(10);
		passBox = new HBox(10);

		userField = new TextField();
		passField = new PasswordField();
		
		loginButton = new Button("Login");
		createUserButton = new Button("Register");
		buttons.getChildren().addAll(loginButton, createUserButton);
		buttons.setAlignment(Pos.CENTER);
	}
	
	public void content() {
		userBox.getChildren().addAll(new Label("Username: "), userField);
		passBox.getChildren().addAll(new Label("Password: "), passField);
		rootPane.getChildren().addAll(userBox, passBox, buttons);
		
		userBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		
		DisplayDriver.getBorderPane().setCenter(rootPane);
	}
	
	public void actionEvents() {
		//DisplayDriver.getUserController().addUser(new User("asd", "asd"));
		loginButton.setOnAction((event) -> {
			new ViewFlightWindow(primaryStage);
//			User user = DisplayDriver.getUserController().login(userField.getText(), passField.getText());
//			
//			if (user != null) {
//				System.out.println("Username: " + userField.getText());
//				System.out.println("Password: " + passField.getText());
//				Alert msg = new Alert(AlertType.INFORMATION);
//				msg.setTitle("Successful Login");
//				msg.setHeaderText(null);
//				msg.setContentText("Your login was successful");
//				msg.showAndWait();
//			}
//			else {
//				System.out.println("Invalid user name or password");
//				Alert alert2 = new Alert(AlertType.ERROR);
//				alert2.setTitle("Invalid Login");
//				alert2.setContentText("ERROR: You must enter a valid user name and password");
//				alert2.setHeaderText(null);
//				alert2.showAndWait();
//				System.out.println("You must enter a user name and a password");
//			}
			
//			primaryStage.close();
		});
		
		createUserButton.setOnAction(event -> {
			new UserRegistrationView();
		});
	}
	
}
