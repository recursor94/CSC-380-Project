package jimmy.pack;

import apz.airplane.user.APZLauncher;
import apz.airplane.util.FilePath;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AccountChangeWindow implements WindowInterface {
	
	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private GridPane gridPane;
	
	private TextField emailField;
	private PasswordField passField, newPassField, confirmPassField;
	
	private Button changeButton, backButton;
	
	public AccountChangeWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		headerText = new Text("Change Account Information");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		
		emailField = new TextField(APZLauncher.getCurrentUser().getEmail());
		passField = new PasswordField();
		newPassField = new PasswordField();
		confirmPassField = new PasswordField();
		
		changeButton = new Button("Change");
		backButton = new Button("Back");
	}

	public void content() {
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 1, 2);
		gridPane.add(new Label("Email: "), 2, 2);
		gridPane.add(emailField, 3, 2);
		
		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 1, 3);
		gridPane.add(new Label("Username: "), 2, 3);
		gridPane.add(new Label(APZLauncher.getCurrentUser().getUsername()), 3, 3);

		gridPane.add(new ImageView(new Image(FilePath.LOGIN_IMAGE)), 1, 4);
		gridPane.add(new Label("Old Password: "), 2, 4);
		gridPane.add(passField, 3, 4);
		gridPane.add(new Label("New Password: "), 2, 5);
		gridPane.add(newPassField, 3, 5);
		gridPane.add(new Label("Confirm New Password: "), 2, 6);
		gridPane.add(confirmPassField, 3, 6);
		
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(changeButton, backButton);
		buttonBox.setAlignment(Pos.CENTER);
		
		mainPane.getChildren().addAll(new Label(), img, headerText, gridPane, buttonBox);
	}

	public void actionEvents() {
		for (int i = 0; i < gridPane.getChildren().size(); i++) 
			if (gridPane.getChildren().get(i) instanceof ImageView) {
				((ImageView) gridPane.getChildren().get(i)).setFitWidth(50);
				((ImageView) gridPane.getChildren().get(i)).setFitHeight(50);
		}
		
		changeButton.setOnAction(event -> {
			if (checkEmail() && checkPassword()) {
				APZLauncher.getCurrentUser().setPassword(newPassField.getText());
				APZLauncher.getCurrentUser().setEmail(emailField.getText());
				MessageBox.message(AlertType.INFORMATION, null, "Account details changed!");
				new AccountInfoWindow();
			}
		}); 
		
		backButton.setOnAction(event -> {
			new AccountInfoWindow();
		});
	}

	public void properties() {
		headerText.setFont(new Font(32));
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		img.setFitWidth(150);
		img.setFitHeight(150);
		APZLauncher.getBorderPane().setCenter(mainPane);
	}
	
	private boolean checkEmail() {
		if (emailField.getText().contains("@") && emailField.getText().contains(".com"))
			return true;
		MessageBox.message(AlertType.ERROR, null, "Email input is invalid");
		return false;
	}
	
	private boolean checkPassword() {
		if (APZLauncher.getCurrentUser().validatePassword(passField.getText()) && newPassField.getText().equals(confirmPassField.getText()) && !newPassField.getText().isEmpty())
			return true;
		MessageBox.message(AlertType.ERROR, null, "Password input is invalid");
		return false;
	}

}