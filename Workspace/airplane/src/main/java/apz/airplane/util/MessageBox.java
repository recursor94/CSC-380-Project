package apz.airplane.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MessageBox {
	
	public static Optional<ButtonType> message(AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		return alert.showAndWait();
		
	}
	
	//overloaded method with option to include header message:
	public static Optional<ButtonType> message(AlertType type, String title, String message, String header) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		return alert.showAndWait();
	}

}
