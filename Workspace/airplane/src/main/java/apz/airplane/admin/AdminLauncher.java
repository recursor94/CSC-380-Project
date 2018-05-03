package apz.airplane.admin;

import javafx.application.Application;
import javafx.stage.Stage;

public class AdminLauncher extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new HomeWindow(primaryStage);
	}
}
