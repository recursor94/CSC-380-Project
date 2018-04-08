package apz.airplane.admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminLauncher extends Application {
	
	private VBox mainPane = new VBox(10);
	private Button addPlaneButton = new Button("Add a Plane");
	private Button addFlightButton = new Button("Add a Flight");
	private Button userManageButton = new Button("User Management");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainPane.getChildren().addAll(addPlaneButton, addFlightButton, userManageButton);
		
		addPlaneButton.setOnAction(event -> {
			new PlaneWindow();
		});
		
		addFlightButton.setOnAction(event -> {
			new FlightWindow(primaryStage);
		});
		
		userManageButton.setOnAction(event -> {
			new UserWindow();
		});
		
		primaryStage.setScene(new Scene(mainPane, 250, 250));
		primaryStage.setTitle("Admin Debug Window");
		primaryStage.show();
	}

}
