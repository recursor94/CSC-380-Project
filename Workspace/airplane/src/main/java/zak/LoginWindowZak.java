package zak;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindowZak {

	// if we're going to do this, lets make a text dir

	private TextField userField;
	private PasswordField passField;
	private Button loginButton;
	private Button createUserButton;

	private VBox rootPane;
	private HBox userBox, passBox, buttonBox;
	private Stage primaryStage;

	private Text status;

	public LoginWindowZak() {
		primaryStage = ZakLauncher.getStage();
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

		ZakLauncher.getBorderPane().setCenter(rootPane);
	}

	public void actionEvents() {
//		APZLauncher.getUserController().addUser(new User("asd", "asd"));

		loginButton.setOnAction((event) -> {
			UserZak user = ZakLauncher.getUserController().login(userField.getText(), passField.getText());

			if (user != null) {
				System.out.println("Username: " + userField.getText());
				System.out.println("Password: " + passField.getText());

				MessageBoxZak.message(AlertType.INFORMATION, "APZ Airplane Application", "Welcome " + userField.getText() + " to the APZ Application!");

				new BookFlightWindowZak();
			} else {
				status.setText("Incorrect user or password combination!");
			}

//			primaryStage.close();
		});

		createUserButton.setOnAction(event -> {
			new RegisterWindowZak();
		});
	}

}