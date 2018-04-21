package apz.airplane.user;

import apz.airplane.model.User;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jimmy.pack.WindowInterface;

public class AccountInfoWindow implements WindowInterface {

	// have email as part of registration?

	private VBox mainPane;
	private GridPane gridPane;
	private Text headerText;
	private Button changeButton;

	private User uc;

	public AccountInfoWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		mainPane = new VBox(10);
		gridPane = new GridPane();
		headerText = new Text("Account Information");
		changeButton = new Button("Change Information");
	}

	public void content() {
		uc = APZLauncher.getCurrentUser();

		gridPane.add(new Label("Email: "), 2, 2);
		gridPane.add(new Label(uc.getEmail()), 3, 2);
		
		gridPane.add(new Label("Username: "), 2, 3);
		gridPane.add(new Label(uc.getUsername()), 3, 3);

		gridPane.add(new Label("Password: "), 2, 4);
		gridPane.add(new Label("******"), 3, 4);
		gridPane.add(changeButton, 4, 6);
		
		mainPane.getChildren().addAll(headerText, new Separator(), gridPane);
	}
	
	public void actionEvents() {
		headerText.setFont(new Font(32));
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);
	}

	public void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
	}

}
