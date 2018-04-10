package apz.airplane.gui;

import java.util.Timer;

import apz.airplane.User;
import apz.airplane.UserController;
import apz.airplane.util.State;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
		uc = State.loadInformation();
		root = new BorderPane();
		primaryStage.setScene(new Scene(root, 300, 400));
		primaryStage.setTitle("APZ Airplane Application");
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
