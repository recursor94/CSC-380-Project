package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminAddPlane {

	private VBox mainPane = new VBox(10);
	private ArrayList<Airplane> planeList = new ArrayList<>();

	public AdminAddPlane() {
		loadFile();
		
		TextField planeField = new TextField();
		TextField airlineField = new TextField();
		ComboBox<Integer> seatsField = new ComboBox();
		
		for (int i = 1; i < 200; i++)
			seatsField.getItems().add(i);
		
		seatsField.setValue(1);
		
		Button createButton = new Button("Create");
		Button testButton = new Button("Load");

		createButton.setOnAction(event -> {
			int planeNum = Integer.valueOf(planeField.getText());
			String airlineName = airlineField.getText();
			int seatCap = Integer.valueOf(seatsField.getSelectionModel().getSelectedItem());

			planeList.add(new Airplane(planeNum, airlineName, seatCap));

			SaveState.savePlane(planeList);
			loadFile();
		});

		testButton.setOnAction(event -> {
			resetList();
			System.out.println(planeList);
			loadFile();
			System.out.println(planeList);
		});

		Button removeButton = new Button("Remove");

		mainPane.getChildren().addAll(new Label("Plane Number"), planeField, new Label("Airline Name"), airlineField,
				new Label("Seat Capacity"), seatsField, createButton, testButton, listOfPlanes, removeButton);

		removeButton.setOnAction(event -> {
			Airplane plane = listOfPlanes.getSelectionModel().getSelectedItem();
			planeList.remove(plane);
			SaveState.savePlane(planeList);
			loadFile();
		});

		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 200, 500));
		stage.show();
	}

	ListView<Airplane> listOfPlanes = new ListView();

	public void loadFile() {
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("planeobject.apz");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			planeList = (ArrayList<Airplane>) obj;

			if (!listOfPlanes.getItems().isEmpty())
				listOfPlanes.getItems().clear();
			for (int i = 0; i < planeList.size(); i++)
				listOfPlanes.getItems().add(planeList.get(i));

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void resetList() {
		planeList = new ArrayList<>();
	}

}
