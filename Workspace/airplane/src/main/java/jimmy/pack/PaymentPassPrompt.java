package jimmy.pack;

import apz.airplane.user.APZLauncher;
import apz.airplane.user.HomeScreenWindow;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PaymentPassPrompt implements WindowInterface {

	private Text header;
	private VBox mainPane;
	private GridPane gridPane;
	private PasswordField passwordTf;
	private Button confirmButton;
	private Stage stage;

	public PaymentPassPrompt() {
		APZLauncher.getStage().getScene().getRoot().setEffect(new GaussianBlur());	// will disable if exit or password right
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		stage = new Stage();
		header = new Text("Enter your Password");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		passwordTf = new PasswordField();
		confirmButton = new Button("Confirm");
	}

	public void content() {
		stage.setScene(new Scene(mainPane, 250, 100));
		mainPane.getChildren().addAll(header, passwordTf, confirmButton);
		
	}

	public void actionEvents() {
		confirmButton.setOnAction(event -> {
			attemptLogin();
		});
		
		stage.getScene().setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				new HomeScreenWindow();
				MessageBox.message(AlertType.INFORMATION, null, "Error, could not validate account.");
				APZLauncher.getStage().getScene().getRoot().setEffect(null);
				stage.close();
			} else if (event.getCode() == KeyCode.ENTER)
				attemptLogin();
		});
	}
	
	public void properties() {
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		mainPane.setAlignment(Pos.TOP_CENTER);
		passwordTf.setMaxWidth(250);
		
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getScene().setFill(Color.BLACK);
		stage.show();
		
		stage.setOnCloseRequest(event -> {
			APZLauncher.getStage().getScene().getRoot().setEffect(null);
		});
	}
	
	private void attemptLogin() {
		if (	APZLauncher.getCurrentUser().validatePassword(passwordTf.getText())) {
			APZLauncher.getStage().getScene().getRoot().setEffect(null);
			stage.close();
		} else
			MessageBox.message(AlertType.INFORMATION, null, "Incorrect password! Try Again!");
	}

}
