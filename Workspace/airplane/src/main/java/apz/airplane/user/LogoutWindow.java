package apz.airplane.user;

import java.util.Optional;

import apz.airplane.util.MessageBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import jimmy.pack.PasswordPromptWindow;

public class LogoutWindow {
	
	public LogoutWindow() {
		confirmLogout();
	}
	
	private void confirmLogout() {
		
		Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "APZ Application - Logout", "Are you sure you want to logout?");
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setTitle("APZ Application - Logout");
//		alert.setHeaderText(null);
//		alert.setContentText("Are you sure you want to logout?");
//
//		ButtonType yesButton = new ButtonType("Yes");
//
//		alert.getButtonTypes().setAll(yesButton, new ButtonType("No", ButtonData.CANCEL_CLOSE));
//		
//		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			new LoginWindow();
			APZLauncher.getBorderPane().setTop(null);
		}
	}

}
