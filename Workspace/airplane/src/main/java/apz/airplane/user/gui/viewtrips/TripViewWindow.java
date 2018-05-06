package apz.airplane.user.gui.viewtrips;

import java.util.ArrayList;

import apz.airplane.model.Booking;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TripViewWindow implements GuiApplication {

	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private ScrollPane scrollPane;
	private GridPane gridPane;

	public TripViewWindow() {

		ArrayList<Booking> tripList = APZLauncher.getCurrentUser().getTripList();

		if (tripList.isEmpty()) {
			MessageBox.message(AlertType.INFORMATION, null, "No trips have been made to view!");
			new HomeScreenWindow();
			return;
		}

		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.HISTORY_IMAGE));
		headerText = new Text("Trip History");
		mainPane = new VBox(10);
		scrollPane = new ScrollPane();
		gridPane = new GridPane();
	}

	public void content() {
		ArrayList<Booking> tripList = APZLauncher.getCurrentUser().getTripList();

		GridPane itemPane;
		for (int i = 0; i < tripList.size(); i++) {
			itemPane = new GridPane();
			Button viewButton = new Button("View in more detail");
			String sItemPane = tripList.get(i).toString();
			Label label = new Label(sItemPane);
			VBox bottomBox = new VBox(10);
			bottomBox.getChildren().add(viewButton);
			itemPane.add(label, 0, 3);
			if (i != tripList.size() - 1)
				bottomBox.getChildren().add(new Separator());
			else
				bottomBox.getChildren().add(new Label());
			int index = i;
			VBox itemBox = new VBox(10);
			itemBox.getChildren().addAll(itemPane, bottomBox);
			itemBox.setMinHeight(150);
			viewButton.setOnAction(event -> {
				new TripResultWindow(tripList.get(index));
			});
			gridPane.add(itemBox, 0, i);
		}

		mainPane.getChildren().addAll(new Label(), headerText, img, scrollPane);
	}

	public void actionEvents() {
	}

	public void properties() {
		img.setFitWidth(235);
		img.setFitHeight(150);
		headerText.setFont(new Font(32));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setContent(gridPane);
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Trip History");
	}
}
