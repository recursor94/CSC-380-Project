package apz.airplane.user.gui.account;

import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PasswordPromptWindow implements GuiApplication {

	private int index;
	private Text header;
	private VBox mainPane;
	private GridPane gridPane;
	private PasswordField passwordTf;
	private Button confirmButton, cancelButton;
	private Stage stage;
	
	public PasswordPromptWindow(int index) {
		this.index = index;
		APZLauncher.getStage().getScene().getRoot().setEffect(new GaussianBlur());	// will disable if exit or password right
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		stage = new Stage();
		header = new Text("Enter your password to continue.");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		passwordTf = new PasswordField();
		confirmButton = new Button("Confirm");
		cancelButton = new Button("Cancel");
	}

	public void content() {
		stage.setScene(new Scene(mainPane, 250, 100));
		
		HBox buttonBox = new HBox(10);
		
		buttonBox.getChildren().addAll(confirmButton, cancelButton);
		buttonBox.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(header, passwordTf, buttonBox);
	}

	public void actionEvents() {
		confirmButton.setOnAction(event -> {
			attemptLogin();
		});
		
		cancelButton.setOnAction(event -> {
			exit();
		});
		
		stage.getScene().setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				exit();
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
		stage.show();
		
		stage.setOnCloseRequest(event -> {
			APZLauncher.getStage().getScene().getRoot().setEffect(null);
		});
	}
	
	private void attemptLogin() {
		if (APZLauncher.getCurrentUser().validatePassword(passwordTf.getText())) {
			APZLauncher.getStage().getScene().getRoot().setEffect(null);
			stage.close();
			if (index == 1) 
				AccountDeleteWindow.deleteScreen();
		} else
			MessageBox.message(AlertType.INFORMATION, null, "Incorrect password! Try Again!");
	}
	
	private void redirect() {
		if (index == 0)
			new HomeScreenWindow();
		else if (index == 1) 
			System.out.println("Invalid pass");
	}
	
	private void exit() {
		redirect();
		MessageBox.message(AlertType.INFORMATION, null, "Error, could not validate account.");
		APZLauncher.getStage().getScene().getRoot().setEffect(null);
		stage.close();
	}
}
