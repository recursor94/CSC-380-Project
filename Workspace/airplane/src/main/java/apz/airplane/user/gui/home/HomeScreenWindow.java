package apz.airplane.user.gui.home;

import java.time.LocalDate;
import java.util.ArrayList;

import apz.airplane.admin.AdminState;
import apz.airplane.model.Flight;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HomeScreenWindow implements GuiApplication {
	private ImageView img;
	private VBox rootPane;
	private Text header, newsHeader, newsUpdate;
	private ArrayList<HomeTableData> flightsToday;

	public HomeScreenWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void actionEvents() {

	}

	public void properties() {
		APZLauncher.getStage().setWidth(500);
		APZLauncher.getStage().setHeight(725);
	}

	public void content() {

		img.setFitWidth(150);
		img.setFitHeight(150);
		flightsToday = getFlightsToday();
		orderFlightsByTime();
		// timeLabel.setText(LocalDateTime.now().toString());
		newsHeader.setStyle("-fx-font: 22 arial;");
		newsHeader.setFill(Color.BLACK);
		newsUpdate.setStyle("-fx-font: 20 arial;");
		newsUpdate.setFill(Color.DARKSLATEGRAY);
		header.setStyle("-fx-font: 38 arial;");
		rootPane.getChildren().addAll(new Label(), img, header, new Separator(),
				newsHeader, newsUpdate);
		rootPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setTitle("APZ Application - Home Screen");
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.HOME_PLANE));
		flightsToday = new ArrayList<>();
		rootPane = new VBox(10);
		newsUpdate = new Text(AdminState.readUpdate());
		newsHeader = new Text("Today's News:\n");
		header = new Text ("Home");
	}

	private ArrayList<HomeTableData> getFlightsToday() {
		ArrayList<Flight> allFlights = APZState.loadFlights();
		ArrayList<HomeTableData> flightsToday = new ArrayList<>(); // has to be new arraylist
		for (Flight flight : allFlights) {
			if (flight.getArriveDate().isEqual(LocalDate.now())) {
				flightsToday.add(new HomeTableData(flight.getFlightNum(), flight.getDepartureAirport().toString(),
						flight.getDestinationAirport().toString(), flight.getDepartureTime().getTimeDouble(),
						flight.getDepartureTime().getTimeString(), flight));
			}
		}
		return flightsToday;
	}

	private void orderFlightsByTime() {
		HomeTableData temp, previous;

		for (int i = 0; i < flightsToday.size(); i++) {
			previous = flightsToday.get(i);

			for (int j = 0; j < flightsToday.size(); j++) {
				double previousTime = previous.getTime();
				double comparisonTime = flightsToday.get(j).getTime();
				if (comparisonTime < previousTime) {
					temp = flightsToday.get(i);
					flightsToday.set(i, flightsToday.get(j));
					flightsToday.set(j, temp);
				}
			}
		}
	}
}
