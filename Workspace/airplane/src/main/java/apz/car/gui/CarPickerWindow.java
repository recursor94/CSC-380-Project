package apz.car.gui;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Optional;

import apz.airplane.model.User;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import apz.car.model.Car;
import apz.car.model.CarManufacturer;
import apz.car.model.CarModel;
import apz.car.model.CarType;
import apz.car.model.Rental;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CarPickerWindow implements GuiApplication {

	private Stage stage;
	private User user;
	private ImageView img;
	private Text header, price;
	private VBox mainPane;
	private GridPane gridPane;
	private ComboBox<String> manufacturerBox, typeBox, modelBox;
	private ComboBox<Integer> daysBox;
	private Button rentButton;
	
	public CarPickerWindow(Stage stage, User user) {
		this.stage = stage;
		this.user = user;
		initialize();
		content();
		actionEvents();
		properties();
		img.setImage(CarModel.getCarImage(modelBox.getValue()));
	}

	public void initialize() {
		img = new ImageView(FilePath.DEFAULT_CAR_IMAGE);
		header = new Text("Car Rental");
		price = new Text();
		mainPane = new VBox(20);
		gridPane = new GridPane();
		manufacturerBox = new ComboBox<>();
		typeBox = new ComboBox<>();
		modelBox = new ComboBox<>();
		daysBox = new ComboBox<>();
		rentButton = new Button("Rent");
	}

	public void content() {
		gridPane.add(new Label("Car Manufacturer: "), 0, 0);
		gridPane.add(manufacturerBox, 1, 0);

		gridPane.add(new Label("Car Type: "), 0, 1);
		gridPane.add(typeBox, 1, 1);

		gridPane.add(new Label("Car Model: "), 0, 2);
		gridPane.add(modelBox, 1, 2);

		gridPane.add(new Label("Number of Days: "), 0, 3);
		gridPane.add(daysBox, 1, 3);

		gridPane.add(new Label("Total Price: "), 0, 4);
		gridPane.add(price, 1, 4);

		manufacturerBox.getItems().addAll(CarManufacturer.getManufacturerList());
		typeBox.getItems().addAll(CarType.getManufacturerList());

		manufacturerBox.setValue(manufacturerBox.getItems().get(0));
		typeBox.setValue(typeBox.getItems().get(0));
		updateModelBox();
		modelBox.setValue(modelBox.getItems().get(0));

		for (int i = 1; i < 12; i++)
			daysBox.getItems().add(i);
		daysBox.setValue(daysBox.getItems().get(0));
		updatePrice();

		mainPane.getChildren().addAll(new Label(), img, header, new Separator(), gridPane, new Separator(), rentButton);
	}

	public void actionEvents() {
		manufacturerBox.setOnAction(event -> {
			typeBox.setValue(typeBox.getItems().get(0));
			updateModelBox();
			modelBox.setValue(modelBox.getItems().get(0));
			updatePrice();
		});

		typeBox.setOnAction(event -> {
			updateModelBox();
			modelBox.setDisable(false);
			modelBox.setValue(modelBox.getItems().get(0));
			updatePrice();
		});

		modelBox.setOnAction(event -> {
			if (modelBox.getValue() != null) {
				img.setImage(CarModel.getCarImage(modelBox.getValue()));
				img.setFitWidth(300);
				img.setFitHeight(150);
				updatePrice();
			}
		});

		daysBox.setOnAction(event -> {
			updatePrice();
		});

		rentButton.setOnAction(event -> {
			Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, null,
					"Are you sure you want to rent this vehicle?");
			if (result.get() == ButtonType.OK) {
				user.getRentalSystem()
						.addCarRental(new Rental(
								new Car(CarManufacturer.getManufacturerName(manufacturerBox.getValue()),
										new CarModel(modelBox.getValue()), CarType.getCarTypeName(typeBox.getValue())),
								daysBox.getValue(), Double.parseDouble(price.getText().replace("$", ""))));
				MessageBox.message(AlertType.INFORMATION, null, "Your selected vehicle has been set aside for you.");
				APZState.saveInformation();
				new HomeScreenWindow();
				APZLauncher.getStage().getScene().getRoot().setEffect(null);
				stage.close();
			}
		});
		
		stage.setOnCloseRequest(event -> {
			MessageBox.message(AlertType.INFORMATION, null, "Car rental cancelled!");
			APZLauncher.getStage().getScene().getRoot().setEffect(null);
		});

	}

	public void properties() {
		img.setFitWidth(300);
		img.setFitHeight(150);
		header.setFont(new Font(30));
		gridPane.setAlignment(Pos.TOP_CENTER);
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(mainPane, 400, 560));
		stage.show();
	}

	private void updateModelBox() {
		modelBox.getItems().clear();
		modelBox.getItems().addAll(
				Arrays.asList(CarModel.getCarModels(CarManufacturer.getManufacturerName(manufacturerBox.getValue()),
						CarType.getCarTypeName(typeBox.getValue()))));
	}

	private void updatePrice() {
		DecimalFormat df = new DecimalFormat(".00");
		double rate = CarType.getTypeRate(manufacturerBox.getValue(), typeBox.getValue());
		double priceCalc = daysBox.getValue() * rate;
		price.setText("$" + df.format(priceCalc));
	}
}
