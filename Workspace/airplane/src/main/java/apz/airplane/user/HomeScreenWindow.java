package apz.airplane.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import apz.airplane.admin.AdminState;
import apz.airplane.model.Flight;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import jimmy.pack.GuiApplication;

public class HomeScreenWindow implements GuiApplication {
	private ImageView img;
	private VBox rootPane;
	private Text timeLabel, dateLabel, header, newsHeader, newsUpdate;
	private Timeline realTimeClock;
	private ArrayList<FlightInformation> flightsToday;

	private int timeHour;
	private int timeMinute;

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
		timeLabel.setStyle("-fx-font: 24 arial;");
		timeLabel.setFill(Color.BLACK);
		dateLabel.setText(LocalDateTime.now().toString());
		dateLabel.setStyle("-fx-font: 24 arial;");
		dateLabel.setFill(Color.BLACK);
		newsHeader.setStyle("-fx-font: 22 arial;");
		newsHeader.setFill(Color.BLACK);
		newsUpdate.setStyle("-fx-font: 20 arial;");
		newsUpdate.setFill(Color.DARKSLATEGRAY);

		header.setFont(new Font(28));
		rootPane.getChildren().addAll(new Label(), header, img, new Separator(), dateLabel, timeLabel, new Separator(), newsHeader, newsUpdate);
		rootPane.setAlignment(Pos.TOP_CENTER);
		APZLauncher.getBorderPane().setCenter(rootPane);
		APZLauncher.getStage().setTitle("APZ Application - Home Screen");
		refreshClockContent();
		setupClock();
		realTimeClock.stop();
		realTimeClock.playFromStart();

	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		flightsToday = new ArrayList<>();
		rootPane = new VBox(10);
		dateLabel = new Text("");
		timeLabel = new Text("00:00");
		header = new Text("Welcome to APZ Booking!");
		newsUpdate = new Text(AdminState.readUpdate());
		newsHeader = new Text("Today's News:\n");
		setupClock();
	}

	private void setupClock() {
		System.out.println("Init clock");
		realTimeClock = new Timeline(new KeyFrame(Duration.minutes(1), e -> {
			refreshClockContent();
		}));
		realTimeClock.setCycleCount(Animation.INDEFINITE);

	}

	private void refreshClockContent() {
		Calendar cal = Calendar.getInstance();
		timeHour = cal.get(Calendar.HOUR);
		timeMinute = cal.get(Calendar.MINUTE);
		// timeHour++;
		String timeOfDay = "AM";

		if (cal.get(Calendar.AM_PM) == Calendar.PM) {
			timeOfDay = "PM";
		}
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM dd");
		DateFormat timeFormat = new SimpleDateFormat("hh:mm");
		String sDate = dateFormat.format(cal.getTime());
		String sTime = timeFormat.format(cal.getTime());
		// timeLabel.setText(timeHour + ":" + (minuteString) + " " + timeOfDay);
		timeLabel.setText(sTime + " " + timeOfDay);
		dateLabel.setText(sDate);

	}

	private ArrayList<FlightInformation> getFlightsToday() {
		ArrayList<Flight> allFlights = APZState.loadFlights();
		ArrayList<FlightInformation> flightsToday = new ArrayList<>(); // has to be new arraylist
		for (Flight flight : allFlights) {
			if (flight.getArriveDate().isEqual(LocalDate.now())) {
				flightsToday.add(new FlightInformation(flight.getFlightNum(), flight.getDepartureAirport().toString(),
						flight.getDestinationAirport().toString(), flight.getDepartureTime().getTimeDouble(),
						flight.getDepartureTime().getTimeString()));
			}
		}
		return flightsToday;
	}

	private void orderFlightsByTime() {
		FlightInformation temp, previous;

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
