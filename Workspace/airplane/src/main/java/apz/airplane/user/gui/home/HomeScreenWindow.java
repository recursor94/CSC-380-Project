package apz.airplane.user.gui.home;

import apz.airplane.admin.AdminState;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HomeScreenWindow implements GuiApplication {
	
	private ImageView img;
	private VBox rootPane, headerPane;
	private Text newsHeader, newsUpdate;

	public HomeScreenWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.HOME_PLANE));
		rootPane = new VBox(10);
		headerPane = new VBox(0);
		newsHeader = new Text("Today's News:\n");
		newsUpdate = new Text(AdminState.readUpdate());
	}

	public void content() {
		headerPane.getChildren().addAll(img, new Separator());
		rootPane.getChildren().addAll(new Label(), img, headerPane, newsHeader, newsUpdate);
	}

	public void actionEvents() {}

	public void properties() {
		rootPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setTitle("APZ Application - Home Screen");
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(725);
		img.setFitWidth(150);
		img.setFitHeight(150);
		newsHeader.setStyle("-fx-font: 22 arial;");
		newsHeader.setFill(Color.BLACK);
	}
}