package apz.airplane.user;

import apz.airplane.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class InformationWindow {
	
	// have email as part of registration?
	
	private GridPane mainPane;
	private Button changeButton;
	
	private User uc;
	
	public InformationWindow() {
		initialize();
		content();
		properties();
	}
	
	private void initialize() {
		mainPane = new GridPane();
		changeButton = new Button("Change Information");
		
	}
	
	private void content() {
		uc = APZLauncher.getCurrentUser();
		
		// X - Y
		
		mainPane.add(new Label("Account Information"), 0, 0);
		
		mainPane.add(new Label("Username: "), 0, 3);
		mainPane.add(new Label(uc.getUsername()), 1, 3);
		
		mainPane.add(new Label("Password: "), 0, 4);
		mainPane.add(new Label("******"), 1, 4);
		mainPane.add(changeButton, 0, 6);
	}
	
	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
	}
	
}
