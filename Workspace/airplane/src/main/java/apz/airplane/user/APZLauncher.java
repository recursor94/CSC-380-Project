package apz.airplane.user;

<<<<<<< HEAD:Workspace/airplane/src/main/java/apz/airplane/gui/APZLauncher.java
import java.util.Timer;

import apz.airplane.User;
import apz.airplane.UserController;
=======
import apz.airplane.model.User;
import apz.airplane.model.UserController;
>>>>>>> fe89f75140c24e17929d657d7213d212565a35d6:Workspace/airplane/src/main/java/apz/airplane/user/APZLauncher.java
import apz.airplane.util.State;
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
		uc = State.loadInformation();
		root = new BorderPane();
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
