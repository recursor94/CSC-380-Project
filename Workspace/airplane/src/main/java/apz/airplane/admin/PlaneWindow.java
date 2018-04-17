package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.model.Airplane;
import apz.airplane.util.IsInteger;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlaneWindow {

	private VBox mainPane;
	private GridPane gridPane;
	private HBox buttonBox;
	private ArrayList<Airplane> planeList;
	private ListView<Airplane> planeView;
	private Text header;
	private TextField planeNumField, airlineField;
	private ComboBox<Integer> seatField;
	private Button createButton, removeButton;

	public PlaneWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	private void initialize() {
		mainPane = new VBox(10);
		gridPane = new GridPane();
		buttonBox = new HBox(10);
		planeList = new ArrayList<>();
		planeView = new ListView<>();
		planeNumField = new TextField();
		airlineField = new TextField();
		seatField = new ComboBox<>();
		header = new Text("Create Planes");
		createButton = new Button("Create");
		removeButton = new Button("Remove");
		loadFile(); // need to load initial values.
	}

	private void content() {
		for (int i = 1; i <= 200; i++)
			seatField.getItems().add(i);

		seatField.setValue(1);

		header.setFont(new Font(32));

		buttonBox.getChildren().addAll(createButton, removeButton);
		buttonBox.setAlignment(Pos.CENTER);

		gridPane.setAlignment(Pos.CENTER);

		gridPane.setVgap(10);
		gridPane.setHgap(10);

		gridPane.add(new Label("Plane Number"), 0, 0);
		gridPane.add(planeNumField, 1, 0);

		gridPane.add(new Label("Airline Name"), 0, 1);
		gridPane.add(airlineField, 1, 1);

		gridPane.add(new Label("Seat Capacity"), 0, 2);
		gridPane.add(seatField, 1, 2);

		mainPane.getChildren().addAll(header, gridPane, planeView, buttonBox);
		mainPane.setAlignment(Pos.CENTER);
	}

	private void actionEvents() {
		createButton.setOnAction(event -> {
			verifyInput();
		});

		removeButton.setOnAction(event -> {
			if (planeView.getSelectionModel().getSelectedItem() != null) {
				Airplane plane = planeView.getSelectionModel().getSelectedItem();
				planeList.remove(plane);
				AdminState.savePlane(planeList);
				loadFile();
			} else
				MessageBox.message(AlertType.ERROR, "ERROR: No Plane Selected", "You must select a plane to remove");
		});
	}

	private void properties() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Create Planes");
		stage.setScene(new Scene(mainPane, 300, 500));
		stage.show();
	}

	public void verifyInput() {
		if (planeNumField.getText().isEmpty() && airlineField.getText().isEmpty() || planeNumField.getText().isEmpty()
				|| airlineField.getText().isEmpty())
			MessageBox.message(AlertType.ERROR, "Invalid Data", "You must enter all of the necessary data");
		else if (!(IsInteger.isInteger(planeNumField.getText()))) {
			MessageBox.message(AlertType.ERROR, "Invalid Data", "The plane number must be an integer");
		} else {
			planeList.add(new Airplane(Integer.valueOf(planeNumField.getText()), airlineField.getText(),
					Integer.valueOf(seatField.getSelectionModel().getSelectedItem())));
			AdminState.savePlane(planeList);
			System.out.println(planeList);
			loadFile();
			System.out.println(planeList);
		}
	}

	private void loadFile() {
		planeList = AdminState.loadPlanes();
		System.out.println(planeList);

		if (!planeView.getItems().isEmpty())
			planeView.getItems().clear();
		for (int i = 0; i < planeList.size(); i++)
			planeView.getItems().add(planeList.get(i));
	}

}
