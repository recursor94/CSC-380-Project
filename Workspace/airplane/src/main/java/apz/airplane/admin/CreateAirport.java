package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAirport {
	
	private ArrayList<String> airportList = new ArrayList<>();
	private VBox mainPane = new VBox(10);
	private ListView<String> airports = new ListView<>();

	
	public CreateAirport() {
		loadFile();
		airportList = State.loadAirports();
		TextField airportField = new TextField();
		Button createButton = new Button("Create Airport");
		Button removeButton = new Button("Remove");
		Button viewButton = new Button("View");
		HBox buttons = new HBox(10);
		buttons.getChildren().addAll(createButton, removeButton, viewButton);
		
		
		createButton.setOnAction(event -> {
			airportList.add(airportField.getText());
			
			airports.getItems().add(airportField.getText());
			
			State.saveAirport(airportList);
			AddFlight.populateComboBoxes();
		});
		
		viewButton.setOnAction(event -> {
			System.out.println(airportList);
		});
		
		removeButton.setOnAction(event -> {
			String airport = airports.getSelectionModel().getSelectedItem();
			airportList.remove(airport);
			State.saveAirport(airportList);
			AddFlight.populateComboBoxes();
			loadFile();
		});
		mainPane.getChildren().addAll(new Label("Airport Name"), airportField, buttons, airports);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 300, 450));
		stage.setTitle("Create Airport");
		stage.show();
	}
	public void loadFile() {
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("airportobject.apz");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			airportList = (ArrayList<String>) obj;

			if (!airports.getItems().isEmpty())
				airports.getItems().clear();
			for (int i = 0; i < airportList.size(); i++)
				airports.getItems().add(airportList.get(i));

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
