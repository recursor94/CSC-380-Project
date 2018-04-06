package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.util.MessageBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlaneWindow {

	private VBox mainPane = new VBox(10);
	private ArrayList<Airplane> planeList = new ArrayList<>();
	private ListView<Airplane> listOfPlanes = new ListView<>();
	private TextField planeField, airlineField;
	private ComboBox<Integer> seatField;
	private Button createButton, removeButton;

	public PlaneWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	private void initialize() {
		planeField = new TextField();
		airlineField = new TextField();
		seatField = new ComboBox<>();
		createButton = new Button("Create");
		removeButton = new Button("Remove");
		loadFile(); // need to load initial values.
	}

	private void content() {
		for (int i = 1; i <= 200; i++)
			seatField.getItems().add(i);
		seatField.setValue(1);

		mainPane.getChildren().addAll(new Label("Plane Number"), planeField, new Label("Airline Name"), airlineField,
				new Label("Seat Capacity"), seatField, createButton, listOfPlanes, removeButton);
	}

	private void actionEvents() {
		createButton.setOnAction(event -> {

			if (planeField.getText().isEmpty() && airlineField.getText().isEmpty() || planeField.getText().isEmpty() || airlineField.getText().isEmpty())
				MessageBox.message(AlertType.ERROR, "Invalid Data", "You must enter all of the necessary data");
			else {
				planeList.add(new Airplane(Integer.valueOf(planeField.getText()), airlineField.getText(), Integer.valueOf(seatField.getSelectionModel().getSelectedItem())));
				AdminState.savePlane(planeList);
				System.out.println(planeList);
				loadFile();
				System.out.println(planeList);
			}

		});

		removeButton.setOnAction(event -> {
			if (listOfPlanes.getSelectionModel().getSelectedItem() != null) {
				Airplane plane = listOfPlanes.getSelectionModel().getSelectedItem();
				planeList.remove(plane);
				AdminState.savePlane(planeList);
				loadFile();
			}
			else {
				MessageBox.message(AlertType.ERROR, "ERROR: No Plane Selected", "You must select a plane to remove");
			}
		});
	}

	private void properties() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Create Planes");
		stage.setScene(new Scene(mainPane, 300, 500));
		stage.show();
	}

	public void loadFile() {
		planeList = AdminState.loadPlanes();
		System.out.println(planeList);

		if (!listOfPlanes.getItems().isEmpty())
			listOfPlanes.getItems().clear();
		for (int i = 0; i < planeList.size(); i++)
			listOfPlanes.getItems().add(planeList.get(i));
	}

}
