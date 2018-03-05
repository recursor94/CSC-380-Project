package apz.airplane.gui;

import apz.airplane.UserController;
import javafx.application.Application;
import javafx.stage.Stage;

public class DisplayDriver extends Application {
	
	//rootpane and usermanagement
	
	public static UserController uc = new UserController();

	public static void main(String[] args) {
		launch(args);
	}
	
	public static UserController getUserController() {
		return uc;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new LoginView(primaryStage);
	}

}
