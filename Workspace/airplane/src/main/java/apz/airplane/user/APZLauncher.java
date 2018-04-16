package apz.airplane.user;

import java.util.Timer;

import apz.airplane.model.User;
import apz.airplane.model.UserController;
import apz.airplane.util.APZState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class APZLauncher extends Application {
	
	private static User user;
	private static UserController uc;
	private static BorderPane root;
	private static Stage stage;
	private Timer timer;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		uc = APZState.loadInformation();
		root = new BorderPane();
		// THIS SHOULD BE DEFAULT SIZE FOR OUR WINDOW
//		APZLauncher.getStage().setWidth(500);
//		APZLauncher.getStage().setHeight(600);
		primaryStage.setScene(new Scene(root, 300, 400));
		primaryStage.setTitle("APZ Airplane Application");
		primaryStage.setResizable(false);
		primaryStage.show();
		
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
