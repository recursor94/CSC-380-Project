package apz.airplane.admin;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAirport {
	
	private ArrayList<String> airportList = new ArrayList<>();
	private VBox mainPane = new VBox(10);
	
	public CreateAirport() {
		airportList = SaveState.loadAirports();
		TextField airportField = new TextField();
		Button createButton = new Button("Create Airport");
		Button viewButton = new Button("View");
		
		createButton.setOnAction(event -> {
			airportList.add(airportField.getText());
			SaveState.saveAirport(airportList);
			AdminAddFlight.populateComboBoxes();
		});
		
		viewButton.setOnAction(event -> {
			System.out.println(airportList);
		});
		
		mainPane.getChildren().addAll(new Label("Airport Name"), airportField, createButton, viewButton);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 300, 150));
		stage.setTitle("Create Airport");
		stage.show();
	}
	

}
