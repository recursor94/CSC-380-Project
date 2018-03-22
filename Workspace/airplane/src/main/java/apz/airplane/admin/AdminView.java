package apz.airplane.admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView extends Application {
	
	private VBox mainPane = new VBox(10);
	private Button addPlaneButton = new Button("Add a Plane");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainPane.getChildren().addAll(addPlaneButton);
		
		addPlaneButton.setOnAction(event -> {
			new AdminAddPlane();
		});
		
		primaryStage.setScene(new Scene(mainPane, 500, 500));
		primaryStage.setTitle("Admin Debug Window");
		primaryStage.show();
	}

}
