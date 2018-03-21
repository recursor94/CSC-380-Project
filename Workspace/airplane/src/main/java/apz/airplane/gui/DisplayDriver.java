package apz.airplane.gui;

import apz.airplane.UserController;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DisplayDriver extends Application {
	
	//rootpane and usermanagement
	
	private static UserController uc;
	private static BorderPane bp;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static UserController getUserController() {
		return uc;
	}
	
	public static BorderPane getMainPane() {
		return bp;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		uc = new UserController();
		bp = new BorderPane();
//		new UserRegistrationView(primaryStage);
		new LoginView(primaryStage);
//		new CancelFlightView(primaryStage);
	}


}
