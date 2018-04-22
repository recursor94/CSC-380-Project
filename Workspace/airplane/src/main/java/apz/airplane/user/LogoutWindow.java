package apz.airplane.user;

import java.util.Optional;

import apz.airplane.util.MessageBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class LogoutWindow {
	
	public LogoutWindow() {
		confirmLogout();
	}
	
	private void confirmLogout() {
		
		Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "APZ Application - Logout", "Press OK to confirm logout");

		if (result.get() == ButtonType.OK) {
			new LoginWindow();
			APZLauncher.getBorderPane().setTop(null);
		}
	}

}
