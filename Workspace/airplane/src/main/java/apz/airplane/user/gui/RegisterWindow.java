package apz.airplane.user.gui;

import apz.airplane.model.User;
import apz.airplane.util.APZState;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
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
import javafx.util.Duration;

public class RegisterWindow implements GuiApplication {

	private ImageView img;
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
		img = new ImageView(new Image("file:resource/logo.png"));
		userField = new TextField();
		emailField = new TextField();
		header = new Text("User Registration");
		passField = new PasswordField();
		registerButton = new Button("Register");
		backButton = new Button("Back");
		rootPane = new VBox(10);
		userBox = new HBox(10);
		emailBox = new HBox(10);
		passBox = new HBox(10);
		buttonBox = new HBox(10);
	}

	public void content() {
		userBox.getChildren().addAll(new Label("      Username: "), userField);
		emailBox.getChildren().addAll(new Label("Email Address: "), emailField);
		passBox.getChildren().addAll(new Label("       Password: "), passField);
		buttonBox.getChildren().addAll(registerButton, backButton);
		rootPane.getChildren().addAll(header, img, userBox, emailBox, passBox, buttonBox);

		RotateTransition rt = new RotateTransition(Duration.millis(3000), img);
		rt.setByAngle(-15);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.setAutoReverse(true);

		rt.play();
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

	public void properties() {
		header.setFont(new Font(20));
		userBox.setAlignment(Pos.CENTER);
		emailBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		img.setFitWidth(150);
		img.setFitHeight(150);
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setTitle("APZ Application - Register Account");
	}

	private void tryRegister() {
		if ((userField.getText().isEmpty()) || (passField.getText().isEmpty()) || emailField.getText().isEmpty())
			MessageBox.message(AlertType.ERROR, null, "Please enter a user name, email, and password");
		else
			verifyInput();
	}

	private void verifyInput() {
		if (APZLauncher.getUserController().userExists(userField.getText()))
			MessageBox.message(AlertType.ERROR, "ERROR: The User Name Already Exists", "Please choose a different user name");
		else {
			if (userField.getText().contains(" "))
				MessageBox.message(AlertType.ERROR, "Invalid User Name", "Your user name cannot contain the empty space character");
			else if (passField.getText().contains(" "))
				MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password cannot contain the empty space character");
			else if (!emailField.getText().contains("@"))
				MessageBox.message(AlertType.ERROR, "Invalid Email", "Your email must be properly formatted with a directory!");
			else if (!emailField.getText().contains("."))
				MessageBox.message(AlertType.ERROR, "Invalid Email", "Your email must be properly formatted with a domain!");
			else {
				User user = new User (emailField.getText(), userField.getText(), passField.getText());
				APZLauncher.getUserController().addUser(user);
				APZState.saveInformation();
				MessageBox.message(AlertType.INFORMATION, "Successful User Creation", "Your account has been created!");
				new LoginWindow();
			}
		}
	}
}
