package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Flight;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminAddFlight {
	private VBox mainPane = new VBox(10);
	//private ArrayList<Flight> flightList = new ArrayList<>();
	private DatePicker departDate;
	private DatePicker arriveDate;
	private AdminAddPlane planes = new AdminAddPlane();
	private ArrayList<Airplane> planeList = planes.getPlaneList();
	private ListView<Airplane> listOfPlanes = planes.getListOfPlanes();

	public AdminAddFlight() {
		
		ComboBox<String> departField = new ComboBox();
		ComboBox<String> arriveField = new ComboBox();
		Button createFlightButton = new Button("Create Flight");
		departDate = new DatePicker();
		departDate.setEditable(false);
		arriveDate = new DatePicker();
		arriveDate.setEditable(false);
		Label departLbl = new Label("Departure Airport");
		Label arriveLbl = new Label("Arrival Airport");
		Label departDateLbl = new Label("Departure Date");
		Label arriveDateLbl = new Label("Arrival Date");
		
		mainPane.getChildren().addAll(departLbl, departField, arriveLbl, arriveField, departDateLbl, departDate, arriveDateLbl, arriveDate, createFlightButton, listOfPlanes);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 400, 500));
		stage.show();
		
	}

}
