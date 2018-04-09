package apz.airplane.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeWindow {

	private VBox mainPane;
	private Button addPlaneButton, addFlightButton, userManageButton;

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
	}
	
	private void content() {
		mainPane.getChildren().addAll(addPlaneButton, addFlightButton, userManageButton);
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
	}
	
	private void properties(Stage primaryStage) {
		primaryStage.setScene(new Scene(mainPane, 250, 250));
		primaryStage.setTitle("Admin Debug Window");
		primaryStage.show();
	}

}
