package apz.airplane.user;

import apz.airplane.model.User;
import apz.airplane.util.FilePath;
import apz.airplane.util.MessageBox;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jimmy.pack.WindowInterface;

public class LoginWindow implements WindowInterface {
	
	private int loginAttempt = 0;

	private TextField userField;
	private PasswordField passField;
	private Button loginButton, registerButton;
	
	private VBox rootPane;
	private HBox userBox, passBox, buttonBox;
	private Text status, header;
	
	public LoginWindow() {
		initialize();
		content();
		actionEvents();
		properties();
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
		
		header = new Text("APZ Application");
		
		loginButton = new Button("Login");
		registerButton = new Button("Register");
	}
	
	public void content() {
		
		header.setFont(new Font(20));
		userBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		
		userBox.getChildren().addAll(new Label("Username: "), userField);
		passBox.getChildren().addAll(new Label("Password: "), passField);
		buttonBox.getChildren().addAll(loginButton, registerButton);
		
		ImageView img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		img.setFitWidth(150);
		img.setFitHeight(150);
		
		rootPane.getChildren().addAll(header, img, status, userBox, passBox, buttonBox);
	}
	
	public void properties() {
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setWidth(320);
		APZLauncher.getStage().setHeight(410);
		APZLauncher.getStage().setTitle("APZ Application - User Login");
	}
	
	public void actionEvents() {
		rootPane.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER)
				tryLogin();
		});
		
		loginButton.setOnAction((event) -> {
			tryLogin();
		});
		
		registerButton.setOnAction(event -> {
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
			new APZMenuBar();
			//new BookFlightByDestinationWindow();
//			new APZMenuBar();
			new HomeScreenWindow();
//			new BookFlightByDestinationWindow();
		} else {
			if (loginAttempt < 5) 
				status.setText("Login Attempt " + loginAttempt + "\nIncorrect user or password combination!");
			else if (loginAttempt == 5)
				status.setText("Last login attempt" + "\nIncorrect user or password combination!");
			else if (loginAttempt > 5){
				MessageBox.message(AlertType.ERROR, null, "Your account has been locked out. Try again in 5 minutes!");
				Platform.exit();
			}
		}
	}
	
}
