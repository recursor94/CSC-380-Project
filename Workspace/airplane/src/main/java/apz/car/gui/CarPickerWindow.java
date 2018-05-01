package apz.car.gui;

import java.util.Arrays;

import apz.airplane.model.User;
import apz.airplane.util.FilePath;
import apz.car.model.CarManufacturer;
import apz.car.model.CarModel;
import apz.car.model.CarType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jimmy.pack.GuiApplication;

public class CarPickerWindow implements GuiApplication {

	private Stage stage;
	private User user;
	private ImageView img;
	private Text header;
	private VBox mainPane;
	private GridPane gridPane;
	private ComboBox<String> manufacturerBox, typeBox, modelBox;
	private Button rentButton;

	public CarPickerWindow(Stage stage, User user) {
		this.stage = stage;
		this.user = user;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(FilePath.DEFAULT_CAR_IMAGE);
		header = new Text("Car Rental");
		mainPane = new VBox(20);
		gridPane = new GridPane();
		manufacturerBox = new ComboBox<>();
		typeBox = new ComboBox<>();
		modelBox = new ComboBox<>();
		rentButton = new Button("Rent");
	}

	public void content() {
		
		// user.getRentalSystem().addCarRental();
		
		gridPane.add(new Label("Car Manufacturer"), 0, 0);
		gridPane.add(manufacturerBox, 1, 0);
		
		gridPane.add(new Label("Car Type"), 0, 1);
		gridPane.add(typeBox, 1, 1);
		
		gridPane.add(new Label("Car Model"), 0, 2);
		gridPane.add(modelBox, 1, 2);

		manufacturerBox.getItems().addAll(CarManufacturer.getManufacturerList());
		typeBox.getItems().addAll(CarType.getManufacturerList());
		
		manufacturerBox.setValue(manufacturerBox.getItems().get(0));
		typeBox.setValue(typeBox.getItems().get(0));

		modelBox.setDisable(true);
		
		mainPane.getChildren().addAll(new Label(), img, header, new Separator(), gridPane, new Separator(), rentButton);
	}
	
	public void actionEvents() {

		manufacturerBox.setOnAction(event -> {
			typeBox.setValue(typeBox.getItems().get(0));
			modelBox.setDisable(true);
			modelBox.setValue(null);
			
			img.setFitWidth(150);
			img.setFitHeight(150);
		});
		
		typeBox.setOnAction(event -> {
			updateModelBox();
			modelBox.setDisable(false);
			modelBox.setValue(modelBox.getItems().get(0));
			
			img.setImage(CarModel.getCarImage(modelBox.getValue()));
			img.setFitWidth(300);
			img.setFitHeight(150);
		});
		
		rentButton.setOnAction(event -> {
			img.setImage(CarModel.getCarImage(modelBox.getValue()));
			img.setFitWidth(300);
			img.setFitHeight(150);
		});

	}

	public void properties() {
		img.setFitWidth(150);
		img.setFitHeight(150);
		header.setFont(new Font(30));
		gridPane.setAlignment(Pos.TOP_CENTER);
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		stage.setScene(new Scene(mainPane, 400, 430));
		stage.show();
	}
	
	private void updateModelBox() {
		modelBox.getItems().clear();
		modelBox.getItems().addAll(
				Arrays.asList(CarModel.getCarModels(CarManufacturer.getManufacturerName(manufacturerBox.getValue()),
						CarType.getCarTypeName(typeBox.getValue()))));
	}

}
