package apz.airplane.user;

import java.util.ArrayList;

import apz.airplane.model.Booking;
import apz.airplane.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BookingViewWindow {
	
	// have email as part of registration?
	
		private GridPane mainPane;
		private Button changeButton;
		
		private User uc;
		
		public BookingViewWindow() {
			initialize();
			content();
			properties();
		}
		
		private void initialize() {
			mainPane = new GridPane();
			changeButton = new Button("View in more detail");
			
		}
		
		private void content() {
			mainPane.setVgap(10);
			mainPane.setHgap(10);
			
			uc = APZLauncher.getCurrentUser();
			
			// X - Y
			
			mainPane.add(new Label("Booking Information"), 0, 0);
			
			ArrayList<Booking> tripList = uc.getTripList();
			
			int shift = 0;
			
			for (int i = 0; i < tripList.size(); i++) {
				mainPane.add(new Label(tripList.get(i).toString()), 0, (3 + shift++ + i));
				mainPane.add(new Label("--------------------"), 0, (3 + shift++ + i));
			}
			
			mainPane.add(changeButton, 0, (3 + shift++ + tripList.size() + 2));
		}
		
		private void properties() {
			APZLauncher.getBorderPane().setCenter(mainPane);
		}

}
