package zak;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminLauncherZak extends Application {
	
	private VBox mainPane = new VBox(10);
	private Button addPlaneButton = new Button("Add a Plane");
	private Button addFlightButton = new Button("Add a Flight");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainPane.getChildren().addAll(addPlaneButton, addFlightButton);
		
		addPlaneButton.setOnAction(event -> {
			new AddPlaneZak();
		});
		
		addFlightButton.setOnAction(event -> {
			new AddFlightZak(primaryStage);
		});
		
		primaryStage.setScene(new Scene(mainPane, 500, 500));
		primaryStage.setTitle("Admin Debug Window");
		primaryStage.show();
	}

}
