package apz.airplane.gui;

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
	
	//rootpane and usermanagement
	
	private static User user;
	private static UserController uc;
	private static BorderPane bp;
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		uc = State.loadInformation();
		bp = new BorderPane();
		
		primaryStage.setScene(new Scene(bp, 300, 400));
//		primaryStage.initStyle(StageStyle.UTILITY);
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
		return bp;
	}
	
	public static Stage getStage() {
		return stage;
	}

}
