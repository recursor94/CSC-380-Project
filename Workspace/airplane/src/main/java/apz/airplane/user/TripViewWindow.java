package apz.airplane.user;

import java.util.ArrayList;

import apz.airplane.model.Booking;
import apz.airplane.model.User;
import apz.airplane.util.FilePath;
import javafx.geometry.Pos;
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
import jimmy.pack.GuiApplication;

public class TripViewWindow implements GuiApplication {

	// have email as part of registration?

	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private ScrollPane scrollPane;
	private GridPane gridPane;

	public TripViewWindow() {
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
		User uc = APZLauncher.getCurrentUser();

		ArrayList<Booking> tripList = uc.getTripList();

		int shift = 0;

		for (int i = 0; i < tripList.size(); i++) {
			Button viewButton = new Button("View in more detail");
			gridPane.add(new Label(tripList.get(i).toString()), 0, (3 + shift++ + i));
			gridPane.add(viewButton, 0, (3 + shift++ + i));
			
			if (i != tripList.size() - 1)
				gridPane.add(new Separator(), 0, (4 + shift++ + i));
			else 
				gridPane.add(new Label(), 0, (4 + shift++ + i));
			
			int index = i;
			viewButton.setOnAction(event -> {
				new TripResultWindow(tripList.get(index));
			});
		}
		
		scrollPane.setContent(gridPane);
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
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
	}

}
