package apz.car.gui;

import java.util.ArrayList;

import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import apz.car.model.Rental;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CarRentalViewWindow implements GuiApplication {

	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private ScrollPane scrollPane;
	private GridPane gridPane;

	public CarRentalViewWindow() {
		ArrayList<Rental> rentList = APZLauncher.getCurrentUser().getRentalSystem().getRentalList();

		if (rentList.isEmpty()) {
			MessageBox.message(AlertType.INFORMATION, null, "No car rentals have been made to view!");
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
		headerText = new Text("Car Rental History");
		mainPane = new VBox(10);
		scrollPane = new ScrollPane();
		gridPane = new GridPane();
	}

	public void content() {
		ArrayList<Rental> rentList = APZLauncher.getCurrentUser().getRentalSystem().getRentalList();

		for (int i = 0; i < rentList.size(); i++) {
			GridPane subPane = new GridPane();
			
			subPane.add(new Label("Rental Date: "), 0, i);
			subPane.add(new Label(rentList.get(i).getDate().toString()), 1, i);
			
			subPane.add(new Label("Car Rented: "), 0, i + 1);
			subPane.add(new Label(rentList.get(i).getCar().toString()), 1, i + 1);
			
			subPane.add(new Label("Days Rented: "), 0, i + 2);
			subPane.add(new Label(rentList.get(i).getDays() + ""), 1, i + 2);
			
			subPane.add(new Label("Total Cost: "), 0, i + 3);
			subPane.add(new Label("$" + rentList.get(i).getPrice()), 1, i + 3);
			
			subPane.setAlignment(Pos.TOP_LEFT);
			
			gridPane.add(subPane, 0, i);
			
		}

		scrollPane.setContent(gridPane);
		scrollPane.setFitToHeight(true);
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
		APZLauncher.getStage().setTitle("APZ Application - Car Rentals");
	}
}
