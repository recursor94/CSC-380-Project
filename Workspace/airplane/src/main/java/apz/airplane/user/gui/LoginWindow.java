package apz.airplane.user.gui;

import apz.airplane.model.User;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
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
import javafx.util.Duration;

public class LoginWindow implements GuiApplication {

	private int loginAttempt = 0;

	private ImageView img;
	private TextField userField;
	private PasswordField passField;
	private Button loginButton, registerButton;
	private VBox rootPane;
	private HBox userBox, passBox, buttonBox;
	private Text status, header;

	public LoginWindow() {
		if (APZLauncher.getBorderPane().getTop() instanceof MenuBar)
			APZLauncher.getBorderPane().setTop(null);
		
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.LOGO_IMAGE));
		userField = new TextField();
		passField = new PasswordField();
		loginButton = new Button("Login");
		registerButton = new Button("Register");
		rootPane = new VBox(10);
		userBox = new HBox(10);
		passBox = new HBox(10);
		buttonBox = new HBox(10);
		status = new Text("");
		header = new Text("APZ Project Application");
	}

	public void content() {
		userBox.getChildren().addAll(new Label("Username: "), userField);
		passBox.getChildren().addAll(new Label("Password: "), passField);
		buttonBox.getChildren().addAll(loginButton, registerButton);

		RotateTransition rt = new RotateTransition(Duration.millis(3000), img);
		rt.setByAngle(15);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.setAutoReverse(true);

		rt.play();

		rootPane.getChildren().addAll(img, header, status, userBox, passBox, buttonBox);
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

	public void properties() {
		img.setFitWidth(170);
		img.setFitHeight(170);
		status.setFill(Color.RED);
		header.setFont(new Font(20));
		userBox.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setWidth(320);
		APZLauncher.getStage().setHeight(430);
		APZLauncher.getStage().setTitle("APZ Application - User Login");
	}
	
	private void tryLogin() {
		++loginAttempt;
		User user = APZLauncher.getUserController().login(userField.getText(), passField.getText());
		if (user != null) {
			MessageBox.message(AlertType.INFORMATION, "APZ Airplane Application",
					"Welcome " + userField.getText() + " to the APZ Application!");
			APZLauncher.setCurrentUser(user);
			new APZMenuBar();
			new HomeScreenWindow();
		} else {
			if (loginAttempt < 5)
				status.setText("Login Attempt " + loginAttempt + "\nIncorrect user or password combination!");
			else if (loginAttempt == 5)
				status.setText("Last login attempt" + "\nIncorrect user or password combination!");
			else if (loginAttempt > 5) {
				MessageBox.message(AlertType.ERROR, null, "Your account has been locked out. Try again in 5 minutes!");
				Platform.exit();
			}
		}
	}

}
