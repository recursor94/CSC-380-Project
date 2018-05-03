package apz.airplane.user.gui.booking;

import java.util.ArrayList;

import apz.airplane.model.Flight;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookingResultWindow implements GuiApplication {
	
	private ImageView img;
	private VBox mainPane;
	private ScrollPane scrollPane;
	private ArrayList<Flight> tripList;
	private Text header;
	
	public BookingResultWindow(ArrayList<Flight> tripList) {
		this.tripList = tripList;
		initialize();
		content();
		properties();
	}
	
	public void initialize() {
		img = new ImageView(new Image(FilePath.QUERY_IMAGE));
		mainPane = new VBox(10);
		scrollPane = new ScrollPane();
		header = new Text("Trips Found");
	}
	
	public void content() {
		header.setFont(new Font(32));
		
		mainPane.getChildren().addAll(header, img, scrollPane);
		
		for (int i = 0; i < tripList.size(); i++) {
			VBox showBox = new VBox(10);
			Flight trip = tripList.get(i);
			
			ImageView img2 = new ImageView(new Image(FilePath.LOGIN_IMAGE));
			img2.setFitWidth(20);
			img2.setFitHeight(20);
			
			HBox textBox = new HBox(10);
			textBox.setAlignment(Pos.CENTER);
			
			Button bookButton = new Button("Book this Flight");
			
			bookButton.setOnAction(event -> {
				new BookingPaymentWindow(trip);
			});
			
			textBox.getChildren().addAll(new Label(), img2, new Text(tripList.get(i).toString()), new Label(), bookButton);
			showBox.getChildren().addAll(new Separator(), textBox, new Label());
			
			scrollPane.setContent(showBox);
		}
	}
	
	public void actionEvents() {}

	public void properties() {
		img.setFitWidth(230);
		img.setFitHeight(150);
		mainPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle(("APZ Application - Booking Results"));
	}
}
