package jimmy.pack;

import java.util.Optional;

import apz.airplane.user.APZLauncher;
import apz.airplane.user.LoginWindow;
import apz.airplane.util.APZState;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AccountDeleteWindow {
	
	public AccountDeleteWindow() {
		confirmDeletion();
	}
	
	private void confirmDeletion() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("APZ Application - Account Deletion");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete your account?");

		ButtonType yesButton = new ButtonType("Yes");

		alert.getButtonTypes().setAll(yesButton, new ButtonType("No", ButtonData.CANCEL_CLOSE));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == yesButton) {
			new PasswordPromptWindow(1);
		}
	}
	
	public static void deleteScreen() {
		Stage stage = new Stage();
		
		VBox mainPane = new VBox(10);
		HBox buttonPane = new HBox(10);
		
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		buttonPane.getChildren().addAll(yesButton, noButton);
		mainPane.getChildren().addAll(new Label("Are you sure you want to delete your account permanently?"), buttonPane);
		
		
		yesButton.setOnAction(event -> {
			APZLauncher.getUserController().removeUser(APZLauncher.getCurrentUser().getUsername());
			MessageBox.message(AlertType.INFORMATION, null, "Your account has been permanently deleted!");
			APZState.saveInformation();
			new LoginWindow();
			stage.close();
		});
		
		noButton.setOnAction(event -> {
			stage.close();
		});
		
		buttonPane.setAlignment(Pos.TOP_CENTER);
		mainPane.setAlignment(Pos.TOP_CENTER);
		
		stage.setScene(new Scene(mainPane, 400, 60));
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNIFIED);
		stage.show();
	}

}
