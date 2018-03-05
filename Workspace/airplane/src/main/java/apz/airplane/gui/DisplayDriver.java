package apz.airplane.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class DisplayDriver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new LoginDisplay2(primaryStage);
	}

}
