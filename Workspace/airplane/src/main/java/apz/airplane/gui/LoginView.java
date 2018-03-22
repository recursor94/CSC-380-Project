package apz.airplane.gui;

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

public class LoginView {
	
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 400;
	
	// if we're going to do this, lets make a text dir
	private static final String APP_TITLE = "APZ Airplane Application";
	private static final String LABEL_USERNAME = "Username: ";
	private static final String LABEL_PASSWORD = "Password: ";
	private static final String BUTTON_LOGIN = "Login";

	private TextField userField;
	private PasswordField passField;
	private Button loginButton;
	
	private VBox rootPane;
	private HBox userBox, passBox;
	private Stage primaryStage;
	
	public LoginView(Stage primaryStage) {
		this.primaryStage = primaryStage;
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
		
		loginButton = new Button(BUTTON_LOGIN);
	}
	
	public void content() {
		userBox.getChildren().addAll(new Label(LABEL_USERNAME), userField);
		passBox.getChildren().addAll(new Label(LABEL_PASSWORD), passField);
		rootPane.getChildren().addAll(userBox, passBox, loginButton);
		
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
				
				new CancelFlightView(primaryStage);
			}
			
//			primaryStage.close();
		});
	}
	
	public void properties() {
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(new Scene(DisplayDriver.getBorderPane(), FRAME_WIDTH, FRAME_HEIGHT));
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.show();
	}
	
}
