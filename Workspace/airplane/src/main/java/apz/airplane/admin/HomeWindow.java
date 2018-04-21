package apz.airplane.admin;

import apz.airplane.user.APZLauncher;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeWindow {

	private VBox mainPane;
	private Button addPlaneButton, addFlightButton, userManageButton, launchButton;

	public HomeWindow(Stage primaryStage) {
		initialize();
		content();
		actionEvents(primaryStage);
		properties(primaryStage);
	}
	
	private void initialize() {
		mainPane = new VBox(10);
		addPlaneButton = new Button("Add a Plane");
		addFlightButton = new Button("Add a Flight");
		userManageButton = new Button("User Management");
		launchButton = new Button("Launch APZ Application");
	}
	
	private void content() {
		mainPane.getChildren().addAll(addPlaneButton, addFlightButton, userManageButton, launchButton);
	}
	
	private void actionEvents(Stage primaryStage) {
		addPlaneButton.setOnAction(event -> {
			new PlaneWindow();
		});

		addFlightButton.setOnAction(event -> {
			new FlightWindow(primaryStage);
		});

		userManageButton.setOnAction(event -> {
			new UserWindow();
		});
		
		launchButton.setOnAction(event -> {
			Platform.runLater(() -> {
				try {
					new APZLauncher().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
	}
	
	private void properties(Stage primaryStage) {
		primaryStage.setScene(new Scene(mainPane, 250, 250));
		primaryStage.setTitle("Admin Debug Window");
		primaryStage.show();
	}

}
