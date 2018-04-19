package jimmy.pack;

import java.util.ArrayList;

import apz.airplane.model.Flight;
import apz.airplane.user.APZLauncher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookingResultWindow implements WindowInterface {
	
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
	
	public void initialize() {
		mainPane = new ScrollPane();
		tripBox = new VBox(10);
		header = new Text("Trips Found");
	}
	
	public void content() {
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
		
		tripBox.setAlignment(Pos.CENTER);
	}
	
	public void actionEvents() {
		// empty 
	}

	public void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
	}
	
}
