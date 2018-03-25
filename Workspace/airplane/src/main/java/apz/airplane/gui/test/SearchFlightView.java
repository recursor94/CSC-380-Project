package apz.airplane.gui.test;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchFlightView {
	
	private VBox mainPane = new VBox(10);
	
	public SearchFlightView(Stage stage) {
	
		ComboBox<String> destBox = new ComboBox<>();
		destBox.getItems().add("SYR");
		ComboBox<String> arrBox = new ComboBox<>();
		arrBox.getItems().add("JFK");
		DatePicker destPick = new DatePicker();
		DatePicker arrPick = new DatePicker();
		
		destPick.setEditable(false);
		arrPick.setEditable(false);
		
		mainPane.getChildren().addAll(destBox, arrBox, destPick, arrPick);
		
		mainPane.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.A && event.isControlDown())
				System.out.println("A was pressed + CTRL was held down");
		});
		
		
		stage.setScene(new Scene(mainPane, 200, 300));
		stage.setTitle("Search for Flights");
		stage.show();
//		DisplayDriver.getBorderPane().setCenter(mainPane);
	}

}
