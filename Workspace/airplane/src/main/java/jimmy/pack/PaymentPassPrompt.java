package jimmy.pack;

import apz.airplane.user.APZLauncher;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
		header = new Text("Enter your Password");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		passwordTf = new PasswordField();
		confirmButton = new Button("Confirm");
	}

	public void content() {
		mainPane.getChildren().addAll(header, passwordTf, confirmButton);
		
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		mainPane.setAlignment(Pos.TOP_CENTER);
		passwordTf.setMaxWidth(250);
	}

	public void actionEvents() {
		confirmButton.setOnAction(event -> {
			if (	APZLauncher.getCurrentUser().validatePassword(passwordTf.getText())) {
				APZLauncher.getStage().getScene().getRoot().setEffect(null);
				stage.close();
			}
		});
	}

	public void properties() {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 250, 100));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getScene().setFill(Color.BLACK);
		stage.show();
		
		stage.setOnCloseRequest(event -> {
			APZLauncher.getStage().getScene().getRoot().setEffect(null);
		});
	}

}
