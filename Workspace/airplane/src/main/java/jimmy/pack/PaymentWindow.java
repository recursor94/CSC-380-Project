package jimmy.pack;

import apz.airplane.user.APZLauncher;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentWindow {
	
	private VBox mainPane;
	private GridPane gridPane; 

	
	// please enter password first
	public PaymentWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	private void initialize() {
		mainPane = new VBox(10);
		gridPane = new GridPane();
	}

	private void content() {
		mainPane.getChildren().addAll(gridPane);
	}

	private void actionEvents() {
		
	}

	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
	}

}
