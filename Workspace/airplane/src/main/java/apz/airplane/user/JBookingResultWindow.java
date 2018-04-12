package apz.airplane.user;

import java.util.ArrayList;

import apz.airplane.model.Flight;
import javafx.scene.layout.GridPane;

public class JBookingResultWindow {
	
	private GridPane mainPane;
	private ArrayList<Flight> tripList;
	
	public JBookingResultWindow(ArrayList<Flight> tripList) {
		this.tripList = tripList;
		
		initialize();
		content();
	}
	
	private void initialize() {
		mainPane = new GridPane();
	}
	
	private void content() {
		for (int i = 0; i < tripList.size(); i++) {
			System.out.println("ads");
//			mainPane.add(new Label("Trip: "), columnIndex, rowIndex);
		}
	}

}
