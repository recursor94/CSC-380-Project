package apz.airplane.user;

<<<<<<< HEAD
=======

>>>>>>> 09b111f555d54888b41cbac5496a3f512286327d
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
<<<<<<< HEAD

=======
	
>>>>>>> 09b111f555d54888b41cbac5496a3f512286327d
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		uc = APZState.loadInformation();
		root = new BorderPane();
		primaryStage.setScene(new Scene(root, 0, 0));
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
