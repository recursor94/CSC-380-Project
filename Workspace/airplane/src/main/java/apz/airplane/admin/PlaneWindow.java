package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.model.Airplane;
import apz.airplane.util.MessageBox;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private Text header, planeText;
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
		header = new Text("Create Planes");
		planeText = new Text("List of Created Planes");
		planeNumField = new TextField();
		airlineField = new TextField();
		seatField = new ComboBox<>();
		createButton = new Button("Create Plane");
		removeButton = new Button("Remove Plane");
		loadFile(); 
	}

	private void content() {
		for (int i = 1; i <= 200; i++)
			seatField.getItems().add(i);

		seatField.setValue(1);

		buttonBox.getChildren().addAll(createButton, removeButton);

		gridPane.add(new Label("Plane Number"), 0, 0);
		gridPane.add(planeNumField, 1, 0);

		gridPane.add(new Label("Airline Name"), 0, 1);
		gridPane.add(airlineField, 1, 1);

		gridPane.add(new Label("Seat Capacity"), 0, 2);
		gridPane.add(seatField, 1, 2);

		mainPane.getChildren().addAll(header, new Separator(), gridPane, createButton, new Separator(), planeText, planeView, removeButton);
		
	}

	private void actionEvents() {
		createButton.setOnAction(event -> {
			verifyInput();
			
			if(planeView.getSelectionModel().isEmpty())
				removeButton.setDisable(true);
		});

		planeView.getSelectionModel().selectedItemProperty().addListener(event -> {
			removeButton.setDisable(false);
		});
		removeButton.setOnAction(event -> {
				Airplane plane = planeView.getSelectionModel().getSelectedItem();
				planeList.remove(plane);
				AdminState.savePlane(planeList);
				loadFile();
				removeButton.setDisable(true);
		});
		
		planeView.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE)
				resetFields();
		});
		
		planeNumField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (!"0123456789".contains(keyEvent.getCharacter()))
		            keyEvent.consume();
	        }
	      });
	}

	private void properties() {
		removeButton.setDisable(true);
		header.setFont(new Font(32));
		planeText.setFont(new Font(18));
		buttonBox.setAlignment(Pos.CENTER);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		mainPane.setAlignment(Pos.CENTER);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Create Planes");
		stage.setScene(new Scene(mainPane, 325, 500));
		stage.setResizable(false);
		stage.show();
	}

	public void verifyInput() {
		if (planeNumField.getText().isEmpty() && airlineField.getText().isEmpty() || planeNumField.getText().isEmpty()
				|| airlineField.getText().isEmpty())
			MessageBox.message(AlertType.ERROR, "Invalid Data", "You must enter all of the necessary data");
		else {
				int planeNum = Integer.valueOf(planeNumField.getText());
				String airline = airlineField.getText();
				int capacity = seatField.getSelectionModel().getSelectedItem();
				
				planeList.add(new Airplane(planeNum, airline, capacity));
				AdminState.savePlane(planeList);
				loadFile();
				resetFields();
		}
	}
	
	private void resetFields() {
		planeNumField.setText("");
		airlineField.setText("");
		seatField.setValue(1);
		planeView.getSelectionModel().clearSelection();
		removeButton.setDisable(true);
	}

	private void loadFile() {
		planeList = AdminState.loadPlanes();
		if (!planeView.getItems().isEmpty())
			planeView.getItems().clear();
		for (int i = 0; i < planeList.size(); i++)
			planeView.getItems().add(planeList.get(i));
	}
}
