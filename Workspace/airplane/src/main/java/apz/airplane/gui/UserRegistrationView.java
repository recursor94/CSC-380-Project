package apz.airplane.gui;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserRegistrationView {

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
	
	public void initialize() {
		rootPane = new VBox(10);
	}
}
