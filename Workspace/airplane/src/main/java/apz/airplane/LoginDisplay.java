package apz.airplane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginDisplay extends Application{
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 400;
	private static final String FRAME_TITLE = "Login";
	private static final String LABEL_USERNAME = "Username: ";
	private static final String LABEL_PASSWORD = "Password: ";

	private TextField fieldUsername;
	private PasswordField fieldPassword;

	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle(FRAME_TITLE);
		setupFrameLayout(primaryStage);
		primaryStage.show();
	}
	
	private void setupFrameLayout(Stage stage) {
		HBox hBoxUsername = new HBox();
		HBox hBoxPassword = new HBox();

		VBox vBoxUserInput = new VBox();
		Label labelUsername = new Label(LABEL_USERNAME);
		Label labelPassword = new Label(LABEL_PASSWORD);
		
		fieldUsername = new TextField();
		fieldPassword = new PasswordField();
		
		hBoxUsername.getChildren().addAll(labelUsername, fieldUsername);
		hBoxPassword.getChildren().addAll(labelPassword, fieldPassword);
		vBoxUserInput.getChildren().addAll(hBoxUsername, hBoxPassword);
		
		StackPane root = new StackPane();
		root.getChildren().add(vBoxUserInput);
		
		Scene scene = new Scene(root, FRAME_WIDTH, FRAME_HEIGHT);
		stage.setScene(scene);
	}
	
}
