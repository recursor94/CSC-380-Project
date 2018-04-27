package apz.airplane.admin;

import apz.airplane.user.APZLauncher;
import apz.airplane.util.FilePath;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeWindow {
	private ImageView img;
	private Text header;
	private GridPane gridPane;
	private VBox mainPane;
	private Button addPlaneButton, addFlightButton, userManageButton, launchButton, updateTextButton;

	public HomeWindow(Stage primaryStage) {
		initialize();
		content();
		actionEvents(primaryStage);
		properties(primaryStage);
	}
	
	private void initialize() {
		img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		header = new Text("\nAdministrative Controls");
		gridPane = new GridPane();
		mainPane = new VBox(20);
		addPlaneButton = new Button("Add a Plane");
		addFlightButton = new Button("Add a Flight");
		userManageButton = new Button("User Management");
		launchButton = new Button("Launch APZ Application");
		updateTextButton = new Button("Update Home Screen Text");
	}
	
	private void content() {
		img.setFitWidth(150);
		img.setFitHeight(150);
		addPlaneButton.setMaxWidth(200);
		addFlightButton.setMaxWidth(250);
		updateTextButton.setMaxWidth(330);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(addPlaneButton, 0, 0);
		gridPane.add(addFlightButton, 1, 0);
		gridPane.add(userManageButton, 0, 1);
		gridPane.add(launchButton, 1, 1);
		header.setFont(new Font(30));
		mainPane.setAlignment(Pos.TOP_CENTER);
		mainPane.getChildren().addAll(header, img, new Separator(), gridPane, updateTextButton);
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
		
		updateTextButton.setOnAction(event -> {
			new TextUpdateWindow();
		});
	}
	
	private void properties(Stage primaryStage) {
		primaryStage.setScene(new Scene(mainPane, 425, 370));
		primaryStage.setTitle("Admin Debug Window");
		primaryStage.show();
	}

}
