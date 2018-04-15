package jimmy.pack;

import java.util.ArrayList;

import apz.airplane.model.Flight;
import apz.airplane.user.APZLauncher;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookingResultWindow {
	
	private ScrollPane mainPane;
	private VBox tripBox;
	private ArrayList<Flight> tripList;
	private Text header;
	
	public BookingResultWindow(ArrayList<Flight> tripList) {
		this.tripList = tripList;
		initialize();
		content();
		properties();
	}
	
	private void initialize() {
		mainPane = new ScrollPane();
		tripBox = new VBox(10);
		header = new Text("Trips Found");
	}
	
	private void content() {
		header.setFont(new Font(32));
		
		mainPane.setContent(tripBox);
		tripBox.getChildren().add(header);
		
		for (int i = 0; i < tripList.size(); i++) {
			Flight trip = tripList.get(i);
			Button bookButton = new Button("Book this Flight");
			tripBox.getChildren().addAll(new Separator(), new Text(tripList.get(i).toString()), bookButton);
			
			bookButton.setOnAction(event -> {
				new BookingPaymentWindow(trip);
			});
		}
	}

	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setWidth(400);
		APZLauncher.getStage().setHeight(600);
	}
	
}
