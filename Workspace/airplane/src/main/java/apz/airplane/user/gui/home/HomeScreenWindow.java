package apz.airplane.user.gui.home;

import java.util.ArrayList;

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
	private ArrayList<HomeTableData> flightsToday;

	public HomeScreenWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.HOME_PLANE));
		flightsToday = new ArrayList<>();
		rootPane = new VBox(10);
		headerPane = new VBox(0);
		newsUpdate = new Text(AdminState.readUpdate());
		newsHeader = new Text("Today's News:\n");
	}

	public void content() {
		headerPane.getChildren().addAll(img, new Separator());
		rootPane.getChildren().addAll(new Label(), img, headerPane, newsHeader, newsUpdate);
		rootPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setTitle("APZ Application - Home Screen");
	}

	public void actionEvents() {

	}

	public void properties() {
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(725);
		img.setFitWidth(150);
		img.setFitHeight(150);
		newsHeader.setStyle("-fx-font: 22 arial;");
		newsHeader.setFill(Color.BLACK);
	}
}