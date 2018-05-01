package apz.car.gui;

import java.util.Arrays;

import apz.airplane.model.User;
import apz.car.model.CarManufacturer;
import apz.car.model.CarModel;
import apz.car.model.CarType;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jimmy.pack.GuiApplication;

public class CarPickerWindow implements GuiApplication {

	private Stage stage;
	private User user;
	private VBox mainPane;
	private ComboBox<String> manufacturerBox, typeBox, modelBox;

	public CarPickerWindow(Stage stage, User user) {
		this.stage = stage;
		this.user = user;
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		mainPane = new VBox(10);
		manufacturerBox = new ComboBox<>();
		typeBox = new ComboBox<>();
		modelBox = new ComboBox<>();
	}

	public void content() {

		// user.getRentalSystem().addCarRental();

		manufacturerBox.getItems().addAll(CarManufacturer.getManufacturerList());
		typeBox.getItems().addAll(CarType.getManufacturerList());
		
		manufacturerBox.setValue(manufacturerBox.getItems().get(0));
		typeBox.setValue(typeBox.getItems().get(0));

		modelBox.setDisable(true);
		
		mainPane.getChildren().addAll(manufacturerBox, typeBox, modelBox);

	}
	
	public void actionEvents() {

		manufacturerBox.setOnAction(event -> {
			typeBox.setValue(typeBox.getItems().get(0));
			modelBox.setDisable(true);
			modelBox.setValue(null);
		});
		
		typeBox.setOnAction(event -> {
			updateModelBox();
			modelBox.setDisable(false);
			modelBox.setValue(modelBox.getItems().get(0));
		});

	}

	public void properties() {
		stage.setScene(new Scene(mainPane, 400, 400));
		stage.show();
	}
	
	private void updateModelBox() {
		modelBox.getItems().clear();
		modelBox.getItems().addAll(
				Arrays.asList(CarModel.getCarModels(CarManufacturer.getManufacturerName(manufacturerBox.getValue()),
						CarType.getCarTypeName(typeBox.getValue()))));
	}

}
