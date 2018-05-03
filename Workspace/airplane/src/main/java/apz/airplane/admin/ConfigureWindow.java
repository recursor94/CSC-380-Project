package apz.airplane.admin;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConfigureWindow {
	
	public ConfigureWindow() {
		
		Stage stage = new Stage();
		GridPane gridPane = new GridPane();
		
		boolean[] config = AdminState.configureFiles();
		
		Label user = new Label(config[0] + "");
		Label flight = new Label(config[1] + "");
		Label airport = new Label(config[2] + "");
		Label plane = new Label(config[3] + "");
		Label home = new Label(config[4] + "");
		
		Label[] labels = {user, flight, airport, plane, home};
		
		for (int i = 0; i < labels.length; i++) {
			if (config[i])
				labels[i].setTextFill(Color.GREEN);
			else
				labels[i].setTextFill(Color.RED);
		}
			
		gridPane.add(new Label("User status: "), 0, 0);
		gridPane.add(user, 1, 0);
		
		gridPane.add(new Separator(), 0, 1);
		gridPane.add(new Separator(), 1, 1);
		
		gridPane.add(new Label("Flight status: "), 0, 2);
		gridPane.add(flight, 1, 2);
		
		gridPane.add(new Label("Airport status: "), 0, 3);
		gridPane.add(airport, 1, 3);
		
		gridPane.add(new Label("Plane status: "), 0, 4);
		gridPane.add(plane, 1, 4);
		
		gridPane.add(new Label("HSNews status: "), 0, 5);
		gridPane.add(home, 1, 5);
		
		gridPane.setAlignment(Pos.CENTER);
		
		stage.setTitle("APZ Configuration");
		stage.setScene(new Scene(gridPane, 300, 120));
		stage.setResizable(false);
		stage.show();
	}
}

