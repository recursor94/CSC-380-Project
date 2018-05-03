package apz.airplane.user.gui;

import apz.airplane.model.User;
import apz.airplane.model.UserController;
import apz.airplane.util.APZState;
import apz.airplane.util.JUtility;
import apz.airplane.util.MessageBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class APZLauncher extends Application {

	private static User user;
	private static UserController uc;
	private static BorderPane root;
	private static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		if (!APZState.checkFilesExist()) {
			MessageBox.message(AlertType.ERROR, "APZ Software Error", "Software not properly configured.\nPlease contact your system administrator!");
			return;
		}
		
		stage = primaryStage;
		uc = APZState.loadInformation();
		root = new BorderPane();
		
		primaryStage.setScene(new Scene(root, 300, 300));
		primaryStage.setTitle("APZ Airplane Application");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		primaryStage.getScene().setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.P && event.isControlDown())
				try {
					new JUtility().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
		});

		new LoginWindow();
	}

	public static void setCurrentUser(User userLogged) {
		user = userLogged;
	}

	public static User getCurrentUser() {
		return user;
	}

	public static UserController getUserController() {
		return uc;
	}

	public static BorderPane getBorderPane() {
		return root;
	}

	public static Stage getStage() {
		return stage;
	}
}
