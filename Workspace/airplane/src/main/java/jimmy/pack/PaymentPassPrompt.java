package jimmy.pack;

import apz.airplane.user.APZLauncher;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentPassPrompt implements WindowInterface {

	private Text header;
	private VBox mainPane;
	private GridPane gridPane;
	private PasswordField passwordTf;
	private Button confirmButton;

	public PaymentPassPrompt() {
		APZLauncher.getStage().getScene().getRoot().setEffect(new GaussianBlur());	// will disable if exit or password right
		initialize();
		content();
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
		
//		gridPane.add(passwordTf, 1, 2);
		mainPane.getChildren().addAll(header, passwordTf, confirmButton);
		
		
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		mainPane.setAlignment(Pos.TOP_CENTER);
		passwordTf.setMaxWidth(250);
	}

	public void actionEvents() {
		
	}

	public void properties() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 250, 100));
		stage.show();
		
		stage.setOnCloseRequest(event -> {
			APZLauncher.getStage().getScene().getRoot().setEffect(null);
		});
	}

}
